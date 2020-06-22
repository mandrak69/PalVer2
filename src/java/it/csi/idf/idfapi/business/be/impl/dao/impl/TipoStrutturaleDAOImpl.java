package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoStrutturaleDAO;
import it.csi.idf.idfapi.dto.TipoStrutturale;
import it.csi.idf.idfapi.mapper.TipoStrutturaleMapper;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class TipoStrutturaleDAOImpl implements TipoStrutturaleDAO {
	
	@Override
	public List<TipoStrutturale> findAllTipoStrutturale() {
		String SQL = "SELECT * FROM idf_d_tipo_strutturale";
		return DBUtil.jdbcTemplate.query(SQL, new TipoStrutturaleMapper());	
	}


}
