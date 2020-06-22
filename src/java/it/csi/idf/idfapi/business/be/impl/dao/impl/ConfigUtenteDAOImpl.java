package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.mapper.ConfigUtenteMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ConfigUtenteDAOImpl implements ConfigUtenteDAO {

	@Override
	public ConfigUtente getCofigUtenteById(int id) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.id_config_utente = ? ";
		return DBUtil.jdbcTemplate.queryForObject(sql, new ConfigUtenteMapper(), id);
	}

	@Override
	public int createConfigUtente(ConfigUtente configUtente) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO idf_cnf_config_utente(");
		sb.append("fk_profilo_utente, fk_tipo_accreditamento, nr_accessi, data_primo_accesso, data_ultimo_accesso, flg_privacy, fk_soggetto)");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		List<Object> parameters = new ArrayList<>();
        parameters.add(configUtente.getFkProfiloUtente());
        parameters.add(configUtente.getFkTipoAccreditamento());
        parameters.add(configUtente.getNrAccessi());
        parameters.add(configUtente.getDataPrimoAccesso());
        parameters.add(configUtente.getDataUltimoAccesso());
        parameters.add(configUtente.getFlgPrivacy());
        parameters.add(configUtente.getFkSoggetto());
		
		return AIKeyHolderUtil.updateWithGenKey("id_config_utente", sb.toString(), parameters);
	}

	@Override
	public void updateConfigUtente(ConfigUtente configUtente) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE idf_cnf_config_utente SET");
		sql.append(" fk_profilo_utente = ?, fk_tipo_accreditamento = ?, nr_accessi = ?");
		sql.append(", data_primo_accesso = ?, data_ultimo_accesso = ?, flg_privacy = ?, fk_soggetto = ?");
		sql.append(" where id_config_utente = ?");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(configUtente.getFkProfiloUtente());
		parameters.add(configUtente.getFkTipoAccreditamento());
		parameters.add(configUtente.getNrAccessi());
		parameters.add(configUtente.getDataPrimoAccesso());
		parameters.add(configUtente.getDataUltimoAccesso());
		parameters.add(configUtente.getFlgPrivacy());
		parameters.add(configUtente.getFkSoggetto());
		parameters.add(configUtente.getIdConfigUtente());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}
}
