package fr.mission5.gsb.persistence;

import android.util.Log;
import java.sql.Timestamp;
import java.util.List;

import fr.mission5.gsb.callbacks.CoefListCallback;
import fr.mission5.gsb.callbacks.LoginCallback;
import fr.mission5.gsb.callbacks.MoisListCallback;
import fr.mission5.gsb.callbacks.PraticienListCallback;
import fr.mission5.gsb.callbacks.RapportCreateCallback;
import fr.mission5.gsb.callbacks.RapportListCallback;
import fr.mission5.gsb.objects.CoefficientConfiance;
import fr.mission5.gsb.objects.Mois;
import fr.mission5.gsb.objects.Praticien;
import fr.mission5.gsb.objects.RapportVisite;
import fr.mission5.gsb.objects.Version;
import fr.mission5.gsb.objects.Visiteur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatabaseManager {

    private static final String END_POINT = "http://213.32.120.28:62/";
    private static final String TAG = "DatabaseManager";
    private Visiteur visiteurSession;
    private Services service;
    private String dateRapport;
    private static DatabaseManager INSTANCE;

    // Méthode Singleton
    public static DatabaseManager getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DatabaseManager();
        }
        return INSTANCE;
    }

    // Constructeur Singleton
    public DatabaseManager() {
        // Chargement des services avec la fabrique (Retrofit)
        this.service = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Services.class);

        // Notification de succès
        Log.d(TAG, "Service loaded.");
    }

    /**
     * Méthode permettant de vérifier si les services sont chargées
     */
    public void isLoaded() {
        service.getVersion().enqueue(new Callback<Version>() {

            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {

                Version version = response.body();

                if(version != null && !version.isError())
                {
                    Log.d(TAG, "API loaded.");
                }
                else
                {
                    Log.d(TAG, "API error.");
                }

            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {
                Log.d(TAG, "API error.");
            }

        });
    }

    //=================================//
    // SERVICES DE LA TABLE "Visiteur" //
    //=================================//

    /**
     * Retourne le visiteur dont l'identifiant et le mot de passe passés en paramètres
     * @param vis_login
     * @param vis_mdp
     */
    public void connexionVisiteur(String vis_login, String vis_mdp, final LoginCallback callback) {

        // Création d'un visiteur
        Visiteur visiteur = new Visiteur();
        visiteur.setLogin(vis_login);
        visiteur.setMdp(vis_mdp);

        // Requête de connexion
        service.connexionVisiteur(visiteur).enqueue(new Callback<Visiteur>() {

            @Override
            public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {

                visiteurSession = response.body();

                if(visiteurSession.getMatricule() != null)
                {
                    Log.d(TAG, "Connexion OK ! matricule " + visiteurSession.getMatricule());
                    callback.onLoginOk();
                }
                else
                {
                    Log.d(TAG, "Couple Identifiant/Mot de passe incorrecte ");
                    callback.onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                Log.d(TAG, "Erreur de login ! " + t.getLocalizedMessage());
                callback.onLoginFailed();
            }

        });
    }

    /**
     * Méthode permettant d'obtenir la session du visiteur
     * @return
     */
    public Visiteur getVisiteurSession(){
        return visiteurSession;
    }

    //==================================//
    // SERVICES DE LA TABLE "Praticien" //
    //==================================//

    /**
     * Retourne le praticien dont le numéro est passé en paramètre
     * @param pra_num
     */
    public void getPraticienByNum(final int pra_num) {

        // Création d'un praticien
        Praticien praticien = new Praticien();
        praticien.setNum(pra_num);

        // Requête
        service.getPraticienByNum(pra_num).enqueue(new Callback<Praticien>() {

            @Override
            public void onResponse(Call<Praticien> call, Response<Praticien> response) {

                Praticien praticienSession = response.body();

                if(praticienSession.getNom() != null)
                {
                    Log.d(TAG, "Praticien récuperé : " + praticienSession.getNom() + " " + praticienSession.getPrenom());
                }
                else
                {
                    Log.d(TAG, "Aucun praticien avec ce numero");
                }

            }

            @Override
            public void onFailure(Call<Praticien> call, Throwable t) {
                Log.d(TAG, "Erreur " + t.getLocalizedMessage());
            }

        });
    }

    /**
     * Retoure les praticiens associés à un visiteur dont le matricule est passé en paramètre
     * @param vis_matricule
     */
    public void getPraticienByVisMatricule(final String vis_matricule, final PraticienListCallback callback) {

        // Création d'un praticien
        Praticien praticien = new Praticien();
        praticien.setVisiteur(vis_matricule);

        // Requête
        service.getPraticienByVisMatricule(vis_matricule).enqueue(new Callback<List<Praticien>>() {

            @Override
            public void onResponse(Call<List<Praticien>> call, Response<List<Praticien>> response) {
                List<Praticien> praticienSessions = response.body();

                if(praticienSessions.size() == 0)
                {
                    callback.onFailed();
                }

                else
                {
                    callback.onGetList(praticienSessions);
                }

            }

            @Override
            public void onFailure(Call<List<Praticien>> call, Throwable t) {
                Log.d(TAG, "Erreur " + t.getLocalizedMessage());
                callback.onFailed();
            }

        });
    }

    //======================================//
    // SERVICES DE LA TABLE "RapportVisite" //
    //======================================//

    /**
     * Retourne toutes les dates (AAAA-MM) des rapports de visite d'un visiteur dont le matricule
     * est passé en paramètre
     * @param vis_matricule
     */
    public void getRapportVisiteDatesByVisMatricule(final String vis_matricule, final MoisListCallback callback) {

        service.getRapportVisiteDatesByVisMatricule(vis_matricule).enqueue(new Callback<List<Mois>>() {

            @Override
            public void onResponse(Call<List<Mois>> call, Response<List<Mois>> response) {
                List<Mois> mois = response.body();

                if(mois.size() == 0)
                {
                    callback.onFailed();
                }

                else
                {
                    callback.onGetMois(mois);
                }

            }

            @Override
            public void onFailure(Call<List<Mois>> call, Throwable t) {
                Log.d(TAG, "Erreur " + t.getMessage());
                callback.onFailed();
            }

        });

    }

    /**
     * Retourne tous les rapports de visite d'un visiteur à une date (AAAA-MM) dont le matricule de
     * visiteur et la date de rapport sont passés en paramètres
     * @param vis_matricule
     * @param rap_dateRapport
     */
    public void getRapportVisiteByVisMatriculeAndDate(final String vis_matricule, final String rap_dateRapport, final RapportListCallback callback) {

        // Requête
        service.getRapportVisiteByVisMatriculeAndDate(vis_matricule, rap_dateRapport).enqueue(new Callback<List<RapportVisite>>() {

            @Override
            public void onResponse(Call<List<RapportVisite>> call, Response<List<RapportVisite>> response) {

                List<RapportVisite> rapportVisites = response.body();

                if(rapportVisites.size() != 0)
                {
                    callback.onGet(rapportVisites);
                }
            }

            @Override
            public void onFailure(Call<List<RapportVisite>> call, Throwable t) {
                Log.d(TAG, "Erreur");
            }

        });
    }

    /**
     * Retourne le rapports de visites dont le numéro et le matricule de visiteur sont passés en
     * paramètres
     * @param rap_num
     * @param vis_matricule
     */
    public void getRapportVisiteByNumAndVisMatricule(final int rap_num, final String vis_matricule) {

        // Requête
        service.getRapportVisiteByNumAndVisMatricule(rap_num, vis_matricule).enqueue(new Callback<RapportVisite>() {

            @Override
            public void onResponse(Call<RapportVisite> call, Response<RapportVisite> response) {

                RapportVisite rapportVisite = response.body();

                if(rapportVisite.rap_motif != null)
                {
                    Log.d(TAG, "Rapport visité récuperé : " + rapportVisite.rap_motif + " à la date du " + rapportVisite.getDateVisite());
                }
                else
                {
                    Log.d(TAG, "Aucun rapport pour ce matricule");
                }

            }

            @Override
            public void onFailure(Call<RapportVisite> call, Throwable t) {
                Log.d(TAG, "Erreur");
            }

        });
    }

    /**
     * Ajoute dans la base de données un nouveau rapport de visite dont les attributs sont passés en paramètres
     * @param vis_matricule
     * @param pra_num
     * @param coef_num
     * @param rap_motif
     * @param rap_bilan
     * @param rap_dateVisite
     */
    public void createRapportVisite(String vis_matricule, int pra_num, int coef_num, String rap_motif, String rap_bilan, String rap_dateVisite, final RapportCreateCallback callback){

        // Création du rapport de visite
        RapportVisite rapportVisite = new RapportVisite(vis_matricule, pra_num, coef_num, rap_motif, rap_bilan, rap_dateVisite);

        // Requête
        service.createRapportVisite(rapportVisite).enqueue(new Callback<RapportVisite>() {

            @Override
            public void onResponse(Call<RapportVisite> call, Response<RapportVisite> response) {
                Log.d(TAG, "Rapport envoyé !");
                callback.onSend();
            }

            @Override
            public void onFailure(Call<RapportVisite> call, Throwable t) {
                Log.d(TAG, "Erreur lors de l'envoi du rapport");
                callback.onFailedSend();
            }

        });
    }

    //=============================================//
    // SERVICES DE LA TABLE "CoefficientConfiance" //
    //=============================================//

    /**
     * Retourne tous les coefficients de confiance
     */
    public void getCoefficientConfiance(final CoefListCallback callback) {

        // Requête
        service.getCoefficientConfiance().enqueue(new Callback<List<CoefficientConfiance>>() {

            @Override
            public void onResponse(Call<List<CoefficientConfiance>> call, Response<List<CoefficientConfiance>> response) {

                List<CoefficientConfiance> coefficientConfiances = response.body();

                if(coefficientConfiances == null || coefficientConfiances.isEmpty())
                {
                   callback.onFailedCoef();
                }
                else
                {
                    callback.onGetListCoef(coefficientConfiances);
                }

            }

            @Override
            public void onFailure(Call<List<CoefficientConfiance>> call, Throwable t) {
                Log.d(TAG, "Erreur");
                callback.onFailedCoef();
            }

        });
    }

    /**
     * Retourne le coefficient de confiance dont le numéro est passé en paramètre
     * @param coef_num
     */
    public void getCoefficientConfianceByNum(int coef_num) {

        // Requête
        service.getCoefficientConfianceByNum(coef_num).enqueue(new Callback<CoefficientConfiance>() {

            @Override
            public void onResponse(Call<CoefficientConfiance> call, Response<CoefficientConfiance> response) {

                CoefficientConfiance coefficientConfiance = response.body();
                Log.d(TAG, "CoefficientConfiance n°" + coefficientConfiance.getNum() + " récupéré " + coefficientConfiance.getLibelle());

            }

            @Override
            public void onFailure(Call<CoefficientConfiance> call, Throwable t) {
                Log.d(TAG, "Erreur " + t.getMessage());
            }

        });
    }

    public void setDateRapport(String dateRapport){ this.dateRapport = dateRapport; }

    public String getDateRapport() {  return dateRapport; }
}
