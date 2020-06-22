package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IntervTrasformazione;

public class IntervTrasformazioneMapper implements RowMapper<IntervTrasformazione> {

	@Override
	public IntervTrasformazione mapRow(ResultSet rs, int arg1) throws SQLException {
		IntervTrasformazione intervTrasformazione = new IntervTrasformazione();

		intervTrasformazione.setIdIntervento(rs.getInt("id_intervento"));
		intervTrasformazione.setFlgCompensazione(rs.getString("flg_compensazione"));
		intervTrasformazione.setFlgArt7A(rs.getByte("flg_art7_a"));
		intervTrasformazione.setFlgArt7B(rs.getByte("flg_art7_b"));
		intervTrasformazione.setFlgArt7C(rs.getByte("flg_art7_c"));
		intervTrasformazione.setFlgArt7D(rs.getByte("flg_art7_d"));
		intervTrasformazione.setFlgArt7Dbis(rs.getByte("flg_art7_d_bis"));
		intervTrasformazione.setFlgProprieta(rs.getByte("flg_proprieta"));
		intervTrasformazione.setFlgDissensi(rs.getByte("flg_dissensi"));
		intervTrasformazione.setFlgAutPaesaggistica(rs.getByte("flg_aut_paesaggistica"));
		intervTrasformazione.setDataAutPaesaggistica(rs.getDate("data_aut_paesaggistica") == null ? null
				: rs.getDate("data_aut_paesaggistica").toLocalDate());
		intervTrasformazione.setNrAutPaesaggistica(rs.getString("nr_aut_paesaggistica"));
		intervTrasformazione.setEnteAutPaesaggistica(rs.getString("ente_aut_paesaggistica"));
		intervTrasformazione.setFlgVincIdro(rs.getByte("flg_vinc_idro"));
		intervTrasformazione.setFlgAutVidro(rs.getByte("flg_aut_vidro"));
		intervTrasformazione.setDataAutVidro(
				rs.getDate("data_aut_vidro") == null ? null : rs.getDate("data_aut_vidro").toLocalDate());
		intervTrasformazione.setNrAutVidro(rs.getString("nr_aut_vidro"));
		intervTrasformazione.setFlgAutIncidenza(rs.getByte("flg_aut_incidenza"));
		intervTrasformazione.setDataAutIncidenza(
				rs.getDate("data_aut_incidenza") == null ? null : rs.getDate("data_aut_incidenza").toLocalDate());
		intervTrasformazione.setNrAutIncidenza(rs.getString("nr_aut_incidenza"));
		intervTrasformazione.setEnteAutIncidenza(rs.getString("ente_aut_incidenza"));
		intervTrasformazione.setEnteAutVidro(rs.getString("ente_aut_vidro"));
		intervTrasformazione.setFlgCauzioneCf(rs.getByte("flg_cauzione_cf"));
		intervTrasformazione.setFlgVersamentoCm(rs.getByte("flg_versamento_cm"));
		intervTrasformazione.setAltriPareri(rs.getString("altri_pareri"));
		intervTrasformazione.setNoteDichiarazione(rs.getString("note_dichiarazione"));
		intervTrasformazione.setCompenzazioneEuro(rs.getBigDecimal("compenzazione_euro"));
		intervTrasformazione.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		intervTrasformazione.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		intervTrasformazione.setFkConfigUtente(rs.getInt("fk_config_utente"));

		return intervTrasformazione;
	}
}
