package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.PfPg;

public interface PfPgDAO {
	void createPfPg(PfPg pfPg);
	
	PfPg getBySoggettoPfAndSoggettoPg(int idSoggetoPf, int idSoggettoPg) throws RecordNotUniqueException;
}
