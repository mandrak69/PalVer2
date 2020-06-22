package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.InterventoPopSeme;

public interface InterventoPopSemeDAO {
	int insertInterventoPopSeme(InterventoPopSeme interventoPopSeme);
	List<InterventoPopSeme> getInterventosByIdIntervento(int idIntervento);
	void deleteInterventosByIdIntervento(int idIntervento);
}
