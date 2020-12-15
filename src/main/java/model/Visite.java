package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Visite")
public class Visite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int cout;
	private int numeroSalle;
	private LocalDate dateVisite;
	@ManyToOne
	private Medecin medecin;
	@ManyToOne
	@JoinColumn(name="numSecu")
	private Patient patient;
	
	
	
	public Visite(int cout, int numeroSalle, LocalDate dateVisite, Patient patient) {
		this.patient = patient;
		this.cout = cout;
		this.numeroSalle = numeroSalle;
		this.dateVisite = dateVisite;
	}
	public Visite() {
		
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
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
	
	
	

