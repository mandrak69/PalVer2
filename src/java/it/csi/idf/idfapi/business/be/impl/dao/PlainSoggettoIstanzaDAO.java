package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.PlainSoggettoIstanza;
import it.csi.idf.idfapi.util.PaginatedList;

public interface PlainSoggettoIstanzaDAO {
	PaginatedList<PlainSoggettoIstanza> getAllIstances(int fkConfigUtente, Integer tipoAccreditamento, int page, int limit, String sort);
}
