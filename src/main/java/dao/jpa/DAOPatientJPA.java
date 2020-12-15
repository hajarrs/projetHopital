package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Context;
import dao.IDAOPatient;
import model.Patient;
import model.Visite;

public class DAOPatientJPA implements IDAOPatient{
	@Override
	public Patient findById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Patient s = em.find(Patient.class, id);
		em.close();
		return s;
	}

	@Override
	public List<Patient> findAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Patient",Patient.class);

		return maRequete.getResultList();
	}


	@Override
	public void insert(Patient objet) {

		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Patient update(Patient objet) {
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
		Patient s=em.find(Patient.class, id);
		
		em.remove(s);
		
		em.getTransaction().commit();
		em.close();
	}
	@Override
	public Patient findWithVisit(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("select p from Patient p join p.visite where p.numeroSecu= :numSecu",Patient.class);
		maRequete.setParameter("numSecu", id);

		return (Patient)maRequete.getSingleResult();
	}
}



