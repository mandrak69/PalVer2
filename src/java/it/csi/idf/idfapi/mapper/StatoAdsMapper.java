package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StatoAds;

public class StatoAdsMapper implements RowMapper<StatoAds>{

	@Override
	public StatoAds mapRow(ResultSet rs, int arg1) throws SQLException {
		
		StatoAds statoAds = new StatoAds();
		statoAds.setIdStatoAds(rs.getInt("id_stato_ads"));
		statoAds.setDescrStatoAds(rs.getString("descr_stato_ads"));
		statoAds.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		statoAds.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return statoAds;
	}

}
