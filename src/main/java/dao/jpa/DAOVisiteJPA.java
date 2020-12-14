package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.IDAOVisite;
import model.Bibliotheque;

public class DAOVisiteJPA implements IDAOVisite{
	@Override
	public Bibliotheque findById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Bibliotheque b = em.find(Bibliotheque.class, id);
		em.close();
		return b;
	}

	@Override
	public List<Bibliotheque> findAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Bibliotheque",Bibliotheque.class);

		return maRequete.getResultList();
	}


	@Override
	public void insert(Bibliotheque objet) {

		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Bibliotheque update(Bibliotheque objet) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		objet=em.merge(objet);
		em.getTransaction().commit();
		em.close();
		return objet;
	}

	@Override
	public void delete(Bibliotheque objet) {
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
		Bibliotheque d=em.find(Bibliotheque.class, id);
		
		em.remove(d);
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Bibliotheque selectByIdCompte(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Query maBiblio = em.createQuery("from bibliotheque where idCompte= :id",Bibliotheque.class);
		return (Bibliotheque) maBiblio.getResultStream();
	}

}



