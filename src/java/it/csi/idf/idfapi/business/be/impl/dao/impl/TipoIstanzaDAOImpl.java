package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoIstanzaDAO;
import it.csi.idf.idfapi.dto.TipoIstanzaResource;
import it.csi.idf.idfapi.mapper.TipoIstanzaResourceMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoIstanzaDAOImpl implements TipoIstanzaDAO {

	private final TipoIstanzaResourceMapper tipoIstanzaResMapper = new TipoIstanzaResourceMapper();
	
	@Override
	public List<TipoIstanzaResource> getTrasformazioneTipo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_tipo_istanza, descr_dett_tipoistanza");
		sql.append(" FROM idf_d_tipo_istanza");
		sql.append(" WHERE id_tipo_istanza = 1");
		
		return Collections.singletonList(DBUtil.jdbcTemplate.queryForObject(sql.toString(), tipoIstanzaResMapper));
	}
}
