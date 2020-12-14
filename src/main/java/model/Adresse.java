package model;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	
	private int numero;
	private String voie;
	private String ville;
	private int cp;
	
	
	public Adresse(int numero, String voie, String ville, int cp) {
		//super();
		this.numero = numero;
		this.voie = voie;
		this.ville = ville;
		this.cp = cp;
	}
	public Adresse() {
		
	}
	
	

}
