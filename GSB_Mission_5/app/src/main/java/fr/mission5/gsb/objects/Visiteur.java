
package fr.mission5.gsb.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visiteur {

	@SerializedName("vis_matricule")
	@Expose
	public String vis_matricule;

	@SerializedName("vis_nom")
	@Expose
	public String vis_nom;

	@SerializedName("vis_prenom")
	@Expose
	public String vis_prenom;

	@SerializedName("vis_adresse")
	@Expose
	public String vis_adresse;

	@SerializedName("vis_cp")
	@Expose
	public String vis_cp;

	@SerializedName("vis_ville")
	@Expose
	public String vis_ville;

	@SerializedName("vis_login")
	@Expose
	public String vis_login;

	@SerializedName("vis_mdp")
	@Expose
	public String vis_mdp;
	
	// Constructor
	public Visiteur(String vis_matricule,  String vis_nom, String vis_prenom, String vis_adresse, String vis_cp, String vis_ville, String vis_login, String vis_mdp) {
		this.vis_matricule = vis_matricule;
        this.vis_nom 	    = vis_nom;
        this.vis_prenom    = vis_prenom;
        this.vis_adresse   = vis_adresse;
        this.vis_cp 	    = vis_cp;
        this.vis_ville 	    = vis_ville;
        this.vis_login 	    = vis_login;
		this.vis_mdp 	    = vis_mdp;
	}

	public Visiteur(){}

	// Getters
	public String getMatricule() { return vis_matricule; }
	public String getNom() 		 { return vis_nom; }
	public String getPrenom() 	 { return vis_prenom; }
	public String getAdresse() 	 { return vis_adresse; }
	public String getCp() 	     { return vis_cp; }
	public String getVille()	 { return vis_ville; }
	public String getLogin() 	 { return vis_login; }
	public String getMdp() 		 { return vis_mdp; }

	// Setters
	public void setMatricule(String vis_matricule) { this.vis_matricule = vis_matricule; }
	public void setNom(String vis_nom) 			   { this.vis_nom = vis_nom; }
	public void setPrenom(String vis_prenom) 	   { this.vis_prenom = vis_prenom; }
	public void setAdresse(String vis_adresse) 	   { this.vis_adresse = vis_adresse; }
	public void setCp(String vis_cp) 			   { this.vis_cp = vis_cp; }
	public void setVille(String vis_ville) 		   { this.vis_ville = vis_ville; }
    public void setLogin(String vis_login) 		   { this.vis_login = vis_login; }
	public void setMdp(String vis_mdp) 			   { this.vis_mdp = vis_mdp; }

}
