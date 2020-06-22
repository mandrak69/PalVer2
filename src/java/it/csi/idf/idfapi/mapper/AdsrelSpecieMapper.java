package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RAdsrelSpecie;

public class AdsrelSpecieMapper implements RowMapper<RAdsrelSpecie> {
	public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC")); 
	@Override
	public RAdsrelSpecie mapRow(ResultSet rs, int arg1) throws SQLException {

		RAdsrelSpecie rAdsrelSpecie = new RAdsrelSpecie();

		rAdsrelSpecie.setIdSpecie(rs.getLong("id_specie"));
		rAdsrelSpecie.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		rAdsrelSpecie.setDataInizioValidita(rs.getTimestamp("data_inizio_validita"));
		rAdsrelSpecie.setCodTipoCampione(rs.getString("cod_tipo_campione"));
		rAdsrelSpecie.setFlgPolloneSeme(rs.getString("flg_pollone_seme"));
		rAdsrelSpecie.setQualita(rs.getString("qualita"));
		rAdsrelSpecie.setDiametro(rs.getInt("diametro"));
		rAdsrelSpecie.setAltezza(rs.getInt("altezza"));
		rAdsrelSpecie.setIncremento(rs.getInt("incremento"));
		rAdsrelSpecie.setEta(rs.getInt("eta"));
		rAdsrelSpecie.setNrAlberiPollone(rs.getInt("nr_alberi_pollone"));
		rAdsrelSpecie.setNrAlberiSeme(rs.getInt("nr_alberi_seme"));
		rAdsrelSpecie.setDataFineValidita(rs.getDate("data_fine_validita")== null ? null : rs.getDate("data_fine_validita").toLocalDate());		
		rAdsrelSpecie.setNote(rs.getString("note"));
		
	return rAdsrelSpecie;
	
	}
}
