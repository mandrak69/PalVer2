package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;

public class TipoInterventoDatiTecniciMapper implements RowMapper<TipoInterventoDatiTecnici>{

	@Override
	public TipoInterventoDatiTecnici mapRow(ResultSet rs, int arg1) throws SQLException {

		TipoInterventoDatiTecnici interv = new TipoInterventoDatiTecnici();
		
		interv.setConformeDeroga(
				rs.getString("flg_conforme_deroga").equals(ConformeDerogaEnum.C.toString()) ? ConformeDerogaEnum.C
						: ConformeDerogaEnum.D);
		interv.setProgressivoNumerico(rs.getInt("nr_progressivo_interv"));
		interv.setDescrStatoIntervento(rs.getString("descr_stato_intervento"));
		interv.setDataPresuntaIntervento(
				rs.getDate("data_presa_in_carico") == null ? null : rs.getDate("data_presa_in_carico").toLocalDate());
		interv.setAnnataSilvana(rs.getString("annata_silvana"));
		interv.setIdParticelaForestale((Integer[])rs.getArray("idgeo_pl_particella_forest").getArray());
		interv.setIdEventoCorelato(rs.getInt("id_evento"));
		
		/*
		StringBuilder progressivoNomeBreve = new StringBuilder();
		progressivoNomeBreve.append(rs.getInt("progressivo_evento_pfa"));
		progressivoNomeBreve.append("/");
		progressivoNomeBreve.append(rs.getString("nome_breve"));
		interv.setProgressivoNomeBreveEvento(progressivoNomeBreve.toString());
		*/
		interv.setFkGoverno(rs.getInt("fk_governo"));
		interv.setRichiedePiedilsta(rs.getByte("flg_piedilista"));
		interv.setDescrizione(rs.getString("descrizione_intervento"));
		interv.setLocalita(rs.getString("localita"));
		interv.setSuperficieInteressata(rs.getInt("superficie_interessata"));
		interv.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
		interv.setFasciaAltimetrica(rs.getInt("fascia_altimetrica"));
		interv.setFkFinalitaTaglio(rs.getInt("fk_finalita_taglio"));
		interv.setFkDestLegname(rs.getInt("fk_dest_legname"));
		
		return interv;
	}
}