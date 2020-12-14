package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.IDAOMedecin;
import model.Medecin;
import model.Medecin;


public class DAOMedecinJPA implements IDAOMedecin {

	@Override
	public Medecin findById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Medecin s = em.find(Medecin.class, id);
		em.close();
		return s;
	}

	@Override
	public List<Medecin> findAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Medecin",Medecin.class);

		return maRequete.getResultList();
	}


	@Override
	public void insert(Medecin objet) {

		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Medecin update(Medecin objet) {
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
		Medecin s=em.find(Medecin.class, id);
		
		em.remove(s);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
}
