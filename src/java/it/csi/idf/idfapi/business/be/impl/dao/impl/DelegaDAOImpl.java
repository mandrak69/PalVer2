package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.mapper.DelegaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DelegaDAOImpl implements DelegaDAO {
	
	private final DelegaMapper delegaMapper = new DelegaMapper();
	
	@Override
	public Delega getByConfigUtente(Integer fkConfigUtente) {
		String sql = "SELECT * FROM idf_cnf_delega WHERE id_config_utente = ? ORDER BY data_utilizzo DESC";
		List<Delega> delegas = DBUtil.jdbcTemplate.query(sql, delegaMapper, fkConfigUtente);
		
		if(delegas.isEmpty()) {
			return null;
		} else {
			return delegas.get(0);
		}
	}
	
	@Override
	public Delega getByCfDeleganteAndConfigUtente(String cfDelegante, Integer fkConfigUtente) throws RecordNotUniqueException {
		String sql = "SELECT * FROM idf_cnf_delega WHERE cf_delegante = ? AND id_config_utente = ?";
		List<Delega> delegas = DBUtil.jdbcTemplate.query(sql, delegaMapper,cfDelegante, fkConfigUtente);
		
		if(delegas.isEmpty()) {
			return null;
		} else if(delegas.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return delegas.get(0);
		}
	}
	
	@Override
	public List<Delega> getListByConfigUtente(Integer fkConfigUtente) {
		String sql = "SELECT * FROM idf_cnf_delega WHERE id_config_utente = ? ORDER BY data_utilizzo DESC";
		return DBUtil.jdbcTemplate.query(sql, delegaMapper, fkConfigUtente);
	}

	@Override
	public int createDelega(Delega delega) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_cnf_delega(");
		sql.append("cf_delegante, id_config_utente, data_inizio, data_fine, data_utilizzo)");
		sql.append(" VALUES(?, ?, ?, ?, ?)");
		
		List<Object> params = new ArrayList<>();
		params.add(delega.getCfDelegante());
		params.add(delega.getIdConfigUtente());
		params.add(delega.getDataInizio() == null ? null : Date.valueOf(delega.getDataInizio()));
		params.add(delega.getDataFine() == null ? null : Date.valueOf(delega.getDataFine()));
		params.add(Timestamp.valueOf(LocalDateTime.now()));

		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public void updateDataUtilizzoDelega(String cfDelegante, Integer idConfigUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_cnf_delega SET");
		sql.append(" data_utilizzo = ?");
		sql.append(" WHERE cf_delegante = ? AND id_config_utente = ?");
		
		List<Object> params = new ArrayList<>();
		params.add(Timestamp.valueOf(LocalDateTime.now()));
		params.add(cfDelegante);
		params.add(idConfigUtente);

		DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public List<Delega> getMieiDelegati(Integer fkConfigUtente) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_cnf_delega");
		sql.append(" WHERE id_config_utente = ?");
		sql.append(" AND data_fine IS NULL");
		sql.append(" ORDER BY data_utilizzo");
		return DBUtil.jdbcTemplate.query(sql.toString(), delegaMapper, fkConfigUtente);
	}
}
