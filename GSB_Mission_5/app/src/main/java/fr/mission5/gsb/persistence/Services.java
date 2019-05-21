package fr.mission5.gsb.persistence;

import java.util.List;

import fr.mission5.gsb.objects.CoefficientConfiance;
import fr.mission5.gsb.objects.Praticien;
import fr.mission5.gsb.objects.RapportVisite;
import fr.mission5.gsb.objects.Version;
import fr.mission5.gsb.objects.Visiteur;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Services {

    @GET("/version")
    Call<Version> getVersion();

    //=================================//
    // SERVICES DE LA TABLE "Visiteur" //
    //=================================//

    @POST("/visiteur/connexion")
    Call<Visiteur> connexionVisiteur(@Body Visiteur visiteur);

    //==================================//
    // SERVICES DE LA TABLE "Praticien" //
    //==================================//

    @GET("/praticien/{pra_num}")
    Call<Praticien> getPraticienByNum(@Path("pra_num") int pra_num);

    @GET("/praticien/visiteur/{vis_matricule}")
    Call<Praticien> getPraticienByVisMatricule(@Path("vis_matricule") String vis_matricule);

    //======================================//
    // SERVICES DE LA TABLE "RapportVisite" //
    //======================================//

    @GET("/rapportvisite/visiteur/{vis_matricule}/daterapport")
    Call<RapportVisite> getRapportVisiteDatesByVisMatricule(@Path("vis_matricule") String vis_matricule);

    @GET("/rapportvisite/visiteur/{vis_matricule}/daterapport/{rap_dateRapport}/")
    Call<RapportVisite> getRapportVisiteByVisMatriculeAndDate(@Path("vis_matricule") String vis_matricule, @Path("rap_dateRapport") String rap_dateRapport);

    @GET("/rapportvisite/{rap_num}/visiteur/{vis_matricule}/")
    Call<RapportVisite> getRapportVisiteByNumAndVisMatricule(@Path("rap_num") int rap_num, @Path("vis_matricule") String vis_matricule);

    @POST("/rapportvisite/saisie")
    Call<RapportVisite> createRapportVisite(@Body RapportVisite rapportVisite);

    //=============================================//
    // SERVICES DE LA TABLE "CoefficientConfiance" //
    //=============================================//

    @GET("/coefficientconfiance")
    Call<List<CoefficientConfiance>> getCoefficientConfiance();

    @GET("/coefficientconfiance/{coef_num}")
    Call<CoefficientConfiance> getCoefficientConfianceByNum(@Path("coef_num") int coef_num);

}
