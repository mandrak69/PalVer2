package it.csi.idf.idfapi.business.be.impl.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.GeoPLProvincia;
import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;


public interface ProvinciaDAO {

	List<GeoPLProvincia> findAllProvincia();
	
	GeoPLProvincia findProvinciaByIstatProv(String istatProv) throws RecordNotFoundException;

	int createProvincia(GeoPLProvincia provincia) throws DuplicateRecordException;

	List<GeoPLProvinciaSearch> findAllSearchProvincia();
	
	List<GeoPLProvinciaSearch> findSearchProvinciaByIstatProv(String istatProv);
}
