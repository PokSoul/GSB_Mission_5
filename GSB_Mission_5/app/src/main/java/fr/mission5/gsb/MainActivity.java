package fr.mission5.gsb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText et_Login;
    EditText et_Mot_De_Passe;

    Button bt_Valider;
    Button bt_Annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Login = (EditText) findViewById(R.id.et_login);
        et_Mot_De_Passe = (EditText) findViewById(R.id.et_mot_de_passe);
        bt_Valider = (Button) findViewById(R.id.bt_valider);
        bt_Annuler = (Button) findViewById(R.id.bt_valider);




    }

    public void bt_Valider (View vue) {
        String nom = et_Login.getText().toString() ;
        String message = new String ("Authentification réussie "+ " "+ nom);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void bt_Annuler( View vue ){
        et_Login.setText("");
        et_Mot_De_Passe.setText("");
    }


}
