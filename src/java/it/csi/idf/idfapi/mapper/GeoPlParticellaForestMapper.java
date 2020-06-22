package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPlParticellaForest;

public class GeoPlParticellaForestMapper implements RowMapper<GeoPlParticellaForest>{

	@Override
	public GeoPlParticellaForest mapRow(ResultSet rs, int arg1) throws SQLException {
		GeoPlParticellaForest geoPlParticellaForest = new GeoPlParticellaForest();
		
		geoPlParticellaForest.setIdgeoPlParticellaForest(rs.getInt("idgeo_pl_particella_forest"));
		geoPlParticellaForest.setFkPadre(rs.getInt("fk_padre"));
		geoPlParticellaForest.setIdgeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		geoPlParticellaForest.setFkCompresa(rs.getInt("fk_compresa"));
		geoPlParticellaForest.setDenominazioneParticella(rs.getString("denominazione_particella"));
		geoPlParticellaForest.setEttari(rs.getBigDecimal("ettari")== null ? null : rs.getBigDecimal("ettari").setScale(2, RoundingMode.FLOOR));
		geoPlParticellaForest.setCodParticellaFor(rs.getInt("cod_particella_for"));
		geoPlParticellaForest.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		geoPlParticellaForest.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		geoPlParticellaForest.setGeometria(null); //TODO Mocked
		geoPlParticellaForest.setProvvigioneHa(rs.getBigDecimal("provvigione_ha")== null ? null : rs.getBigDecimal("provvigione_ha").setScale(2, RoundingMode.FLOOR));
		geoPlParticellaForest.setProvvigioneReale(rs.getBigDecimal("provvigione_reale"));
		geoPlParticellaForest.setPercTassoRipresaPot(rs.getInt("perc_tasso_ripresa_pot"));
		geoPlParticellaForest.setPercTara(rs.getInt("perc_tara"));
		geoPlParticellaForest.setRipresaTotHa(rs.getBigDecimal("ripresa_tot_ha"));
		geoPlParticellaForest.setRipresaAttuale(rs.getBigDecimal("ripresa_attuale"));
		geoPlParticellaForest.setRipresaResidua(rs.getBigDecimal("ripresa_residua"));
		
		return geoPlParticellaForest;
	}
}