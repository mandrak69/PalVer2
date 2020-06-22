package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;
import it.csi.idf.idfapi.dto.ProgressivoNomeBreve;
import it.csi.idf.idfapi.mapper.EventoDTOMapper;
import it.csi.idf.idfapi.mapper.EventoPianoMapper;
import it.csi.idf.idfapi.mapper.PlainSecondoPfaEventoMapper;
import it.csi.idf.idfapi.mapper.ProgressivoNomeBreveMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.TipoEventoEnum;

@Component
public class EventoDAOImpl extends GenericDAO implements EventoDAO {
	
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	
	private static final String[] eventoPianoColumns = new String[] {
			"evento.id_evento", "plpfa.denominazione", "evento.progressivo_evento_pfa", "evento.nome_breve", "evento.data_evento"
			, "ARRAY_AGG(parFore.idgeo_pl_particella_forest) as idgeo_pl_particella_forest"
			, "ARRAY_AGG(parFore.denominazione_particella) as denominazione_particella"
			, "evento.fk_tipo_evento", "evento.descr_evento", "evento.localita", "evento.sup_interessata_ha"};
	
	private final EventoDTOMapper eventoMapper = new EventoDTOMapper();
	private final EventoPianoMapper eventoPianoMapper = new EventoPianoMapper();
	private final PlainSecondoPfaEventoMapper secondoPfaEventomapper = new PlainSecondoPfaEventoMapper();
	private final ProgressivoNomeBreveMapper progressivoNomeBreveMapper = new ProgressivoNomeBreveMapper();

	@Override
	public List<EventoDTO> findAllEventi() {
		
		return DBUtil.jdbcTemplate.query(getEventoMainQuery().toString(), eventoMapper);
	}

