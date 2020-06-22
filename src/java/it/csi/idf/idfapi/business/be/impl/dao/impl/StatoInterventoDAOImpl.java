package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.StatoInterventoDAO;
import it.csi.idf.idfapi.dto.StatoIntervento;
import it.csi.idf.idfapi.mapper.StatoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class StatoInterventoDAOImpl implements StatoInterventoDAO {
	
	private final StatoInterventoMapper statoInterventoMapper = new StatoInterventoMapper();

	@Override
	public List<StatoIntervento> getStatiInterventi() {
		String sql = "SELECT id_stato_intervento, descr_stato_intervento, cod_stato_intervento FROM idf_d_stato_intervento";
		
		return DBUtil.jdbcTemplate.query(sql, statoInterventoMapper);
	}
}