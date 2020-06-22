package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IntervSelvicolturale;

public class IntervSelvicolturaleMapper implements RowMapper<IntervSelvicolturale> {

	@Override
	public IntervSelvicolturale mapRow(ResultSet rs, int arg1) throws SQLException {

		IntervSelvicolturale interventoSelvcolt = new IntervSelvicolturale();

		interventoSelvcolt.setIdIntervento(rs.getInt("id_intervento"));
		interventoSelvcolt.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
		interventoSelvcolt.setFkStatoIntervento(rs.getInt("fk_stato_intervento"));
		interventoSelvcolt.setIdgeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		interventoSelvcolt.setFkTipoForestalePrevalente(rs.getInt("fk_tipo_forestale_prevalente"));

		interventoSelvcolt.setFkFinalitaTaglio(rs.getInt("fk_finalita_taglio"));
		interventoSelvcolt.setFkDestLegname(rs.getInt("fk_dest_legname"));
		interventoSelvcolt.setFkFasciaAltimetrica(rs.getInt("fk_fascia_altimetrica"));
		interventoSelvcolt.setFlgIntervNonPrevisto(rs.getByte("flg_interv_non_previsto"));
		interventoSelvcolt.setFkConfigIpla(rs.getInt("fk_config_ipla"));

		interventoSelvcolt.setNrPiante(rs.getInt("nr_piante"));
		interventoSelvcolt.setStimaMassaRetraibileM3(rs.getInt("stima_massa_retraibile_m3"));
		interventoSelvcolt.setM3Prelevati(rs.getInt("m3_prelevati"));
		interventoSelvcolt.setVolumeRamagliaM3(rs.getInt("volume_ramaglia_m3"));

		interventoSelvcolt.setDataPresaInCarico(
				rs.getDate("data_presa_in_carico") == null ? null : rs.getDate("data_presa_in_carico").toLocalDate());
		interventoSelvcolt.setAnnataSilvana(rs.getString("annata_silvana"));
		interventoSelvcolt.setNrProgressivoInterv((Integer)rs.getInt("nr_progressivo_interv") == null ? null : rs.getInt("nr_progressivo_interv"));
		interventoSelvcolt.setFlgIstanzaTaglio(rs.getByte("flg_istanza_taglio"));

		interventoSelvcolt.setFlgPiedilista(rs.getByte("flg_piedilista"));
		interventoSelvcolt.setFlgConformeDeroga(rs.getString("flg_conforme_deroga"));
		interventoSelvcolt.setNoteEsbosco(rs.getString("note_esbosco"));
		interventoSelvcolt.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		interventoSelvcolt.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());

		interventoSelvcolt.setFkConfigUtente(rs.getInt("fk_config_utente"));
		interventoSelvcolt.setRipresaPrevistaMc(rs.getBigDecimal("ripresa_prevista_mc") == null ? null
				: rs.getBigDecimal("ripresa_prevista_mc").setScale(0, RoundingMode.FLOOR));
		interventoSelvcolt.setRipresaRealeFineIntervMc(rs.getBigDecimal("ripresa_reale_fine_interv_mc") == null ? null 
				:rs.getBigDecimal("ripresa_reale_fine_interv_mc").setScale(0, RoundingMode.FLOOR));
		interventoSelvcolt.setFkGoverno(rs.getInt("fk_governo"));

		interventoSelvcolt.setIdUsoViabilita(rs.getInt("fk_uso_viabilita"));
		interventoSelvcolt.setCodEsbosco(rs.getString("cod_esbosco"));

		return interventoSelvcolt;
	}

}
