package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPlLottoIntervento;

public class GeoPlLottoInterventoMapper implements RowMapper<GeoPlLottoIntervento>{

	@Override
	public GeoPlLottoIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		GeoPlLottoIntervento geoPlLottoIntervento = new GeoPlLottoIntervento();
		
		geoPlLottoIntervento.setIdgeoPlLottoIntervento(rs.getInt("idgeo_pl_lotto_intervento"));
		geoPlLottoIntervento.setDatainserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		geoPlLottoIntervento.setGeometria(rs.getObject("geometria"));
		geoPlLottoIntervento.setFkIntervento(rs.getInt("fk_intervento"));
		
		return geoPlLottoIntervento;
	}
}