	@Override
	public EventoDTO findEventoById(Integer idEvento) throws RecordNotFoundException {
		
		StringBuilder sql = getEventoMainQuery();
		sql.append(" WHERE evento.id_evento = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), eventoMapper, idEvento);
	}

	@Override
	public int createEvento(EventoDTO evento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_evento(");
		sql.append("fk_tipo_evento, nome_breve, data_evento, progressivo_evento_pfa");
		sql.append(", descr_evento, localita, sup_interessata_ha, annata_silvana)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(evento.getTipoEvento());
		parameters.add(evento.getNomeBreve());
		parameters.add(evento.getDataEvento() == null ? null : Date.valueOf(evento.getDataEvento()));
		parameters.add(evento.getProgressivoEventoPfa());
		parameters.add(evento.getDescrEvento());
		parameters.add(evento.getLocalita());
		parameters.add(evento.getSupInteressataHa());
		parameters.add(evento.getAnnataSilvana());
		
		return AIKeyHolderUtil.updateWithGenKey("id_evento", sql.toString(), parameters);
	}

	@Override
	public void updateEvento(PlainSecondoPfaEvento evento, Integer idEvento) throws RecordNotFoundException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE idf_t_evento ");
		sql.append("SET fk_tipo_evento = ?, nome_breve = ?, data_evento = ?");
		sql.append(", descr_evento = ?, localita = ?, sup_interessata_ha = ?, annata_silvana = ? ");
		sql.append(" WHERE id_evento = ? ");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(evento.getTipoEvento());
		parameters.add(evento.getNomeBreve());
		parameters.add(evento.getDataEvento()== null?null:java.sql.Date.valueOf(evento.getDataEvento()));
		parameters.add(evento.getDescrEvento());
		parameters.add(evento.getLocalita());
		parameters.add(evento.getSupInteressataHa());
		parameters.add(evento.getAnnataSilvana());
		parameters.add(idEvento);
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		
	}

	@Override
	public void deleteEvento(Integer idEvento) throws RecordNotFoundException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM idf_t_evento ");
		sql.append(" WHERE id_evento = ?; ");
		
		DBUtil.jdbcTemplate.update(sql.toString(),idEvento);
		
	}
	
	@Override
	public PaginatedList<EventoPiano> findEventiPianoDTOByIdGeoPlPfa(int idGeoPlPfa, int page, int limit, String sort) {
		StringBuilder sql = getEventoPianoMainQuery(eventoPianoColumns);
		sql.append(" WHERE parFore.idgeo_pl_pfa = ? ").append(" GROUP BY evento.id_evento").append(getQuerySortingSegment(sort));
		
		return super.paginatedList(sql.toString(), Arrays.asList(idGeoPlPfa), eventoPianoMapper, page, limit);
	}
	
	@Override
	public List<EventoPiano> findEventiPianoDTOByIdGeoPlPfa(int idGeoPlPfa) {
		StringBuilder sql = getEventoPianoMainQuery(eventoPianoColumns);
		sql.append(" WHERE parFore.idgeo_pl_pfa = ? ");
		sql.append(" GROUP BY evento.id_evento,plpfa.denominazione");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), eventoPianoMapper, idGeoPlPfa);
	}
	
	@Override
	public int getProssimoProgressivoEventoPfa(TipoEventoEnum tipoEvento) {
		return DBUtil.jdbcTemplate.queryForInt(
				"SELECT COALESCE(MAX(progressivo_evento_pfa), 0) FROM idf_t_evento WHERE fk_tipo_evento = ?",
				tipoEvento.getTypeValue()) + 1;
	}
	
	@Override
	public PlainSecondoPfaEvento findSecondoPfaEventoById(Integer idEvento) {
		StringBuilder sql = getSecondoPfaEventoQuery(eventoPianoColumns);
		sql.append(" WHERE evento.id_evento = ? ");
		sql.append(" GROUP BY evento.id_evento");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), secondoPfaEventomapper, idEvento);
	}

	@Override
	public List<ProgressivoNomeBreve> findProgressiviNomeBreve(int idGeoPlPfa) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT evento.progressivo_evento_pfa, evento.nome_breve, evento.id_evento");
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		sql.append(" WHERE parFore.idgeo_pl_pfa = ? ");
		sql.append(" GROUP BY evento.id_evento");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), progressivoNomeBreveMapper, idGeoPlPfa); 
	}
	
	private StringBuilder getEventoMainQuery() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT evento.id_evento, evento.progressivo_evento_pfa, evento.nome_breve, evento.data_evento");
		sql.append(", parFore.idgeo_pl_particella_forest, parFore.denominazione_particella, evento.fk_tipo_evento");
		sql.append(", evento.descr_evento, evento.localita, evento.sup_interessata_ha, partfor.perc_danno, evento.annata_silvana");
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_d_tipo_evento tipo ON evento.fk_tipo_evento = tipo.id_tipo_evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		
		return sql;
	}
	
	private StringBuilder getEventoPianoMainQuery(String[] columnNames) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(DBUtil.createQueryListOfColumnsForInsert(columnNames));
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_d_tipo_evento tipo ON evento.fk_tipo_evento = tipo.id_tipo_evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		sql.append(" INNER JOIN idf_geo_pl_pfa plpfa ON parFore.idgeo_pl_pfa = plpfa.idgeo_pl_pfa");
		
		return sql;
	}
	
	private StringBuilder getSecondoPfaEventoQuery(String[] columnNames) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(DBUtil.createQueryListOfColumnsForInsert(columnNames));
		sql.append(", ARRAY_AGG(partfor.perc_danno) as perc_danni");
		sql.append(", evento.annata_silvana");
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_d_tipo_evento tipo ON evento.fk_tipo_evento = tipo.id_tipo_evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		
		return sql;
	}

	private static String getQuerySortingSegment(String sort) {
		String sortField = null;
		String sortOrder = null;

		StringBuilder sql = new StringBuilder();
		if (sort != null && !sort.isEmpty()) {
			sortOrder = sort.charAt(0) == ORDER ? DESCENDING : ASCENDING;
			sortField = sortOrder.equals(DESCENDING) ? sort.substring(1) : sort;
			if (sortField != null && !sortField.isEmpty()) {
				sql.append(" ORDER BY ");
				sql.append(translateFieldNameForBackOffice(sortField)).append(" ").append(sortOrder);
			}
		}
		return sql.toString();
	}
	
	private static String translateFieldNameForBackOffice(String fieldName) {
		Map<String, String> mappedColumnNames = new HashMap<>();
		mappedColumnNames.put("idEvento", "id_evento");
		mappedColumnNames.put("nomeBreve", "nome_breve");
		mappedColumnNames.put("dataEvento", "data_evento");
		mappedColumnNames.put("idgeoPlParticelaForest", "idgeo_pl_particella_forest");
		mappedColumnNames.put("tipoEvento", "fk_tipo_evento");
		mappedColumnNames.put("descrEvento", "descr_evento");
		mappedColumnNames.put("localita", "localita");
		mappedColumnNames.put("supInteressataHA", "sup_interessata_ha");
		
		return mappedColumnNames.get(fieldName) != null ? mappedColumnNames.get(fieldName) : "id_evento";
	}
}