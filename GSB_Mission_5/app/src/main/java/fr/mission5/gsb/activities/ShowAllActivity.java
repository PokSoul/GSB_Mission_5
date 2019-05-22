package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.mission5.gsb.R;
import fr.mission5.gsb.RapportVisiteAdapter;
import fr.mission5.gsb.callbacks.RapportListCallback;
import fr.mission5.gsb.objects.RapportVisite;
import fr.mission5.gsb.objects.Visiteur;
import fr.mission5.gsb.persistence.DatabaseManager;

public class ShowAllActivity extends AppCompatActivity implements RapportListCallback {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private ShowAllActivity context = this;

    private Button detailButtonView;
    private Button backButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        detailButtonView = findViewById(R.id.show_all_screen_detail_button);
        detailButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowActivity.class));
            }
        });

        backButtonView = findViewById(R.id.show_all_screen_back_button);
        backButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadRapportsVisite();
    }

    /*=========================*
     * Chargement des éléments *
     *=========================*/

    public void loadRapportsVisite() {

        Visiteur visiteurSession = databaseManager.getVisiteurSession();
        String dateRapport = databaseManager.getDateRapport();

        databaseManager.getRapportVisiteByVisMatriculeAndDate(visiteurSession.getMatricule(), dateRapport, context);
    }

    /*===========*
     * Callbacks *
     *===========*/

    @Override
    public void onRapportListOk(List<RapportVisite> rapports) {

        // Récupère la portion de l'écran où va être injecté la liste des rapports
        RecyclerView recyclerView = findViewById(R.id.recycler);

        // Injection de l'adapter qui organise l'ensemble des rapports
        recyclerView.setAdapter(new RapportVisiteAdapter(rapports));

        // Positionne les éléments de façon linéaire
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }
}
