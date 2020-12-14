package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.IDAOCompte;
import model.Compte;

public class DAOCompteJPA implements IDAOCompte {

	@Override
	public Compte findById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Compte s = em.find(Compte.class, id);
		em.close();
		return s;
	}

	@Override
	public List<Compte> findAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte",Compte.class);

		return maRequete.getResultList();
	}


	@Override
	public void insert(Compte objet) {

		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Compte update(Compte objet) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		objet=em.merge(objet);
		em.getTransaction().commit();
		em.close();
		return objet;
	}


	@Override
	public void deleteById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Compte s=em.find(Compte.class, id);
		
		em.remove(s);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static Compte checkConnect(String pseudo, String password) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte c where c.password=:password and c.pseudo=:pseudo",Compte.class);

		maRequete.setParameter("pseudo", pseudo);
		maRequete.setParameter("password", password);

		return (Compte)maRequete.getSingleResult();




		}
	
}
