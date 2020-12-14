package model;

public class Patient {
	
	private int numeroSecu;
	private String nom;
	private String prenom;
	
	
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
