package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoInterventoDAO;
import it.csi.idf.idfapi.dto.SoggettoIntervento;
import it.csi.idf.idfapi.mapper.SoggettoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SoggettoInterventoDAOImpl implements SoggettoInterventoDAO {

	@Override
	public int createSoggettoIntervento(SoggettoIntervento soggettoIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_soggetto_intervento(");
		sql.append("id_intervento, id_soggetto, id_tipo_soggetto, data_inizio_validita, data_fine_validita, ");
		sql.append("data_inserimento, data_aggiornamento, fk_config_utente, fk_tipo_titolarita)");
		sql.append("VALUES(?,?,?,?,?,?,?,?, ?)");
		
		List<Object> parameters = new ArrayList<>();
		
        parameters.add(soggettoIntervento.getIdIntervento());
        parameters.add(soggettoIntervento.getIdSoggetto());
        parameters.add(soggettoIntervento.getIdTipoSoggetto());
		parameters.add(soggettoIntervento.getDataInizioValidita() == null ? null
				: Date.valueOf(soggettoIntervento.getDataInizioValidita()));
		parameters.add(soggettoIntervento.getDataFineValidita() == null ? null
				: Date.valueOf(soggettoIntervento.getDataFineValidita()));
		parameters.add(soggettoIntervento.getDataInserimento() == null ? null
				: Date.valueOf(soggettoIntervento.getDataInserimento()));
		parameters.add(soggettoIntervento.getDataAggiornamento() == null ? null
				: Date.valueOf(soggettoIntervento.getDataAggiornamento()));
		parameters.add(soggettoIntervento.getFkConfigUtente());
		parameters.add(soggettoIntervento.getFkTipoTitolarita());
		
        return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public SoggettoIntervento getSoggettoInterventoById(Integer idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException {
		
		String sql = "SELECT * FROM idf_r_soggetto_intervento WHERE id_intervento = ?";

		List<SoggettoIntervento> interventos = DBUtil.jdbcTemplate.query(sql, new SoggettoInterventoMapper(),
				idIntervento);

		if (interventos.isEmpty()) {
			throw new RecordNotFoundException();
		} else if (interventos.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return interventos.get(0);
		}
	}

	@Override
	public void updateWithIdSoggettoAndFkTipoTitolarita(int idSoggetto, int tipoTitolarita, int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_soggetto_intervento SET");
		sql.append(" id_soggetto = ?, fk_tipo_titolarita = ?, data_aggiornamento = ?");
		sql.append(" WHERE id_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idSoggetto, tipoTitolarita, Date.valueOf(LocalDate.now()), idIntervento);
	}
}
