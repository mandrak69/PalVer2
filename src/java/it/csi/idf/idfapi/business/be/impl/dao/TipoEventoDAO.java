package it.csi.idf.idfapi.business.be.impl.dao;
import java.util.List;

import it.csi.idf.idfapi.dto.TipoEvento;

public interface TipoEventoDAO {

	List<TipoEvento> findAllTipoEventi();
	
}
