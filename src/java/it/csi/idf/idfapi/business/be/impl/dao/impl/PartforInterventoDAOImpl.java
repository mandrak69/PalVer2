package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PartforInterventoDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PartforInterventoDAOImpl implements PartforInterventoDAO {
	
	final static Logger logger = Logger.getLogger(PartforInterventoDAOImpl.class);

	@Override
	public int createParforInterv(Integer idParticelaForestale, Integer idIntervento) {

		String sql = "INSERT INTO idf_r_partfor_intervento(\r\n" + 
				"	id_intervento, idgeo_pl_particella_forest, data_inizio_validita)\r\n" + 
				"	VALUES (?, ?, ?)";
		
        List<Object>parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(idParticelaForestale);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		
		logger.info("into partForInterventoDAO");
		
		return DBUtil.jdbcTemplate.update(sql,parameters.toArray());
		
	}

	@Override
	public void deletePartforIntervento(Integer idIntervento) {
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM idf_r_partfor_intervento ");
		sql.append("WHERE id_intervento = ? ");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}


}
