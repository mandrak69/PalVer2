package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.IntervSelvicolturale;

public interface IntervSelvicoulturaleDAO {
	
	void insertIntervSelvicolturale(IntervSelvicolturale intervSelvicolturale, Integer idIntervento);
	
	void updateIntervSelvicolturale(IntervSelvicolturale interventoSelvi, Integer idIntervento );
	
	void deleteIntervSelvicolturale(Integer idIntervento);

	int getProssimoNrProgInterv(int idTipoIntervento);
	
	IntervSelvicolturale findInterventoSelvicolturale(Integer idIntervento);

}
