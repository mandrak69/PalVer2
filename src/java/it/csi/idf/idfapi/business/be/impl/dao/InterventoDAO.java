package it.csi.idf.idfapi.business.be.impl.dao;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.PaginatedList;


public interface InterventoDAO {

	List<Intervento> findAllInterventi();
	
	InterventoPiano findInterventoPianoByID(Integer idIntervento);
	
	int createInterventoWithConfigUtente(int fkConfigUtente);
	
	int createIntervento(TipoInterventoDatiTecnici interventoDatiTehnici) throws DuplicateRecordException;
	
    void updateIntervento(TipoInterventoDatiTecnici interventoDatiTehnici, Integer idIntervento, int fkConfigUtente) throws RecordNotFoundException;
    
    Intervento findInterventoByIdIntervento(int idIntervento);
    
    void updateInterventoWithSuperficieInteressata(Integer idIntervento, BigDecimal superficieInteressata);
    
    void updateInterventoWithFkSoggettoProfess(int idIntervento, int fkSoggettoProfess);
    
    void deleteIntervento(Integer idIntervento);

	PaginatedList<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa, int page, int limit, String sort);
	
	List<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa);
	
	
}
