package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.FatSoggetto;
import it.csi.idf.idfapi.dto.SoggettoResource;
import it.csi.idf.idfapi.dto.TSoggetto;

public interface SoggettoDAO {

	TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException;

	TSoggetto findSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException;

	List<TSoggetto> findByPartialCodiceFiscale(String codiceFiscale);

	int createSoggetto(TSoggetto soggetto);

	int createSoggettoFisica(TSoggetto soggetto);

	int updateSoggetto(TSoggetto soggetto);

	void updateFkConfigUtente(int idSoggetto, int configUtenteId);

	void updateSoggettoProfess(TSoggetto soggettoProfess);

	List<SoggettoResource> getPersFisicaForBOSearch();

	List<SoggettoResource> getPersGiuridicaForBOSearch();

	List<SoggettoResource> getProfessForBOSearch();
	
	FatSoggetto findFatSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException;
}
