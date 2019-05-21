package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.mission5.gsb.R;

public class HomeActivity extends AppCompatActivity {

    // boutons
    private Button logoutButtonBiew;
    private Button addButtonView;
    private Button monthButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // initialisation des vues
        logoutButtonBiew = findViewById(R.id.home_screen_logout_button);
        logoutButtonBiew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirige vers la page d'avant en detruisant celle ci
                finish();
            }
        });

        addButtonView = findViewById(R.id.home_screen_add_button);
        addButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

        monthButtonView = findViewById(R.id.home_screen_month_button);
        monthButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MonthActivity.class));
            }
        });

    }
}
