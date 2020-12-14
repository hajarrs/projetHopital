package model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Patient {
	
	@Id
	private int numeroSecu;
	private String nom;
	private String prenom;
	
	@Embedded
	private Adresse adresse;
	
	
	public Patient(int numeroSecu, String nom, String prenom) {
		super();
		this.numeroSecu = numeroSecu;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Patient() {
		
	}
	public int getNumeroSecu() {
		return numeroSecu;
	}
	public void setNumeroSecu(int numeroSecu) {
		this.numeroSecu = numeroSecu;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	

}
