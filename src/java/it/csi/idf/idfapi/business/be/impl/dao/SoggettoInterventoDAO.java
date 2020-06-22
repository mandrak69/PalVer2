package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.SoggettoIntervento;

public interface SoggettoInterventoDAO {
	
	int createSoggettoIntervento(SoggettoIntervento soggettoIntervento);

	SoggettoIntervento getSoggettoInterventoById(Integer idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException;
	
	void updateWithIdSoggettoAndFkTipoTitolarita(int idSoggetto, int tipoTitolarita, int idIntervento);
}
