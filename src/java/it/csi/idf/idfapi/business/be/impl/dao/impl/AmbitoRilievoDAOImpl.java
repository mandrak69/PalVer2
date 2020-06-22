package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AmbitoRilievoDAO;
import it.csi.idf.idfapi.dto.AmbitoRilievo;
import it.csi.idf.idfapi.mapper.AmbitoRilievoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class AmbitoRilievoDAOImpl implements AmbitoRilievoDAO {
	
	@Override
	public List<AmbitoRilievo> findAllAmbitoRilievo() {
		String SQL = "SELECT * FROM idf_d_ambito_rilievo";
		return DBUtil.jdbcTemplate.query(SQL, new AmbitoRilievoMapper());	
	}
}
