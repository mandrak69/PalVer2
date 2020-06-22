package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ZonaAltimetricaDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ZonaAltimetricaDAOImpl implements ZonaAltimetricaDAO {

	@Override
	public Integer getMaxOccurencesOfFkParamtrasf(List<String[]> sezioniFogli) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT fk_paramtrasf_zona_altimetrica FROM (");
		sql.append("SELECT fk_paramtrasf_zona_altimetrica, COUNT(fk_paramtrasf_zona_altimetrica) AS countParam");
		sql.append(" FROM idf_geo_pl_zona_altimetrica WHERE ");
		
		for(int i = 0; i < sezioniFogli.size(); i++) {
			sql.append("(sezione = '");
			sql.append(sezioniFogli.get(i)[0]);
			sql.append("' AND foglio = ");
			sql.append(sezioniFogli.get(i)[1]);
			sql.append(")");
			if(i != sezioniFogli.size() - 1) {
				sql.append(" OR ");
			}
		}
		sql.append(" GROUP BY fk_paramtrasf_zona_altimetrica ORDER BY countParam DESC) AS x LIMIT 1");

		return DBUtil.jdbcTemplate.queryForInt(sql.toString());
	}
}
