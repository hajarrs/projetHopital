package dao;

import model.Patient;

public interface IDAOPatient extends IDAO<Patient,Integer>{
	public Patient findWithVisit(Integer id);
}
