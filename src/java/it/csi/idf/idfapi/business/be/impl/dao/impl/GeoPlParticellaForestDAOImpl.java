package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.dto.GeoPlParticellaForest;
import it.csi.idf.idfapi.mapper.GeoPlParticellaForestMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoPlParticellaForestDAOImpl implements GeoPlParticellaForestDAO {
	
	private final GeoPlParticellaForestMapper geoPlParticellaForestMapper = new GeoPlParticellaForestMapper();

	@Override
	public GeoPlParticellaForest getOneIdByGeoPlPfa(Integer idGeoPlPfa) {
		String sql = "SELECT * FROM idf_geo_pl_particella_forest WHERE idgeo_pl_pfa = ?  LIMIT 1";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, geoPlParticellaForestMapper, idGeoPlPfa);
	}

	
	@Override
	public GeoPlParticellaForest getForestParticleById(Integer idGeoPlParticellaForest) {
		
		String sql = "SELECT * FROM idf_geo_pl_particella_forest WHERE idgeo_pl_particella_forest = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), geoPlParticellaForestMapper, idGeoPlParticellaForest);
	}
	
	
}