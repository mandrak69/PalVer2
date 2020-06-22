package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IstanzaForest;

public class IstanzaForestMapper implements RowMapper<IstanzaForest>{

	@Override
	public IstanzaForest mapRow(ResultSet rs, int arg1) throws SQLException {
		IstanzaForest istanzaForest = new IstanzaForest();
		
		istanzaForest.setIdIstanzaIntervento(rs.getInt("id_istanza_intervento"));
		istanzaForest.setFkSoggSettoreRegionale(rs.getInt("fk_sogg_settore_regionale"));
		istanzaForest.setFkStatoIstanza(rs.getInt("fk_stato_istanza"));
		istanzaForest.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		istanzaForest.setDataInvio(rs.getDate("data_invio") == null ? null : rs.getDate("data_invio").toLocalDate());
		istanzaForest.setDataVerifica(rs.getDate("data_verifica") == null ? null : rs.getDate("data_verifica").toLocalDate());
		istanzaForest.setDataProtocollo(rs.getDate("data_protocollo") == null ? null : rs.getDate("data_protocollo").toLocalDate());
		istanzaForest.setNrProtocollo(rs.getString("nr_protocollo"));
		istanzaForest.setDataUltAgg(rs.getDate("data_ult_agg") == null ? null : rs.getDate("data_ult_agg").toLocalDate());
		istanzaForest.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		istanzaForest.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		istanzaForest.setFkConfigUtente(rs.getInt("fk_config_utente"));
		istanzaForest.setFkTipoIstanza(rs.getInt("fk_tipo_istanza"));
		
		return istanzaForest;
	}

}
