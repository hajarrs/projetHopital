package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.IDAOSecretaire;
import model.Secretaire;


public class DAOSecretaireJPA implements IDAOSecretaire{


	@Override
	public Secretaire findById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Secretaire s = em.find(Secretaire.class, id);
		em.close();
		return s;
	}

	@Override
	public List<Secretaire> findAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Secretaire",Secretaire.class);

		return maRequete.getResultList();
	}


	@Override
	public void insert(Secretaire objet) {

		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Secretaire update(Secretaire objet) {
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
		Secretaire s=em.find(Secretaire.class, id);
		
		em.remove(s);
		
		em.getTransaction().commit();
		em.close();
	}
}
