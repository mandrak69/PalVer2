package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;

public interface RelascopicaSempliceDAO {
	
	int saveRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice);
	
	public int updateRelascopica(PlainRelascopicaSemplice plainRelascopicaSemplice);
	
	PlainRelascopicaSemplice getPlainRelascopicaSemplice(String codiceAds);
	
	boolean relascopicaExists(Long codiceAds);
}
