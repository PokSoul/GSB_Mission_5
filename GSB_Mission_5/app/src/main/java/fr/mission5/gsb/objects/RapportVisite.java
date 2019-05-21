package fr.mission5.gsb.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class RapportVisite {

	@SerializedName("vis_matricule")
	@Expose
    public String vis_matricule;

	@SerializedName("rap_num")
	@Expose
    public int rap_num;

	@SerializedName("pra_num")
	@Expose
    public int pra_num;

	@SerializedName("coef_num")
	@Expose
    public int coef_num;

	@SerializedName("rap_motif")
	@Expose
    public String rap_motif;

	@SerializedName("rap_bilan")
	@Expose
    public String rap_bilan;

	@SerializedName("rap_dateVisite")
	@Expose
    public String rap_dateVisite;

	@SerializedName("rap_dateRapport")
	@Expose
    public String rap_dateRapport;

	@SerializedName("rap_moisRapport")
	@Expose
	public Timestamp rap_moisRapport;

    // Constructor
	public RapportVisite(String vis_matricule, int pra_num, int coef_num, String rap_motif, String rap_bilan, String rap_dateVisite) {

		this.vis_matricule 	 = vis_matricule;
		this.pra_num 		 = pra_num;
		this.coef_num 		 = coef_num;
		this.rap_motif 		 = rap_motif;
		this.rap_bilan 		 = rap_bilan;
		this.rap_dateVisite  = rap_dateVisite;
	}

	// Getters
	public String getVisMatricule()	 { return vis_matricule; }
	public int getNum() 	  		 { return rap_num; }
	public int getPraNum() 	  		 { return pra_num; }
	public int getCoefNum() 	  	 { return coef_num; }
	public String getMotif()   		 { return rap_motif; }
	public String getBilan()   		 { return rap_bilan; }
	public String getDateVisite()  { return rap_dateVisite; }
	public String getDateRapport() { return rap_dateRapport; }

	// Setters
	public void setVisMatricule(String vis_matricule) 	 { this.vis_matricule = vis_matricule; }
	public void setNum(int rap_num) 				  	 { this.rap_num = rap_num; }
	public void setPraNum(int pra_num) 				  	 { this.pra_num = pra_num; }
	public void setCoefNum(int coef_num) 			  	 { this.coef_num = coef_num; }
	public void setMotif(String rap_motif) 			  	 { this.rap_motif = rap_motif; }
	public void setBilan(String rap_bilan) 			  	 { this.rap_bilan = rap_bilan; }
	public void setDateVisite(String rap_dateVisite) 	 { this.rap_dateVisite = rap_dateVisite; }
	public void setDateRapport(String rap_dateRapport) { this.rap_dateRapport = rap_dateRapport; }

}
