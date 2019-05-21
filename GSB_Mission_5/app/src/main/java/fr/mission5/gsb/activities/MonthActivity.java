package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.mission5.gsb.R;
import fr.mission5.gsb.callbacks.MoisListCallback;
import fr.mission5.gsb.objects.Mois;
import fr.mission5.gsb.objects.Visiteur;
import fr.mission5.gsb.persistence.DatabaseManager;

public class MonthActivity extends AppCompatActivity implements MoisListCallback {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private MonthActivity context = this;

    private Button confirmButtonView;
    private Button backButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        confirmButtonView = findViewById(R.id.month_screen_confirm_button);
        confirmButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get date rapport
                Spinner dateRapportSpinner = findViewById(R.id.month_screen_daterapport_spinner);
                String dateRapportText = dateRapportSpinner.getSelectedItem().toString();
                databaseManager.setDateRapport(dateRapportText);

                // start activity
                startActivity(new Intent(getApplicationContext(), ShowAllActivity.class));
            }
        });

        backButtonView = findViewById(R.id.month_screen_back_button);
        backButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadMonthsList();

    }

    public void loadMonthsList() {

        Visiteur visiteurSession = databaseManager.getVisiteurSession();

        databaseManager.getRapportVisiteDatesByVisMatricule(visiteurSession.getMatricule(), context);

    }


    @Override
    public void onGetMois(List<Mois> mois) {
        // creation d'une liste contenant les noms de mois / periode
        List<String> monthsPeriods = new ArrayList<>();
        for(Mois mois_u : mois)
        {
            monthsPeriods.add(mois_u.getRap_moisRapport());
        }

        Spinner spinner = findViewById(R.id.month_screen_daterapport_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthsPeriods);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onFailed() {
        Toast.makeText(getApplicationContext(), "Aucun mois", Toast.LENGTH_LONG).show();
    }
}
