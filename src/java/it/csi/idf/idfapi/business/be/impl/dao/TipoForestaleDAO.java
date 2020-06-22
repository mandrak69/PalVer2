package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.TipoForestale;

public interface TipoForestaleDAO {
	List<TipoForestale> findAllTipoForestale();
	List<TipoForestale> findAllTipoByCategoria(Integer categoriaForestale) throws RecordNotFoundException;
	TipoForestale getTipoForestaleById(int idTipoForestale);
}
