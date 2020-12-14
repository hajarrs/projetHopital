package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.IDAOAdresse;
import model.Adresse;

public class DAOAdresseJPA implements IDAOAdresse{
	@Override
	public Adresse findById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Adresse a = em.find(Adresse.class, id);
		em.close();
		return a;
	}

	@Override
	public List<Adresse> findAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Adresse",Adresse.class);

		return maRequete.getResultList();
	}


	@Override
	public void insert(Adresse objet) {

		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Adresse update(Adresse objet) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		objet=em.merge(objet);
		em.getTransaction().commit();
		em.close();
		return objet;
	}

	@Override
	public void delete(Adresse objet) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		objet=em.merge(objet);

		em.remove(objet);

		em.getTransaction().commit();
		em.close();
	}
	@Override
	public void deleteById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Adresse a=em.find(Adresse.class, id);
		
		em.remove(a);
		
		em.getTransaction().commit();
		em.close();
	}
}
