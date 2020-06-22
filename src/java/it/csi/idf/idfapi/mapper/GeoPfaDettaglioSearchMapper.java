package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;

public class GeoPfaDettaglioSearchMapper implements RowMapper<GeoPfaSearchDettaglio> {

	@Override
	public GeoPfaSearchDettaglio mapRow(ResultSet rs, int arg1) throws SQLException {

		GeoPfaSearchDettaglio geoSearch = new GeoPfaSearchDettaglio();
		geoSearch.setIdGeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		geoSearch.setDenominazione(rs.getString("denominazione"));
		geoSearch.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		geoSearch.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		geoSearch.setDataApprovazione(
				rs.getDate("data_approvazione") == null ? null : rs.getDate("data_approvazione").toLocalDate());
		geoSearch.setDataRevisione(
				rs.getDate("data_revisione") == null ? null : rs.getDate("data_revisione").toLocalDate());
		geoSearch.setGeometria(rs.getObject("geometria"));
		geoSearch.setFonteFinanziamento(rs.getString("fonte_finanziamento"));
		geoSearch.setFlgRevisione(rs.getByte("flg_revisione"));
		geoSearch.setPropNonForestaleHa(rs.getBigDecimal("prop_non_forestale_ha") == null ? null
				: rs.getBigDecimal("prop_non_forestale_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setSupPianifNonForestaleHa(rs.getBigDecimal("sup_pianif_non_forestale_ha") == null ? null
				: rs.getBigDecimal("sup_pianif_non_forestale_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setProprietaSilvopastHa(rs.getBigDecimal("proprieta_silvopast_ha") == null ? null
				: rs.getBigDecimal("proprieta_silvopast_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setProprietaForestaleHa(rs.getBigDecimal("proprieta_forestale_ha") == null ? null
				: rs.getBigDecimal("proprieta_forestale_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setSuperfBocsGestAttivaHa(rs.getBigDecimal("superf_bosc_gest_attiva_ha") == null ? null
				: rs.getBigDecimal("superf_bosc_gest_attiva_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setSupPianifForestaleHa(rs.getBigDecimal("sup_pianif_forestale_ha") == null ? null
				: rs.getBigDecimal("sup_pianif_forestale_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setSuperfGestNonAttivaMonHa(rs.getBigDecimal("superf_gest_non_attiva_mon_ha") == null ? null
				: rs.getBigDecimal("superf_gest_non_attiva_mon_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setSuperfGestNonAttivaTotHa(rs.getBigDecimal("superf_gest_non_attiva_tot_ha") == null ? null
				: rs.getBigDecimal("superf_gest_non_attiva_tot_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setSuperfGestNonAttivaEvlHa(rs.getBigDecimal("superf_gest_non_attiva_evl_ha") == null ? null
				: rs.getBigDecimal("superf_gest_non_attiva_evl_ha").setScale(0, RoundingMode.FLOOR));
		geoSearch.setGestori((String[]) rs.getArray("gestori").getArray());
		geoSearch.setDenominazioneComuni(rs.getString("denominazione_comuni"));
		geoSearch.setIdComuni((Integer[]) rs.getArray("id_comuni").getArray());
		geoSearch.setDenominazioneProvincie(rs.getString("denominazione_provincie"));
		geoSearch.setIstatProvincie((String[]) rs.getArray("istat_provincie").getArray());
		geoSearch.setDescrPropriete(rs.getString("descr_propriete"));

		return geoSearch;

	}

}