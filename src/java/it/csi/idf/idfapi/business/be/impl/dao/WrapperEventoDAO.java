package it.csi.idf.idfapi.business.be.impl.dao;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.PlainEventoId;
import it.csi.idf.idfapi.dto.PlainPrimaPfaEvento;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;

@Transactional(rollbackFor=Exception.class)
public interface WrapperEventoDAO {
	
	PlainEventoId savePrimaPfaEvento(PlainPrimaPfaEvento body, int idGeoPlPfa);

	void saveSecondoPfaEvento(PlainSecondoPfaEvento evento, Integer idEvento)throws RecordNotFoundException;
	
	void deleteEventi(Integer idEvento) throws RecordNotFoundException;
}