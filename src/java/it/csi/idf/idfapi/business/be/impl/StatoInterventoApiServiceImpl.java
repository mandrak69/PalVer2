package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.StatoInterventoApi;
import it.csi.idf.idfapi.business.be.impl.dao.StatoInterventoDAO;
import it.csi.idf.idfapi.dto.StatoIntervento;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class StatoInterventoApiServiceImpl extends SpringSupportedResource implements StatoInterventoApi {

	@Autowired
	private StatoInterventoDAO statoInterventoDAO;
	
	@Override
	public List<StatoIntervento> getStatoList() {
		return statoInterventoDAO.getStatiInterventi();
	}
}