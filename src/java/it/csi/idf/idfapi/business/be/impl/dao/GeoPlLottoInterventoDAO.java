package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeoPlLottoIntervento;

public interface GeoPlLottoInterventoDAO {
	
	int insertGeoPlLottoIntervento(GeoPlLottoIntervento geoPlLottoIntervento);
	
	void deleteGeoPlLottoIntervento(int idIntervento);
}