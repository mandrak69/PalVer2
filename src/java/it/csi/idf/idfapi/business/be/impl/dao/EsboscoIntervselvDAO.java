package it.csi.idf.idfapi.business.be.impl.dao;

public interface EsboscoIntervselvDAO {

	int createIntervselvEsbosco(String codEsbosco, Integer idIntervento);
	
	void deleteIntervselvEsbosco(Integer idIntervento);
	
}
