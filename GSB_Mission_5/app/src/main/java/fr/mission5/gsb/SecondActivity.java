package fr.mission5.gsb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import fr.mission5.gsb.technique.Session;

public class SecondActivity extends AppCompatActivity {

    private static Session session ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        session = Session.getSession();

        if (session != null) {
            String matricule = session.getLeVisiteur().getVisMatricule();
            // edtMatricule.setText(Session.getSession().getLeVisiteur().getVisMatricule());
            Log.i("Menu  ", "matricule : "+matricule);
            Toast.makeText(SecondActivity.this, "Matricule "+matricule, Toast.LENGTH_SHORT).show();

        } else {
            //edtMatricule.setText("Vide");
            Log.e("Menu : ", "Matricule erreur ");
            Toast.makeText(SecondActivity.this, "Matricule : vide", Toast.LENGTH_SHORT).show();
        }
    }

    public void seDeconnecter(View view) {
        Session.getSession().fermer();

        Intent retourConnexion = new Intent(this, MainActivity.class);
        startActivity(retourConnexion);
    }


}