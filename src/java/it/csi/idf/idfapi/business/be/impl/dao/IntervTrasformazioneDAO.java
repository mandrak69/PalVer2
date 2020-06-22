package it.csi.idf.idfapi.business.be.impl.dao;

import java.math.BigDecimal;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.CompensationForesteDTO;
import it.csi.idf.idfapi.dto.IntervTrasformazione;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;

public interface IntervTrasformazioneDAO {
	
	int createIntervTrasformazioneWithConfigUtente(int idIntervento, int fkConfigUtente);
	
	void setFlgVincIdro(byte flagVincIdro, Integer idIntervento);
	
	void setCompenzazioneEuro(BigDecimal compenzazione, Integer idIntervento);
	
	void updateCompensazioneNecessaria(SuperficieCompensationEnum superficieCompensationEnum, Integer idIntervento);
	
	void updateCompensazioneNonNecessaria(CompensationForesteDTO compensationForeste, Integer idIntervento);
	
	IntervTrasformazione getIntervTrasfById(int idIntervento) throws RecordNotUniqueException;
	
	void updateWithDichiarazioni(IntervTrasformazione intervTrasformazione, int idIntervento);
}
