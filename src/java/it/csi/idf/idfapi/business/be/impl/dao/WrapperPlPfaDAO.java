package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.util.PaginatedList;

@Transactional(rollbackFor=Exception.class)
public interface WrapperPlPfaDAO {
	
	PaginatedList<GeoPfaSearch> getPublicPianiForestaliSearch(Map<String, String> queryParams);

	GeoPfaSearchDettaglio getPublicPfaSearchByID(Integer idGeoPlPfa);
	
	byte[] generateExcel(ExcelDTO excel);
}