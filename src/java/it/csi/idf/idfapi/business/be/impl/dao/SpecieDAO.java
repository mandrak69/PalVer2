package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.GruppoSpecie;
import it.csi.idf.idfapi.dto.TSpecie;

public interface SpecieDAO {
	
	List<TSpecie> findAllSpecie();
	
	List<GruppoSpecie> findAllGruppoSpecie();
}
