package fr.mission5.gsb.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mois {

    @SerializedName("rap_moisRapport")
    @Expose
    private String rap_moisRapport;

    public String getRap_moisRapport(){
        return rap_moisRapport;
    }

    public void setRap_moisRapport(String rap_moisRapport) {
        this.rap_moisRapport = rap_moisRapport;
    }

}
