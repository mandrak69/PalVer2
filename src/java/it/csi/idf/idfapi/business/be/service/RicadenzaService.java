package it.csi.idf.idfapi.business.be.service;

import java.util.List;

import org.opengis.geometry.Geometry;

import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

public interface RicadenzaService {
	
	List<RicadenzaInformazioni> getPopolamentiDaSemes(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> getCategoriesForestali(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> cercaTuttiPopolamentiDaSeme();
	List<RicadenzaInformazioni> cercaTutteCategorieForestali();
}
