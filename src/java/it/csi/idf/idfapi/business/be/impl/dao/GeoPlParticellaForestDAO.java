package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeoPlParticellaForest;

public interface GeoPlParticellaForestDAO {

	GeoPlParticellaForest getOneIdByGeoPlPfa(Integer idGeoPlPfa);
	GeoPlParticellaForest getForestParticleById(Integer idGeoPlPartiellaForest);
	
}
