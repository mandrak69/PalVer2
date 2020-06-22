package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeoLnLottoIntervento;

public interface GeoLnLottoInterventoDAO {

	int insertGeoLnLottoIntervento(GeoLnLottoIntervento geoLnLottoIntervento);
	
	void deleteGeoLnLottoIntervento(int idIntervento);
}