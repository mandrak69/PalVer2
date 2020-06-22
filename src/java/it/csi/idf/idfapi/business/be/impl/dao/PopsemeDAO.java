package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.Popseme;

public interface PopsemeDAO {
	
	List<Popseme> getFatPopsemeByIdgeoPlPfa(int idgeoPlPfa);
}