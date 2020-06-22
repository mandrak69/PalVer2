package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.SpeciePfaIntervento;

public interface SpeciePfaInterventoDAO {

	int createSpeciePfaIntervento(SpeciePfaIntervento speciePfaIntervento, Integer idIntervento);
	
	void deleteSpeciePfaIntervento(Integer idSpecie, Integer idIntervento);
}
