package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.EventoDTO;

public interface EventoPartforDAO {

	int createEventoPartfor(EventoDTO eventoDto, Integer idEvento);
	
	void deleteEventoPartfor(Integer idEvento);
	
	void createEventoPartfor(Integer idEvento, Integer idGeoPlParticellaForest);
	
	void updateEventoPartfor(Integer idEvento, Integer idGeoPlParticellaForest, Integer percDanno);
}
