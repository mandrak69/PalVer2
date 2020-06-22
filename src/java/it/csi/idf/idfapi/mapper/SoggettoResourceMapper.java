package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.SoggettoResource;

public class SoggettoResourceMapper implements RowMapper<SoggettoResource> {

	@Override
	public SoggettoResource mapRow(ResultSet rs, int arg1) throws SQLException {
		SoggettoResource soggettoResource = new SoggettoResource();
		
		soggettoResource.setIdSoggetto(rs.getInt("id_soggetto"));
		soggettoResource.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggettoResource.setPartitaIva(rs.getString("partita_iva"));
		soggettoResource.setNome(rs.getString("nome"));
		soggettoResource.setCognome(rs.getString("cognome"));
		soggettoResource.setDenominazione(rs.getString("denominazione"));
		
		return soggettoResource;
	}
}
