package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.UsoviabInterventoselvDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class UsoviabInterventoselvDAOImpl implements UsoviabInterventoselvDAO {
	
	final static Logger logger = Logger.getLogger(UsoviabInterventoselvDAOImpl.class);

	@Override
	public int createIntervselUsovib(Integer fkUsoViabilita, Integer idIntervento) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO idf_r_interventoselv_usoviab(");
		query.append("id_intervento, fk_uso_viabilita) ");
		query.append("VALUES (?, ? )");
		
        List<Object>parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(fkUsoViabilita);
		
		logger.info("into create usoviabInterselv");
		
		return DBUtil.jdbcTemplate.update(query.toString(),parameters.toArray());
	}

	@Override
	public void deleteIntervselUsovib(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_r_interventoselv_usoviab WHERE id_intervento = ?";
		
		DBUtil.jdbcTemplate.update(sql,idIntervento);
	}

}
