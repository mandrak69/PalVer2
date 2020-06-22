package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;
import it.csi.idf.idfapi.mapper.PropcatastoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PropcatastoInterventoDAOImpl implements PropcatastoInterventoDAO{

	@Override
	public List<PropcatastoIntervento> getAllPropcatastoByIdIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idgeo_pl_prop_catasto, id_intervento, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente");
		sql.append(" FROM idf_r_propcatasto_intervento");
		sql.append(" WHERE id_intervento = ?");

		return DBUtil.jdbcTemplate.query(sql.toString(), new PropcatastoInterventoMapper(), idIntervento);
	}

	@Override
	public void insertPropcatastoIntervento(PropcatastoIntervento propcatastoIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_propcatasto_intervento(");
		sql.append("idgeo_pl_prop_catasto, id_intervento, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente");
		sql.append(") VALUES(?, ?, ?, ?, ?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(propcatastoIntervento.getIdgeoPlPropCatasto());
		parameters.add(propcatastoIntervento.getIdIntervento());
		parameters.add(propcatastoIntervento.getDataInserimento() == null ? null
				: Date.valueOf(propcatastoIntervento.getDataInserimento()));
		parameters.add(propcatastoIntervento.getDataAggiornamento() == null ? null
				: Date.valueOf(propcatastoIntervento.getDataAggiornamento()));
		parameters.add(propcatastoIntervento.getFkConfigUtente());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void deletePropcatastoIntervento(int idgeoPlPropCatasto, int idIntervento) {
		String sql = "DELETE FROM idf_r_propcatasto_intervento WHERE idgeo_pl_prop_catasto = ? AND id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idgeoPlPropCatasto, idIntervento);
	}
}
