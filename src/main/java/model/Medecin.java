package model;

public class Medecin extends Compte{
	
	private int salle;
	
	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public Medecin() {
		
		
		
	}

	public Medecin(int id, String login, String password) {
		super(id, login, password);
		
	}

}