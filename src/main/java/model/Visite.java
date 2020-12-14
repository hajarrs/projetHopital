package model;

import java.time.LocalDate;

public class Visite {
	
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
	
	
	

