package dao;

import model.Bibliotheque;

public interface IDAOVisite extends IDAO<Bibliotheque,Integer>{

	
	public Bibliotheque selectByIdCompte(Integer id);
}
