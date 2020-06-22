package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoAllegatoDAO;
import it.csi.idf.idfapi.dto.TipoAllegato;
import it.csi.idf.idfapi.mapper.TipoAllegatoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoAllegatoDAOImpl implements TipoAllegatoDAO {

	private final TipoAllegatoMapper tipoAllegatoMapper = new TipoAllegatoMapper();
	
	@Override
	public TipoAllegato getTipoById(int idTipoAllegato) {
		String sql = "SELECT id_tipo_allegato, descr_tipo_allegato FROM idf_d_tipo_allegato WHERE id_tipo_allegato = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, tipoAllegatoMapper, idTipoAllegato);
	}
}
