package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeneratedFileParameters;

public interface DichiarazioneSummaryDAO {
	
	GeneratedFileParameters getSummary(int idIntervento);
}
