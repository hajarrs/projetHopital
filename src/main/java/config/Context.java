package config;


import java.util.LinkedList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAOCompte;
import dao.IDAOMedecin;
import dao.IDAOPatient;
import dao.IDAOSecretaire;
import dao.IDAOVisite;
import dao.jpa.DAOCompteJPA;
import dao.jpa.DAOMedecinJPA;
import dao.jpa.DAOPatientJPA;
import dao.jpa.DAOSecretaireJPA;
import dao.jpa.DAOVisiteJPA;
import model.Patient;


public class Context {
	
	private static Context _instance;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetHopital");

	private LinkedList<Patient> fileAttente = new LinkedList<>();

	private IDAOCompte daoCompte = new DAOCompteJPA();
	private IDAOMedecin daoMedecin = new DAOMedecinJPA();
	private IDAOSecretaire daoSecretaire = new DAOSecretaireJPA();
	private IDAOPatient daoPatient = new DAOPatientJPA();
	private IDAOVisite daoVisite= new DAOVisiteJPA();

	
	private Context() {}
	
	public static Context getInstance()
	{
		if(_instance==null) 
		{
			_instance=new Context();
		}
		return _instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void closeEmf() 
	{
		emf.close();
	}


	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}

	public IDAOMedecin getDaoMedecin() {
		return daoMedecin;
	}

	public IDAOSecretaire getDaoSecretaire() {
		return daoSecretaire;
	}

	public IDAOPatient getDaoPatient() {
		return daoPatient;
	}

	public IDAOVisite getDaoVisite() {
		return daoVisite;
	}

	public LinkedList<Patient> getFileAttente() {
		return fileAttente;
	}

	public void setFileAttente(LinkedList<Patient> fileAttente) {
		this.fileAttente = fileAttente;
	}
	
}


