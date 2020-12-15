package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.jpa.DAOCompteJPA;
import model.Adresse;
import model.Compte;
import model.Medecin;
import model.Patient;
import model.Secretaire;
import model.Visite;

public class Test implements Serializable {

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
		
		Visite v1 = new Visite(20, 1, LocalDate.parse("2020-06-15"), p1);
		Visite v2 = new Visite(20, 2, LocalDate.parse("2020-07-15"), p1);
		Visite v3 = new Visite(20, 2, LocalDate.parse("2020-06-15"), p2);
		Visite v4 = new Visite(20, 1, LocalDate.parse("2020-08-15"), p2);
		
		
		Context.getInstance().getDaoCompte().insert(m1);
		Context.getInstance().getDaoCompte().insert(m2);
		Context.getInstance().getDaoSecretaire().insert(s);
		Context.get_instance().getDaoPatient().insert(p1);
		Context.get_instance().getDaoPatient().insert(p2);
		Context.get_instance().getDaoVisite().insert(v1);
		Context.get_instance().getDaoVisite().insert(v3);
		Context.get_instance().getDaoVisite().insert(v2);
		Context.get_instance().getDaoVisite().insert(v4);
		Context.get_instance().getFileAttente().add(p1);
		Context.get_instance().getFileAttente().add(p2);
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
				menuSecretaire();

			}
			else 
			{
				menuGlobal();
			}

	}

	private static void menuMedecin() {
		
		System.out.println("Bienvenu dans le menu Medecin");
		int choix = saisieInt("Veuillez faire un choix:\n1 - Rendre la salle disponible\n2 - Visualiser la salle d'attente"
				+ "\n3 - Sauvegarder la liste de visites\n4 - Quitter\n5 - Se deconnecter");
		
		switch(choix) {
		case 1 : rendreSalleDispo(); break;
		case 2 : visualiserSalle(); break;
		case 3 : sauvegarderListe();break;
		case 4 : menuGlobal();break;
		case 5 : System.exit(0); break;
		}
		menuMedecin();
	}
	//les methodes utilisees
	private static void rendreSalleDispo() {
		// TODO Auto-generated method stub
		
	}

	private static void visualiserSalle() {
		// TODO Auto-generated method stub
		
	}
	private static void sauvegarderListe() {
		// TODO Auto-generated method stub
		
	}

	private static void menuSecretaire() {
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
		int numeroSecu = saisieInt("Veuillez entrer les donnees:\nNumero de securite:");
		String nom = saisieString("Nom:");
		String prenom = saisieString("Prenom:");
		int numero = saisieInt("L'adresse:\nNumero:");
		String voie = saisieString("Voie:");
		String ville = saisieString("Ville");
		int cp = saisieInt("CP:");
		Adresse a= new Adresse(numero, voie, ville, cp);
		Patient p = new Patient(numeroSecu, nom, prenom,a);
		Context.get_instance().getFileAttente().add(p);
		Context.get_instance().getDaoPatient().insert(p);
		Context.get_instance().getFileAttente().add(p);
		//Visite v = new Visite(20, numeroSalle, dateVisite);
		//Context.get_instance().getDaoVisite().insert(v);
		
	}

	private static void etatFileAttente() {
		if(Context.get_instance().getFileAttente()!=null) {
			for(Patient p: Context.get_instance().getFileAttente()) {
				System.out.println("Patient N°"+(Context.get_instance().getFileAttente().indexOf(p)+1)+" [numeroSecu=" + p.getNumeroSecu() + ", nom=" + p.getNom() + ", prenom=" + p.getPrenom() + ", adresse=" + p.getAdresse()
						+ "]");
			}
		}else System.out.println("Il n' y a personne dans la salle d'attente");
		
	}

	private static void listeHistorique() {
		etatFileAttente();
		int numSecu = saisieInt("Quel est le numéro de securite du patient?");
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Patient p = Context.get_instance().getDaoPatient().findWithVisit(numSecu);
		
		for(Visite v :  p.getVisite()) {
			System.out.println(v);
		}
		
	}

	private static void partirEnPause() {
		// TODO Auto-generated method stub
		
	}

	public static void ecrireObject(Patient p) {
		String chemin = "/Users/hajarelboumtiri/Desktop/file_Attente.rtf"; 
		File monFichier = new File(chemin);
		
		
		try(FileOutputStream fos = new FileOutputStream(monFichier);
			ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			
			oos.writeObject(p);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void lireObject() {
		String chemin = "/Users/hajarelboumtiri/Desktop/file_Attente.rtf"; 
		File monFichier = new File(chemin);
		
		Patient p = new Patient();
		
		try(FileInputStream fis = new FileInputStream(monFichier);
			ObjectInputStream ois = new ObjectInputStream(fis);) {
			p= (Patient) ois.readObject();
			System.out.println(p);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		baseHopital();
		menuGlobal();
		

	}

}
