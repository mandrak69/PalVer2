package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.FasciaAltimetrica;

public class FasciaAltimetricaMapper implements RowMapper<FasciaAltimetrica> {

	@Override
	public FasciaAltimetrica mapRow(ResultSet rs, int arg1) throws SQLException {

		FasciaAltimetrica fasciaAltimetrica = new FasciaAltimetrica();
		
		fasciaAltimetrica.setIdFasciaAltimetrica(rs.getInt("id_fascia_altimetrica"));
		fasciaAltimetrica.setFasciaAltimetricaMIN(rs.getInt("fascia_altimetica_min"));
		fasciaAltimetrica.setFasciaAltimetricaMAX(rs.getInt("fascia_altimetrica_max"));
		fasciaAltimetrica.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		fasciaAltimetrica.setFlgVisible(rs.getInt("flg_visibile"));
		
		return fasciaAltimetrica;
	}

}
