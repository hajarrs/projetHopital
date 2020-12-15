package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import config.Context;
import dao.jpa.DAOCompteJPA;
import model.Adresse;
import model.Compte;
import model.Medecin;
import model.Patient;
import model.Secretaire;
import model.Visite;

public class Test{

	static Compte connected=null;
	
	public static void baseHopital() {

		Medecin m1 = new Medecin("magali1", "123456");
		m1.setSalle(1);
		Medecin m2 = new Medecin("magali2", "123456");
		m2.setSalle(2);
		Secretaire s = new Secretaire("hajars", "hajar1");

		Adresse a1 = new Adresse(1, "Chemin des monts", "Marseille", 13008);
		Adresse a2= new Adresse(14, "Chemin Jean Claire", "Caen", 14000);
		Patient p1 = new Patient(12, "Ram", "Sara",a1);
		Patient p2 = new Patient(13, "Qai", "Laila",a2);
		
		Visite v1 = new Visite(20, 1, LocalDate.parse("2020-06-15"),m1, p1);
		Visite v2 = new Visite(20, 2, LocalDate.parse("2020-07-15"), m2,p1);
		Visite v3 = new Visite(20, 2, LocalDate.parse("2020-06-15"), m2,p2);
		Visite v4 = new Visite(20, 1, LocalDate.parse("2020-08-15"),m1, p2);
		
		
		Context.getInstance().getDaoCompte().insert(m1);
		Context.getInstance().getDaoCompte().insert(m2);
		Context.getInstance().getDaoSecretaire().insert(s);
		Context.getInstance().getDaoPatient().insert(p1);
		Context.getInstance().getDaoPatient().insert(p2);
		Context.getInstance().getDaoVisite().insert(v1);
		Context.getInstance().getDaoVisite().insert(v3);
		Context.getInstance().getDaoVisite().insert(v2);
		Context.getInstance().getDaoVisite().insert(v4);
		
		Context.getInstance().getFileAttente().add(p1);
		Context.getInstance().getFileAttente().add(p2);
	}
	
