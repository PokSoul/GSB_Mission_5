package fr.mission5.gsb.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoefficientConfiance {

	@SerializedName("coef_num")
	@Expose
	private int coef_num;

	@SerializedName("coef_libelle")
	@Expose
    private String coef_libelle;
	
	// Constructor
	public CoefficientConfiance(int coef_num, String coef_libelle) {
		this.coef_num 	  = coef_num;
        this.coef_libelle = coef_libelle;;
	}

	// Getters
	public int getNum()    	   { return coef_num; }
	public String getLibelle() { return coef_libelle; }

	// Setters
	public void setNum(int coef_num) 			{ this.coef_num = coef_num; }
	public void setLibelle(String coef_libelle) { this.coef_libelle = coef_libelle; }

}
