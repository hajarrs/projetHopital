package model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Visite")
public class Visite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double cout;
	private int numeroSalle;
	private LocalDate dateVisite;
	@ManyToOne
	private Medecin medecin;
	@ManyToOne
	private Patient patient;
	
	
	
	public Visite(int id, double cout, int numeroSalle, LocalDate dateVisite) {
		//super();
		this.id = id;
		this.cout = cout;
		this.numeroSalle = numeroSalle;
		this.dateVisite = dateVisite;
	}
	public Visite() {
		
	}
	public double getCout() {
		return cout;
	}
	public void setCout(double cout) {
		this.cout = cout;
	}
	public int getNumeroSalle() {
		return numeroSalle;
	}
	public void setNumeroSalle(int numeroSalle) {
		this.numeroSalle = numeroSalle;
	}
	public LocalDate getDateVisite() {
		return dateVisite;
	}
	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	
	
	
	}
	
	
	

