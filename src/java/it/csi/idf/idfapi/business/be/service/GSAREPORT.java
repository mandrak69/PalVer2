package it.csi.idf.idfapi.business.be.service;

import java.util.List;

import org.opengis.geometry.Geometry;

import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

public interface GSAREPORT {
	
	List<RicadenzaInformazioni> determinaRicadenzaSuAreeProtettePerGeometriaGML(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> determinaRicadenzaSuSicPerGeometriaGML(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> determinaRicadenzaSuZpsPerGeometriaGML(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> cercaTutteLePotettePerFiltri();
	List<RicadenzaInformazioni> cercaTuttiSic();
	List<RicadenzaInformazioni> cercaTuttiZps();
}
