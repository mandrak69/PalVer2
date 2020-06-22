package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ProprietaDAO;
import it.csi.idf.idfapi.dto.Proprieta;
import it.csi.idf.idfapi.mapper.ProprietaMapper;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class ProprietaDAOImpl implements ProprietaDAO {
	
	@Override
	public List<Proprieta> findAllProprieta() {
		String SQL = "SELECT * FROM idf_d_proprieta";
		return DBUtil.jdbcTemplate.query(SQL, new ProprietaMapper());	
	}

}
