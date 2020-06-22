package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDAO;
import it.csi.idf.idfapi.dto.TipoIntervento;
import it.csi.idf.idfapi.mapper.TipoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class TipoInterventoDAOImpl implements TipoInterventoDAO {
	
	private final TipoInterventoMapper tipoInterventoMapper = new TipoInterventoMapper();

	@Override
	public List<TipoIntervento> findAllTipoIntervento() {
		String sql = "SELECT * FROM idf_d_tipo_intervento WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, tipoInterventoMapper);	
	}
}