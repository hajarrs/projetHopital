package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Query;

import config.Context;


@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte")
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	protected String login;
	protected String password;
	
	public Compte() 
	{
		
	}

	public Compte(int id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static Compte checkConnect(String pseudo, String password) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte c where c.password=:password and c.pseudo=:pseudo",Compte.class);

		maRequete.setParameter("pseudo", pseudo);
		maRequete.setParameter("password", password);

		return (Compte)maRequete.getSingleResult();




		}

}
