package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.EventoDTO;

public class EventoDTOMapper implements RowMapper<EventoDTO>{

	@Override
	public EventoDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		EventoDTO evento = new EventoDTO();
		
		evento.setIdEvento(rs.getInt("id_evento"));
		evento.setProgressivoEventoPfa(rs.getInt("progressivo_evento_pfa"));
		evento.setNomeBreve(rs.getString("nome_breve"));
		evento.setDataEvento(rs.getDate("data_evento") == null ? null : rs.getDate("data_evento").toLocalDate());
		evento.setIdgeoPlParticelaForest(rs.getInt("idgeo_pl_particella_forest"));
		evento.setDenominazioneParticella(rs.getString("denominazione_particella"));
		evento.setTipoEvento(rs.getInt("fk_tipo_evento"));
		evento.setDescrEvento(rs.getString("descr_evento"));
		evento.setLocalita(rs.getString("localita"));
		evento.setSupInteressataHa(rs.getBigDecimal("sup_interessata_ha"));
		evento.setPercDanno(rs.getInt("perc_danno"));
		evento.setAnnataSilvana(rs.getInt("annata_silvana"));
		
		return evento;
	}
}