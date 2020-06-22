package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PropCatasto;

public class PropCatastoMapper implements RowMapper<PropCatasto> {

	@Override
	public PropCatasto mapRow(ResultSet rs, int arg1) throws SQLException {
		PropCatasto propCatasto = new PropCatasto();
		
		propCatasto.setIdGeoPlPropCatasto(rs.getInt("idgeo_pl_prop_catasto"));
		propCatasto.setIdGeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		propCatasto.setFkComune(rs.getInt("fk_comune"));
		propCatasto.setSezione(rs.getString("sezione"));
		propCatasto.setFoglio(rs.getInt("foglio"));
		propCatasto.setAllegatoCatasto(rs.getString("allegato_catasto"));
		propCatasto.setSviluppoCatasto(rs.getString("sviluppo_catasto"));
		propCatasto.setParticella(rs.getString("particella"));
		propCatasto.setSupCatastaleMq(rs.getBigDecimal("sup_catastale_mq"));
		propCatasto.setSupCartograficaHa(rs.getBigDecimal("sup_cartografica_ha"));
		propCatasto.setFkProprieta(rs.getInt("fk_proprieta"));
		propCatasto.setIntestato(rs.getString("intestato"));
		propCatasto.setQualitaColtura(rs.getString("qualita_coltura"));
		propCatasto.setFlgUsiCivici(rs.getByte("flg_usi_civici"));
		propCatasto.setFlgPossessiContest(rs.getByte("flg_possessi_contest"));
		propCatasto.setFlgLivellari(rs.getByte("flg_livellari"));
		propCatasto.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		propCatasto.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		propCatasto.setNote(rs.getString("note"));
		propCatasto.setDataAggiornamentoDatocatast(
				rs.getDate("data_aggiornamento_datocatast") == null ? null : rs.getDate("data_aggiornamento_datocatast").toLocalDate());
		propCatasto.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		//propCatasto.setGeometria();
		propCatasto.setFkConfigUtente(rs.getInt("fk_config_utente"));
		
		return propCatasto;
	}
}
