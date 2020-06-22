package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.dto.InterventoCatfor;
import it.csi.idf.idfapi.mapper.CategoriaForestaleMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class CategoriaForestaleDAOImpl implements CategoriaForestaleDAO {
	
	@Override
	public List<CategoriaForestale> findAllCategoriaForestale() {
		String sql = "SELECT * FROM idf_d_categoria_forestale";
		return DBUtil.jdbcTemplate.query(sql, new CategoriaForestaleMapper());
		
	}

	@Override
	public CategoriaForestale getByCodiceAmministratico(String codiceAmministrativo) {
		String sql = "SELECT * FROM idf_d_categoria_forestale WHERE codice_amministrativo = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, new CategoriaForestaleMapper(), codiceAmministrativo);
	}

	@Override
	public CategoriaForestale getCategoriaForestaleById(int idCategoriaForestale) {
		String sql = "SELECT * FROM idf_d_categoria_forestale WHERE id_categoria_forestale = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, new CategoriaForestaleMapper(), idCategoriaForestale);
	}

	@Override
	public List<CategoriaForestale> getAllByInterventoCatfors(List<InterventoCatfor> interventoCatfors) {
		
		if(interventoCatfors.isEmpty()) {
			return Collections.emptyList();
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_d_categoria_forestale WHERE id_categoria_forestale IN(");
		
		for(int i = 0; i < interventoCatfors.size(); i++) {
			sql.append(interventoCatfors.get(i).getIdCategoriaForestale());
			if(i != interventoCatfors.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), new CategoriaForestaleMapper());
	}
}
