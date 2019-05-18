package fr.mission5.gsb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button confirmButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // je charge le confirm button
        confirmButtonView = findViewById(R.id.login_screen_confirm_button);
        confirmButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirige vers la page d'accueil apres connexion
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }
}
