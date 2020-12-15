package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Medecin")
public class Medecin extends Compte{
	
	private int salle;
	@OneToMany(mappedBy = "medecin",fetch = FetchType.EAGER)
	private List<Visite> visite = new ArrayList();

	public Medecin() {
		
		
	}

	public Medecin(String login, String password) {
		super(login, password);
		
	}

	public List<Visite> getVisite() {
		return visite;
	}

	public void setVisite(List<Visite> visite) {
		this.visite = visite;
	}


	public void setSalle(int salle) {
		this.salle = salle;
	}
	public int getSalle() {
		return salle;
	}
	

}
