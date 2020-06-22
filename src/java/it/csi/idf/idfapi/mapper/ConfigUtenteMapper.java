package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ConfigUtente;

public class ConfigUtenteMapper implements RowMapper<ConfigUtente> {

	@Override
	public ConfigUtente mapRow(ResultSet rs, int arg1) throws SQLException {
		ConfigUtente configUtente = new ConfigUtente();
		
		configUtente.setIdConfigUtente(rs.getInt("id_config_utente"));
		configUtente.setFkProfiloUtente(rs.getInt("fk_profilo_utente"));
		configUtente.setFkTipoAccreditamento(rs.getInt("fk_tipo_accreditamento"));
		configUtente.setNrAccessi(rs.getInt("nr_accessi"));
		configUtente.setDataPrimoAccesso(rs.getTimestamp("data_primo_accesso"));
		configUtente.setDataUltimoAccesso(rs.getTimestamp("data_ultimo_accesso"));
		configUtente.setFlgPrivacy(rs.getByte("flg_privacy"));
		configUtente.setFkSoggetto(rs.getInt("fk_soggetto"));
		
		return configUtente;
	}

}
