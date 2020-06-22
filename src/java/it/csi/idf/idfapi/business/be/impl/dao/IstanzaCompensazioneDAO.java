package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.IstanzaCompensazione;

public interface IstanzaCompensazioneDAO {
	void insertIstanzaCompensazione(IstanzaCompensazione istanzaCompensazione);
	List<IstanzaCompensazione> getAllByFkIntervento(int fkIntervento);
	void deleteIstanza(int numIstanzaCompensazione);
}
