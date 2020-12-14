package test;

import java.util.Scanner;

import config.Context;
import dao.jpa.DAOCompteJPA;
import model.*;

public class Test {

	static Compte connected=null;
	
	public static void baseHopital() {

		Medecin m1 = new Medecin(1, "magali1", "123456");
		Medecin m2 = new Medecin(2, "magali2", "123456");
		Secretaire s = new Secretaire(1, "hajars", "hajar1");
		
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
		int choix = saisieInt("Bienvenue dans l'application de l'hopital\nSe connecter:\n");
		String pseudo= saisieString("Pseudo:");
		String password=saisieString("Password:");
		try{
			connected=DAOCompteJPA.checkConnect(pseudo,password); 
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
		// TODO Auto-generated method stub
		
	}
	private static void menuSecretaire() {
		// TODO Auto-generated method stub
		
	}

	

	public static void main(String[] args) {
		baseHopital();
		menuGlobal();
		

	}

}
