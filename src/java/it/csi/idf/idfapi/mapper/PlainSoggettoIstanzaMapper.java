package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanza;
import it.csi.idf.idfapi.dto.SoggettoResource;

public class PlainSoggettoIstanzaMapper implements RowMapper<PlainSoggettoIstanza> {

	@Override
	public PlainSoggettoIstanza mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainSoggettoIstanza plainSoggettoIstanza = new PlainSoggettoIstanza();
		
		plainSoggettoIstanza.setIdIntervento(rs.getInt("id_istanza_intervento"));
		plainSoggettoIstanza.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		plainSoggettoIstanza.setDataInvio(rs.getDate("data_invio") == null ? null : rs.getDate("data_invio").toLocalDate());
		
		SoggettoResource soggettoResource = new SoggettoResource();
		soggettoResource.setIdSoggetto(rs.getInt("id_soggetto"));
		soggettoResource.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggettoResource.setPartitaIva(rs.getString("partita_iva"));
		soggettoResource.setNome(rs.getString("nome"));
		soggettoResource.setCognome(rs.getString("cognome"));
		soggettoResource.setDenominazione(rs.getString("denominazione"));
		plainSoggettoIstanza.setRichiedente(soggettoResource);
		
		ComuneResource comuneResource = new ComuneResource();
		comuneResource.setIdComune(rs.getInt("id_comune"));
		comuneResource.setIstatComune(rs.getString("istat_comune"));
		comuneResource.setIstatProv(rs.getString("istat_prov"));
		comuneResource.setDenominazioneComune(rs.getString("denominazione_comune"));
		plainSoggettoIstanza.setComune(comuneResource);
		
		plainSoggettoIstanza.setStato(rs.getString("descr_stato_istanza"));
		
		return plainSoggettoIstanza;
	}

}
