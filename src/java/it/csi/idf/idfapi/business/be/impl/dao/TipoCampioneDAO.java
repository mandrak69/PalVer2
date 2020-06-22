package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.TipoCampione;

public interface TipoCampioneDAO {

	List<TipoCampione> findAllTipoCampione(Byte flagVisible);
	
}
