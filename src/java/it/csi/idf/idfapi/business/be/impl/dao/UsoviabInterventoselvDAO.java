package it.csi.idf.idfapi.business.be.impl.dao;

public interface UsoviabInterventoselvDAO {

	int createIntervselUsovib(Integer fkUsoViabilita, Integer idIntervento);
	
	void deleteIntervselUsovib(Integer idIntervento);
}
