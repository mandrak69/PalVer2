package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.PropcatastoIntervento;

public interface PropcatastoInterventoDAO {
	List<PropcatastoIntervento> getAllPropcatastoByIdIntervento(int idIntervento);
	void insertPropcatastoIntervento(PropcatastoIntervento propcatastoIntervento);
	void deletePropcatastoIntervento(int idgeoPlPropCatasto, int idIntervento);
}
