package it.csi.idf.idfapi.business.be.impl.dao;

import java.text.ParseException;
import java.util.Map;

import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.IstanzaForest;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.PaginatedList;

public interface IstanzaForestDAO {
	int createIstanzaForest(IstanzaForest istanzaForest);
	
	int getNumberOfInstanceType(int instanceTypeId);
	
	IstanzaForest getByIdIntervento(int idIntervento);

	PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams) throws ParseException;

	void updateInvioIstanza(Integer idIntervento);

	void updateIstanzaTo(Integer idIntervento, InstanceStateEnum verificata);

	ConfigUtente getUtenteForIstanzaById(Integer idIntervento);
}
