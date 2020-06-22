package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DestinazioneDAO;
import it.csi.idf.idfapi.dto.Destinazione;
import it.csi.idf.idfapi.mapper.DestinazioneMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DestinazioneDAOImpl implements DestinazioneDAO {
	
	@Override
	public List<Destinazione> findAllDestinazione() {
		String SQL = "SELECT * FROM idf_d_destinazione";
		return DBUtil.jdbcTemplate.query(SQL, new DestinazioneMapper());	
	}


}
