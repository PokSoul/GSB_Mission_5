package fr.mission5.gsb.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Praticien {

	@SerializedName("pra_num")
	@Expose
    public int pra_num;

	@SerializedName("pra_nom")
	@Expose
    public String pra_nom;

	@SerializedName("pra_prenom")
	@Expose
    public String pra_prenom;

	@SerializedName("pra_adresse")
	@Expose
    public String pra_adresse;

	@SerializedName("pra_cp")
	@Expose
    public String pra_cp;

	@SerializedName("pra_ville")
	@Expose
    public String pra_ville;

	@SerializedName("pra_coefNotoriete")
	@Expose
    public double pra_coefNotoriete;

	@SerializedName("pra_typeCode")
	@Expose
    public String pra_typeCode;

	@SerializedName("pra_visiteur")
	@Expose
    public String pra_visiteur;

    // Constructor
	public Praticien(int pra_num, String pra_nom, String pra_prenom, String pra_adresse, String pra_cp, String pra_ville, double pra_coefNotoriete, String pra_typeCode, String pra_visiteur) {
		this.pra_num 		   = pra_num;
		this.pra_nom 		   = pra_nom;
		this.pra_prenom 	   = pra_prenom;
		this.pra_adresse 	   = pra_adresse;
		this.pra_cp 		   = pra_cp;
		this.pra_ville 		   = pra_ville;
		this.pra_coefNotoriete = pra_coefNotoriete;
		this.pra_typeCode 	   = pra_typeCode;
		this.pra_visiteur 	   = pra_visiteur;
	}

	public Praticien(){}

	// Getters
	public int getNum() 			 { return pra_num; }
	public String getNom() 		 	 { return pra_nom; }
	public String getPrenom() 		 { return pra_prenom; }
	public String getAdresse() 		 { return pra_adresse; }
	public String getCp() 		 	 { return pra_cp; }
	public String getVille() 		 { return pra_ville; }
	public double getCoefNotoriete() { return pra_coefNotoriete; }
	public String getTypeCode() 	 { return pra_typeCode; }
	public String getVisiteur() 	 { return pra_visiteur; }

	// Setters
	public void setNum(int pra_num) 					   { this.pra_num = pra_num; }
	public void setNom(String pra_nom) 				   	   { this.pra_nom = pra_nom; }
	public void setPrenom(String pra_prenom) 			   { this.pra_prenom = pra_prenom; }
	public void setAdresse(String pra_adresse) 			   { this.pra_adresse = pra_adresse; }
	public void setCp(String pra_cp) 					   { this.pra_cp = pra_cp; }
	public void setVille(String pra_ville) 				   { this.pra_ville = pra_ville; }
	public void setCoefNotoriete(double pra_coefNotoriete) { this.pra_coefNotoriete = pra_coefNotoriete; }
	public void setTypeCode(String pra_typeCode) 		   { this.pra_typeCode = pra_typeCode; }
	public void setVisiteur(String pra_visiteur) 		   { this.pra_visiteur = pra_visiteur; }

}
