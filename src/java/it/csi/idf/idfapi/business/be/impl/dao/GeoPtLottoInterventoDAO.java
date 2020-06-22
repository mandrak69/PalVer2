package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeoPtLottoIntervento;

public interface GeoPtLottoInterventoDAO {
	
	int insertGeoPtLottoIntervento(GeoPtLottoIntervento geoPtLottoIntervento);
	
	void deleteGeoPtLottoIntervento(int idIntervento);
}