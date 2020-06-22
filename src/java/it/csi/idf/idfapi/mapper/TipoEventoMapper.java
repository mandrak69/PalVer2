package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoEvento;

public class TipoEventoMapper implements RowMapper<TipoEvento> {

	@Override
	public TipoEvento mapRow(ResultSet rs, int arg1) throws SQLException {

		TipoEvento tipoEvento = new TipoEvento();
		
		tipoEvento.setIdTipoEvento(rs.getInt("id_tipo_evento"));
		tipoEvento.setDescrTipoEvento(rs.getString("descr_tipo_evento"));
		tipoEvento.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		tipoEvento.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return tipoEvento;
	}

	
}
