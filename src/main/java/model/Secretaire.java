package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Secretaire")
public class Secretaire extends Compte{
	
	public Secretaire() {
		
	}

	public Secretaire(int id, String login, String password) {
		super(id, login, password);
		
	}
	
	
	

}
