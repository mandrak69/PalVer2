package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IntervselEventoDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class IntervselEventoDAOImpl implements IntervselEventoDAO {
	
	final static Logger logger = Logger.getLogger(IntervselEventoDAOImpl.class);

	@Override
	public int createIntervselEvento(Integer idEvento, Integer idIntervento) {

		String sql = "INSERT INTO idf_r_intervselv_evento(\r\n" + 
				"	id_evento, id_intervento)\r\n" + 
				"	VALUES (?, ?);";
		
		List<Object>parameters = new ArrayList<>();
		parameters.add(idEvento);
		parameters.add(idIntervento);
		
		logger.info("into createIntervselvEvento");
		
		return DBUtil.jdbcTemplate.update(sql,parameters.toArray());
	}

	@Override
	public void deleteIntervselEvento(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_r_intervselv_evento WHERE id_intervento = ? ";
		
		DBUtil.jdbcTemplate.update(sql, idIntervento);
		
	}

}
