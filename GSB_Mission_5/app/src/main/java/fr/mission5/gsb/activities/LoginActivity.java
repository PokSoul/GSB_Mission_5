package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import fr.mission5.gsb.R;
import fr.mission5.gsb.callbacks.LoginCallback;
import fr.mission5.gsb.persistence.DatabaseManager;

public class LoginActivity extends AppCompatActivity implements LoginCallback{

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private LoginActivity context = this;
    private EditText loginScreenIdInput, loginScreenPasswordInput;
    private Button confirmButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Chargement des deux champs input (id et password)
        loginScreenIdInput = findViewById(R.id.login_screen_id_input);
        loginScreenPasswordInput = findViewById(R.id.login_screen_password_input);
        loginScreenPasswordInput.setTransformationMethod(new PasswordTransformationMethod());

        // Chargement du bouton valider
        confirmButtonView = findViewById(R.id.login_screen_confirm_button);
        confirmButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Vérification si les champs sont remplis
                if(!loginScreenIdInput.getText().toString().isEmpty() && !loginScreenPasswordInput.getText().toString().isEmpty())
                {
                    // Tentative de connexion
                    databaseManager.connexionVisiteur(loginScreenIdInput.getText().toString(), loginScreenPasswordInput.getText().toString(), context);
                }

            }
        });

        // Vérification du bon fonctionnement des services
        databaseManager.isLoaded();

    }

    @Override
    public void onLoginOk() {
        // Redirige vers la page d'accueil après connexion
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

        // Alerter l'utilisateur de la bonne connexion
        Toast.makeText(getApplicationContext(), "Bienvenue", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(getApplicationContext(), "Identifiant / Mot de passe incorrect", Toast.LENGTH_SHORT).show();
    }

}
