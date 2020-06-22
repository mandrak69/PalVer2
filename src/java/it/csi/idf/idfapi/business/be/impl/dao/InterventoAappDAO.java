package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.InterventoAapp;

public interface InterventoAappDAO {
	int insertInterventoAapp(InterventoAapp interventoAapp);
	List<InterventoAapp> getInterventosByIdIntervento(int idIntervento);
	void deleteAllInterventoAappsByIdIntervento(int idIntervento);
}
