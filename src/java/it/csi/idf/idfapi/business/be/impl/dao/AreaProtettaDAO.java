package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.AreaProtetta;

public interface AreaProtettaDAO {
	
	List<AreaProtetta> getByIdgeoPlPfa(int idgeoPlPfa);
} 
