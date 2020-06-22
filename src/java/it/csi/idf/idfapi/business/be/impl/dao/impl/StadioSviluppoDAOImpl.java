package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.StadioSviluppoDAO;
import it.csi.idf.idfapi.dto.StadioSviluppo;
import it.csi.idf.idfapi.mapper.StadioSviluppoMapper;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class StadioSviluppoDAOImpl implements StadioSviluppoDAO {
	
	@Override
	public List<StadioSviluppo> findAllStadioSviluppo() {
		String SQL = "SELECT * FROM idf_d_stadio_sviluppo";
		return DBUtil.jdbcTemplate.query(SQL, new StadioSviluppoMapper());	
	}

}
