package config;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dao.IDAOAdresse;
import dao.IDAOCompte;
import dao.IDAOMedecin;
import dao.IDAOPatient;
import dao.IDAOSecretaire;
import dao.IDAOVisite;
import dao.jpa.DAOAdresseJPA;
import dao.jpa.DAOCompteJPA;
import dao.jpa.DAOSecretaireJPA;
import dao.jpa.DAOVisiteJPA;
import dao.jpa.DAOMedecinJPA;
import dao.jpa.DAOPatientJPA;


public class Context {
	
	private static Context _instance;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hopital");


	private IDAOAdresse daoAdresse= new DAOAdresseJPA();
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

	public static Context get_instance() {
		return _instance;
	}

	public IDAOAdresse getDaoAdresse() {
		return daoAdresse;
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
}


