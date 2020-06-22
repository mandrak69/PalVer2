package it.csi.idf.idfapi.business.be.impl.dao;

public interface PartforInterventoDAO {
	
	int createParforInterv(Integer idParticelaForestale, Integer idIntervento);
	
	void deletePartforIntervento(Integer idIntervento);

	
}
