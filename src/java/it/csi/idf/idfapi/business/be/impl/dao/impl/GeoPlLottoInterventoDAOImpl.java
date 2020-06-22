package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlLottoInterventoDAO;
import it.csi.idf.idfapi.dto.GeoPlLottoIntervento;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoPlLottoInterventoDAOImpl implements GeoPlLottoInterventoDAO {

	@Override
	public int insertGeoPlLottoIntervento(GeoPlLottoIntervento geoPlLottoIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_geo_pl_lotto_intervento(");
		sql.append("data_inserimento, geometria, fk_intervento)");
		sql.append(" VALUES (?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(Date.valueOf(geoPlLottoIntervento.getDatainserimento()));
		parameters.add(geoPlLottoIntervento.getGeometria());
		parameters.add(geoPlLottoIntervento.getFkIntervento());
		
		
		return AIKeyHolderUtil.updateWithGenKey("idgeo_pl_lotto_intervento", sql.toString(), parameters);
	}

	@Override
	public void deleteGeoPlLottoIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM idf_geo_pl_lotto_intervento");
		sql.append(" WHERE fk_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}
}