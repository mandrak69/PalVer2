package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import it.csi.idf.idfapi.business.be.impl.dao.PrioritaDAO;
import it.csi.idf.idfapi.dto.Priorita;
import it.csi.idf.idfapi.mapper.PrioritaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PrioritaDAOImpl implements PrioritaDAO {

	@Override
	public List<Priorita> findAllPriorita() {
		
		String sql = "SELECT * FROM idf_d_priorita";
		return DBUtil.jdbcTemplate.query(sql, new PrioritaMapper());
		
	}

}
