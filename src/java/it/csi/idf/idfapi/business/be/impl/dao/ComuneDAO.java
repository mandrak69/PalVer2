package it.csi.idf.idfapi.business.be.impl.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;

public interface ComuneDAO {

	List<ComuneResource> findAllComune();

	Comune findComuneByID(Integer idComune) throws RecordNotFoundException;
	Comune findComuneByName(String name) throws RecordNotUniqueException;
	int createComune(Comune comune);

	ComuneResource findComuneResourceByID(Integer idComune) throws RecordNotUniqueException;
	List<ComuneResource> findAllComuneByProvincia(String istatProv);
	ComuneResource findComuneResourceByIstatComune(String istatComune) throws RecordNotUniqueException;
}
