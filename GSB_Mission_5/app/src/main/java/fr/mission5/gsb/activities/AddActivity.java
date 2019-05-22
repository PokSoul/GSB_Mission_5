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

    // Composants
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
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

    /*===============================================*
     * Chargement des éléments du formulaire d'ajout *
     *===============================================*/

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
                month += 1;
                String date = year + "-" + month + "-" + day;
                mDisplayDate.setText(date);
            }
        };
    }

    public void loadSendButton() {

        Button sendButton = findViewById(R.id.add_screen_confirm_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ressources du formulaire
                EditText dateVisiteView = findViewById(R.id.add_screen_datevisite_input);
                Spinner spinnerPraticienNameView = findViewById(R.id.add_screen_praticien_spinner);
                EditText motifView = findViewById(R.id.add_screen_motif_input);
                EditText bilanView = findViewById(R.id.add_screen_bilan_input);

                if(!dateVisiteView.getText().toString().isEmpty() && !motifView.getText().toString().isEmpty() && !bilanView.getText().toString().isEmpty())
                {
                    // On obtient les données depuis le formulaire
                    Visiteur visiteurSession = databaseManager.getVisiteurSession();
                    String vis_matricule = visiteurSession.getMatricule();

                    int pra_num = 1; // form
                    int coef_num = 1; // form

                    String rap_motif = motifView.getText().toString(); // form
                    String rap_bilan = bilanView.getText().toString(); // form
                    String rap_dateVisite = dateVisiteView.getText().toString();

                    // Requête d'insertion
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

    /*===========*
     * Callbacks *
     *===========*/

    // Praticien :

    @Override
    public void onPraticienListOk(List<Praticien> lesPraticiens) {

        // creation d'une liste contenant les noms des praticiens
        List<String> praticienNames = new ArrayList<>();
        for(Praticien unPraticien : lesPraticiens)
        {
            praticienNames.add(unPraticien.getNom() + " " + unPraticien.getPrenom());
        }

        Spinner spinner = findViewById(R.id.add_screen_praticien_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, praticienNames);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onPraticienListFailed() {
        Toast.makeText(getApplicationContext(), "Aucun praticien", Toast.LENGTH_LONG).show();
    }

    // CoefficientConfiance :

    @Override
    public void onCoefListOk(List<CoefficientConfiance> lesCoefs) {

        // creation d'une liste contenant les coefs
        List<String> coefNames = new ArrayList<>();
        for(CoefficientConfiance unCoef : lesCoefs)
        {
            coefNames.add(unCoef.getNum() + ". " + unCoef.getLibelle());
        }

        Spinner spinner = findViewById(R.id.add_screen_coefconfiance_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, coefNames);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onCoefListFailed() {
        Toast.makeText(getApplicationContext(), "Aucun coef", Toast.LENGTH_LONG).show();
    }


    // Création du rapport de visite :

    @Override
    public void onRapportCreateOk() {
        Toast.makeText(getApplicationContext(), "Rapport envoyé", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRapportCreateFailed() {
        Toast.makeText(getApplicationContext(), "Création du rapport impossible", Toast.LENGTH_LONG).show();
    }
}
