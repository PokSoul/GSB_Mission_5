package fr.mission5.gsb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import fr.mission5.gsb.metier.Visiteur;
import fr.mission5.gsb.technique.Session;

public class MainActivity extends AppCompatActivity {

    EditText et_Login;
    EditText et_Mot_De_Passe;

    Button bt_Valider;
    Button bt_Annuler;

    Visiteur visiteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Login = (EditText) findViewById(R.id.et_login);
        et_Mot_De_Passe = (EditText) findViewById(R.id.et_mot_de_passe);
        bt_Valider = (Button) findViewById(R.id.bt_valider);
        bt_Annuler = (Button) findViewById(R.id.bt_annuler);





    }

    public void bt_Valider (View vue) {
        String login = et_Login.getText().toString() ;
        String mdp = et_Mot_De_Passe.getText().toString();

        String url = "http://localhost:49297/api/visiteur/connexion/" + login + "/" + mdp;

        Log.i("APP_RV","Login:"+ login);
        Log.i("APP_RV","Mdp:"+ mdp);


        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("APP_RV","Visiteur : "+ response.toString());
                try {
                    String matricule = response.getString("visMatricule");
                    String nom = response.getString("visNom");
                    String prenom = response.getString("visPrenom");
                    Toast.makeText(MainActivity.this, "Connexion réussie : "
                            + matricule, Toast.LENGTH_LONG).show();
                    Log.i("APP_RV","Visiteur : "+ response.toString());
                    connecterVisiteur(matricule, nom, prenom);
                } catch (JSONException e) {
                    if (Session.getSession() != null) {
                        Session.getSession().fermer();
                    }
                    Toast.makeText(MainActivity.this, "Echec de connexion ",
                            Toast.LENGTH_LONG).show();
                    Log.e("APP-RV", "Erreur : " + e.getMessage());
                }
            }
        };

        Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("APP_RV", "Erreur JSON : " + error.getMessage());
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                responseListener, responseErrorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

        //System.out.println(visiteur.toString());
    }

    public void connecterVisiteur(String matricule, String nom, String prenom) {

        visiteur = new Visiteur();
        visiteur.setVisMatricule(matricule);
        visiteur.setVisNom(nom);
        visiteur.setVisPrenom(prenom);
        if ((Session.init(visiteur) == false )) {
            Log.e("Main activity","Erreur session");
        } else {
            Intent intentMenu = new Intent(this, SecondActivity.class);
            startActivity(intentMenu);
        }

    }

    public void bt_Annuler( View vue ){
        et_Login.setText("");
        et_Mot_De_Passe.setText("");
    }


}
