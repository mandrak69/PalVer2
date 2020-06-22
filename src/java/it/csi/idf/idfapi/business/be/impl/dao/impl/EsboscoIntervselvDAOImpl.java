package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.EsboscoIntervselvDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class EsboscoIntervselvDAOImpl implements EsboscoIntervselvDAO {
	
	final static Logger logger = Logger.getLogger(EsboscoIntervselvDAOImpl.class);


	@Override
	public int createIntervselvEsbosco(String codEsbosco, Integer idIntervento) {

        StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO idf_r_intervselv_esbosco(");
		query.append("id_intervento, cod_esbosco) ");
		query.append("VALUES (?, ? )");
		
        List<Object>parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(codEsbosco);
		
		logger.info("into createInterselvEsbosco");
		
		return DBUtil.jdbcTemplate.update(query.toString(),parameters.toArray());
		
	}

	@Override
	public void deleteIntervselvEsbosco(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_r_intervselv_esbosco WHERE id_intervento = ?";
		
		DBUtil.jdbcTemplate.update(sql,idIntervento);
		
	}

}
