package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.FasciaAltimetricaDAO;
import it.csi.idf.idfapi.dto.FasciaAltimetrica;
import it.csi.idf.idfapi.mapper.FasciaAltimetricaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class FasciaAltimetricaDAOImpl implements FasciaAltimetricaDAO{

	@Override
	public List<FasciaAltimetrica> findAllFasciaAltimetrica() {
		
		String query = "SELECT * FROM idf_d_fascia_altimetrica";
		
		return DBUtil.jdbcTemplate.query(query, new FasciaAltimetricaMapper());
	}

}
