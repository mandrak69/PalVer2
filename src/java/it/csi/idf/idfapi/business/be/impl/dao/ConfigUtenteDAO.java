package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.ConfigUtente;

public interface ConfigUtenteDAO {
	
	ConfigUtente getCofigUtenteById(int configUtenteId);
	
	int createConfigUtente(ConfigUtente configUtente);
	
	void updateConfigUtente(ConfigUtente configUtente);
}
