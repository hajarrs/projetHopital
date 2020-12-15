package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Patient {
	
	@Id
	private int numeroSecu;
	private String nom;
	private String prenom;
	
	@Embedded
	private Adresse adresse;
	
	//@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER) si je veux à chaque fois récuperer la liste de visites avec le patient une fois on recupere le patient par selectById sinon faire findWithVisit()
	@OneToMany(mappedBy = "patient")
	private List<Visite> visite=new ArrayList();;
	
	public Patient(int numeroSecu, String nom, String prenom, Adresse adresse) {
		this.numeroSecu = numeroSecu;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
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
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public List<Visite> getVisite() {
		return visite;
	}
	public void setVisite(List<Visite> visite) {
		this.visite = visite;
	}
	
	

}
