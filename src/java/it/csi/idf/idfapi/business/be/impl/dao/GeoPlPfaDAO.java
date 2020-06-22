package it.csi.idf.idfapi.business.be.impl.dao;


import java.util.List;
import java.util.Map;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.dto.GeoPlPfa;
import it.csi.idf.idfapi.util.PaginatedList;

public interface GeoPlPfaDAO {

	int createGeoPlPfa(GeoPlPfa newGeoPlPfa) throws DuplicateRecordException;

	GeoPlPfa findGeoPlPfaById(Integer idGeoPlPfa) throws RecordNotFoundException;

	List<GeoPlPfa> findAllGeoPlPfa();
	
	GeoPfaSearchDettaglio findSearchPfaByID(StringBuilder s, List<Object> parameters);
	
	PaginatedList<GeoPfaSearchDettaglio> search(StringBuilder s, int page, int limit, List<Object>parameters);

	PaginatedList<GeoPfaSearch> searchPianiForestali(Map<String, String> queryParams);
	
	GeoPfaSearchDettaglio pianoForestaleSearchDettaglio(Integer idGeoPlPfa);
}