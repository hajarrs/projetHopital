package config;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class Context {
	
	private static Context _instance;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetHopital");


	private IDAOSecretaire daoSecretaire = new DAOSecretaireJPA();
	private IDAOMedecin daoMedecin = new DAOMedecinJPA();
	private IDAOPatient daoPatient = new DAOPatientJPA();
	private IDAOVisite daoVisite = new DAOVisiteJPA();
	
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

	public IDAOSecretaire getDaoSecretaire() {
		return daoSecretaire;
	}

	public IDAOMedecin getDaoMedecin() {
		return daoMedecin;
	}

	public IDAOPatient getDaoPatient() {
		return daoPatient;
	}

	public IDAOVisite getDaoVisite() {
		return daoVisite;
	}





	
}
