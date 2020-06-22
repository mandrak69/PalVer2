package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.dto.InterventoCatfor;

public interface CategoriaForestaleDAO {
	List<CategoriaForestale> findAllCategoriaForestale();
	CategoriaForestale getByCodiceAmministratico(String codiceAmministrativo);
	CategoriaForestale getCategoriaForestaleById(int idCategoriaForestale);
	List<CategoriaForestale> getAllByInterventoCatfors(List<InterventoCatfor> interventoCatfors);
}
