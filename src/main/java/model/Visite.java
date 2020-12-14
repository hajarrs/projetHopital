package model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Medecin med;
	private Patient pat;
	
	
	public Visite(int id, double cout, int numeroSalle, LocalDate dateVisite) {
		//super();
		this.id = id;
		this.cout = cout;
		this.numeroSalle = numeroSalle;
		this.dateVisite = dateVisite;
	}
	public Visite() {
		
	}
	
	}
	
	
	

