package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SpecieDAO;
import it.csi.idf.idfapi.dto.GruppoSpecie;
import it.csi.idf.idfapi.dto.TSpecie;
import it.csi.idf.idfapi.mapper.GruppoSpecieMapper;
import it.csi.idf.idfapi.mapper.TSpecieMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TspecieDAOImpl implements SpecieDAO {

	@Override
	public List<TSpecie> findAllSpecie() {
		String SQL = "SELECT * FROM idf_t_specie";
		return DBUtil.jdbcTemplate.query(SQL, new TSpecieMapper());
		
	}
	
	@Override
	public List<GruppoSpecie> findAllGruppoSpecie() {
		String SQL = "SELECT * FROM idf_d_gruppo";
		return DBUtil.jdbcTemplate.query(SQL, new GruppoSpecieMapper());
		
	}

}
