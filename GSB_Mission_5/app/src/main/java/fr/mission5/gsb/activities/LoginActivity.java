package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.mission5.gsb.R;
import fr.mission5.gsb.persistence.DatabaseManager;

public class LoginActivity extends AppCompatActivity {

    // base de données (dao)
    private DatabaseManager databaseManager = DatabaseManager.getInstance();

    // composants
    private Button confirmButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Je charge le confirm button
        confirmButtonView = findViewById(R.id.login_screen_confirm_button);
        confirmButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige vers la page d'accueil après connexion
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });


        // verif si les services sont allumés
        databaseManager.isLoaded();

    }
}
