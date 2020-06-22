package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.SoggettoIntervento;

public class SoggettoInterventoMapper  implements RowMapper<SoggettoIntervento> {

	@Override
	public SoggettoIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();
		
		soggettoIntervento.setIdIntervento(rs.getInt("id_intervento"));
		soggettoIntervento.setIdSoggetto(rs.getInt("id_soggetto"));
		soggettoIntervento.setIdTipoSoggetto(rs.getInt("id_tipo_soggetto"));
		soggettoIntervento.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		soggettoIntervento.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		soggettoIntervento.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		soggettoIntervento.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		soggettoIntervento.setFkConfigUtente(rs.getInt("fk_config_utente"));
		soggettoIntervento.setFkTipoTitolarita(rs.getInt("fk_tipo_titolarita"));
		
		return soggettoIntervento;
	}
}
