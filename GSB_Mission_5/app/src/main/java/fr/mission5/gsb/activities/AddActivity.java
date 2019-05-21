package fr.mission5.gsb.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.mission5.gsb.R;
import fr.mission5.gsb.callbacks.CoefListCallback;
import fr.mission5.gsb.callbacks.PraticienListCallback;
import fr.mission5.gsb.callbacks.RapportCreateCallback;
import fr.mission5.gsb.objects.CoefficientConfiance;
import fr.mission5.gsb.objects.Praticien;
import fr.mission5.gsb.objects.Visiteur;
import fr.mission5.gsb.persistence.DatabaseManager;

public class AddActivity extends AppCompatActivity implements PraticienListCallback, CoefListCallback, RapportCreateCallback {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private AddActivity context = this;

    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    // composants
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        loadDatePicker();
        loadSendButton();
        loadBackButton();
        loadPraticienList();
        loadCoefList();

    }

    public void loadDatePicker() {
        mDisplayDate = findViewById(R.id.add_screen_datevisite_input);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    public void loadSendButton() {

        Button sendButton = findViewById(R.id.add_screen_confirm_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get form data
                EditText dateVisiteView = findViewById(R.id.add_screen_datevisite_input);
                Spinner spinnerPraticienNameView = findViewById(R.id.add_screen_praticien_spinner);
                EditText motifView = findViewById(R.id.add_screen_motif_input);
                EditText bilanView = findViewById(R.id.add_screen_bilan_input);

                if(!dateVisiteView.getText().toString().isEmpty() && !motifView.getText().toString().isEmpty() && !bilanView.getText().toString().isEmpty())
                {
                    // get data from form
                    Visiteur visiteurSession = databaseManager.getVisiteurSession();
                    String vis_matricule = visiteurSession.getMatricule();

                    int pra_num = 1; // form
                    int coef_num = 1; // form

                    String rap_motif = motifView.getText().toString(); // form
                    String rap_bilan = bilanView.getText().toString(); // form
                    String rap_dateVisite = dateVisiteView.getText().toString();

                    // create request
                    databaseManager.createRapportVisite(vis_matricule, pra_num, coef_num, rap_motif, rap_bilan, rap_dateVisite, context);
                }

            }
        });

    }

    public void loadBackButton() {
        backButton = findViewById(R.id.add_screen_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadPraticienList() {
        databaseManager.getPraticienByVisMatricule(databaseManager.getVisiteurSession().getMatricule(), context);
    }

    public void loadCoefList() {
        databaseManager.getCoefficientConfiance(this);
    }

    @Override
    public void onGetList(List<Praticien> praticiens) {

        // creation d'une liste contenant les noms des praticiens
        List<String> praticienNames = new ArrayList<>();
        for(Praticien praticien : praticiens)
        {
            praticienNames.add(praticien.getNom() + " " + praticien.getPrenom());
        }

        Spinner spinner = findViewById(R.id.add_screen_praticien_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, praticienNames);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onGetListCoef(List<CoefficientConfiance> coeffffs) {

        // creation d'une liste contenant la liste des coeffffs
        List<String> coeffNames = new ArrayList<>();
        for(CoefficientConfiance coeffff : coeffffs)
        {
            coeffNames.add(coeffff.getNum() + ". " + coeffff.getLibelle());
        }

        Spinner spinner = findViewById(R.id.add_screen_coefconfiance_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, coeffNames);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onFailedCoef() {
        Toast.makeText(getApplicationContext(), "Aucun coef", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed() {
        Toast.makeText(getApplicationContext(), "Aucun praticien", Toast.LENGTH_LONG).show();
    }


    // rapport send

    @Override
    public void onSend() {
        Toast.makeText(getApplicationContext(), "Rapport envoyé !", Toast.LENGTH_LONG).show();

        // redirect to previous page
        finish();
    }

    @Override
    public void onFailedSend() {
        Toast.makeText(getApplicationContext(), "Erreur envoi du rapport", Toast.LENGTH_LONG).show();
    }
}
