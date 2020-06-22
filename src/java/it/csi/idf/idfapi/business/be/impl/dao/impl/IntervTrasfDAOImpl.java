package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.dto.IntervTrasf;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class IntervTrasfDAOImpl implements IntervTrasfDAO {

	@Override
	public void insertIntervTrasf(IntervTrasf intervTrasf) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_interv_trasf(");
		sql.append("fk_intervento, data_inserimento");
		sql.append(") VALUES (?, ?)");
		//sql.append("fk_intervento, data_inserimento, geometria");
		//sql.append(") VALUES (?, ?, ?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(intervTrasf.getFkIntervento());
		parameters.add(intervTrasf.getDataInserimento() == null ? null : Date.valueOf(intervTrasf.getDataInserimento()));
		//parameters.add(intervTrasf.getGeometria());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

}
