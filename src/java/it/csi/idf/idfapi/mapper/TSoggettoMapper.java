package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TSoggetto;

public class TSoggettoMapper implements RowMapper<TSoggetto>{

	@Override
	public TSoggetto mapRow(ResultSet rs, int arg1) throws SQLException {
		TSoggetto soggetto = new TSoggetto();
		soggetto.setIdSoggetto(rs.getInt("id_soggetto"));
		soggetto.setFkComune(rs.getInt("fk_comune"));
		soggetto.setNome(rs.getString("nome"));
		soggetto.setCognome(rs.getString("cognome"));
		soggetto.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggetto.setPartitaIva(rs.getString("partita_iva"));
		soggetto.setDenominazione(rs.getString("denominazione"));
		soggetto.setIndirizzo(rs.getString("indirizzo"));
		soggetto.setNrTelefonico(rs.getString("nr_telefonico"));
		soggetto.seteMail(rs.getString("e_mail"));
		soggetto.setPec(rs.getString("pec"));
		soggetto.setnIscrizioneOrdine(rs.getString("n_iscrizione_ordine"));
		soggetto.setIstatProvIscrizioneOrdine(rs.getString("istat_prov_iscrizione_ordine"));
		soggetto.setIstatProvCompetenzaTerr(rs.getString("istat_prov_competenza_terr"));
		soggetto.setFlgSettoreRegionale(rs.getByte("flg_settore_regionale"));
		soggetto.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		soggetto.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		soggetto.setFkConfigUtente(rs.getInt("fk_config_utente"));
		soggetto.setCivico(rs.getString("civico"));
		soggetto.setCap(rs.getString("cap"));
		
		return soggetto;
	}
}