	public static int saisieInt(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public static double saisieDouble(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextDouble();
	}


	public static String saisieString(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	
	private static void menuGlobal() {
		System.out.println("------------------------------------------------");
		System.out.println("Bienvenue dans l'application de l'hopital\nSe connecter:\n");
		String login= saisieString("Login:");
		String password=saisieString("Password:");
		try{
			connected=DAOCompteJPA.checkConnect(login,password); 
			System.out.println(connected);
		}catch(Exception e) {
			System.out.println("Mauvais identifiants");
			}

			if(connected instanceof Medecin) 
			{
				menuMedecin();

			}
			else if(connected instanceof Secretaire) 
			{
				System.out.println("Connectee");
				File monFichier = new File("file_Attente.rtf");
				
				try(FileInputStream fis = new FileInputStream(monFichier);
					ObjectInputStream ois = new ObjectInputStream(fis);) {
					Context.getInstance().setFileAttente((LinkedList<Patient>) ois.readObject());
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				menuSecretaire();

			}
			else 
			{
				menuGlobal();
			}

	}

	private static void menuMedecin() {
		System.out.println("------------------------------------------------");
		
		System.out.println("Bienvenu dans le menu Medecin");
		int choix = saisieInt("Veuillez faire un choix:\n1 - Rendre la salle disponible\n2 - Visualiser la salle d'attente"
				+ "\n3 - Sauvegarder la liste de visites\n4 - Quitter\n5 - Se deconnecter");
		
		switch(choix) {
		case 1 : rendreSalleDispo(); break;
		case 2 : etatFileAttente(); break;
		case 3 : sauvegarderListe();break;
		case 4 : menuGlobal();break;
		case 5 : System.exit(0); break;
		}
		menuMedecin();
	}
	private static void afficherVisite() {
		for(Visite v1 : Context.getInstance().getDaoMedecin().findById(connected.getId()).getVisite()) {
			System.out.println(v1.getPatient().getNumeroSecu()+"\t"+v1.getPatient().getNom()+"\t"+v1.getPatient().getPrenom());
		}
		
	}

	//les methodes utilisees
	private static void rendreSalleDispo() {
		System.out.println("------------------------------------------------");
		Patient p = Context.getInstance().getFileAttente().peek();
		System.out.println("La salle N°"+Context.getInstance().getDaoMedecin().findById(connected.getId()).getSalle()+" est disponible");
		System.out.println("Prochain patient: \nNom: "+p.getNom()+"\nPrenom: "+p.getPrenom());
	}

	private static void sauvegarderListe() {
		System.out.println("------------------------------------------------");
		Patient p = Context.getInstance().getFileAttente().poll();
		Visite v = new Visite(20, Context.getInstance().getDaoMedecin().findById(connected.getId()).getSalle(), LocalDate.now(), Context.getInstance().getDaoMedecin().findById(connected.getId()), p);
		Context.getInstance().getDaoMedecin().findById(connected.getId()).getVisite().add(v);

		System.out.println("Patient Ajoute");
	}

	private static void menuSecretaire() {
		System.out.println("------------------------------------------------");
		System.out.println("Bienvenu dans le menu Secretaire");
		int choix = saisieInt("Veuillez faire un choix:\n1 - Ajouter un patient\n2 - Afficher l'etat de la file"
				+ " d'attente\n3 - Retourner la liste de l'historique de visites\n4 - Partir en Pause\n5 - Quitter\n6 - Se deconnecter");
		
		switch(choix) {
		case 1 : ajouterPatient();break;
		case 2 : etatFileAttente();break;
		case 3 : listeHistorique();break;
		case 4 : partirEnPause();break;
		case 5 : menuGlobal();break;
		case 6 : System.exit(0); break;
		}
		menuSecretaire();
	}


	private static void ajouterPatient() {
		boolean existe= false;
		System.out.println("------------------------------------------------");
		int numeroSecu = saisieInt("Numero de securite:");
		List <Patient> listePatient = Context.getInstance().getDaoPatient().findAll();
		for(int i=0; i<listePatient.size(); i++) {
			if(listePatient.get(i).getNumeroSecu()== numeroSecu) existe=true;
		}
		if(!existe) {
			String nom = saisieString("Nom:");
			String prenom = saisieString("Prenom:");
			int numero = saisieInt("L'adresse:\nNumero:");
			String voie = saisieString("Voie:");
			String ville = saisieString("Ville");
			int cp = saisieInt("CP:");
			Adresse a= new Adresse(numero, voie, ville, cp);
			Patient p1 = new Patient(numeroSecu, nom, prenom,a);
			Context.getInstance().getFileAttente().add(p1);
			Context.getInstance().getDaoPatient().insert(p1);
		}else {
			Patient p =Context.getInstance().getDaoPatient().findById(numeroSecu);
			System.out.println("\nCe patient existe déjà dans la base de l'hopital\n");
			System.out.println("Numero de securite: "+p.getNumeroSecu()+"\nNom:" +p.getNom()+"\nPrenom: "+p.getPrenom()+
					"\nAdresse:\nNumero: "+p.getAdresse().getNumero()+"\nVoie: "+p.getAdresse().getVoie()+"\nVille: "+
					p.getAdresse().getVille()+"\nCP: "+p.getAdresse().getCp());
			Context.getInstance().getFileAttente().add(p);
		}
	}

	private static void etatFileAttente() {
		System.out.println("------------------------------------------------");
		if(Context.getInstance().getFileAttente()!=null) {
			for(Patient p: Context.getInstance().getFileAttente()) {
				System.out.println("Patient N°"+(Context.getInstance().getFileAttente().indexOf(p)+1)+" [numeroSecu=" + p.getNumeroSecu() + ", nom=" + p.getNom() + ", prenom=" + p.getPrenom() + ", adresse=" + p.getAdresse()
						+ "]");
			}
		}else if(Context.getInstance().getFileAttente().size()==0) System.out.println("Secretaire en Pause");
		else System.out.println("Il n' y a personne dans la salle d'attente");
		
	}

	private static void listeHistorique() {
		System.out.println("------------------------------------------------");
		etatFileAttente();
		int numSecu = saisieInt("Quel est le numéro de securite du patient?");
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Patient p = Context.getInstance().getDaoPatient().findWithVisit(numSecu);
		
		for(Visite v :  p.getVisite()) {
			System.out.println("Visite [cout=" + v.getCout() + ", numeroSalle=" + v.getNumeroSalle() + ", dateVisite=" + v.getDateVisite()+ "]");
		}
		
	}

	private static void partirEnPause() {
		System.out.println("------------------------------------------------");
		File monFichier = new File("file_Attente.rtf");
		try(FileOutputStream fos = new FileOutputStream(monFichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
				oos.writeObject(Context.getInstance().getFileAttente());;
				Context.getInstance().getFileAttente().clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		menuGlobal();
		
	}


	
	public static void main(String[] args) {
		baseHopital();
		menuGlobal();
	}

}
