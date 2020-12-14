package test;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.jpa.DAOCompteJPA;
import model.*;

public class Test {

	static Compte connected=null;
	
	public static void baseHopital() {

		Medecin m1 = new Medecin("magali1", "123456");
		m1.setSalle(1);
		Medecin m2 = new Medecin("magali2", "123456");
		m2.setSalle(2);
		Secretaire s = new Secretaire("hajars", "hajar1");
		
		Context.getInstance().getDaoCompte().insert(m1);
		Context.getInstance().getDaoCompte().insert(m2);
		Context.getInstance().getDaoSecretaire().insert(s);

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
		System.out.println("Hello medecin");
		
	}
	private static void menuSecretaire() {
		System.out.println("Hello secretaire");
	}

	

	public static void main(String[] args) {
		baseHopital();
		menuGlobal();
		

	}

}
