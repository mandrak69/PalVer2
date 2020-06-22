package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.mapper.InterventoMapper;
import it.csi.idf.idfapi.mapper.InterventoPianoMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;

@Component
public class InterventoDAOImpl extends GenericDAO implements InterventoDAO {

	static final Logger logger = Logger.getLogger(InterventoDAOImpl.class);
	
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	
	private final InterventoPianoMapper interventoPianoMapper =  new InterventoPianoMapper();
	private final InterventoMapper interventoMapper =  new InterventoMapper();
	
	@Override
	public List<Intervento> findAllInterventi() {
		
		String sql = "SELECT * from idf_t_intervento";
		return DBUtil.jdbcTemplate.query(sql, interventoMapper);
	}

	@Override
	public InterventoPiano findInterventoPianoByID(Integer idIntervento) {
		
        StringBuilder sql = getGeoPlPfaMainQuery();
		sql.append(" WHERE interv.id_intervento = ?");
		geoPlPfaQueryGroupBy(sql);
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), interventoPianoMapper, idIntervento);
	}
	
	@Override
	public int createInterventoWithConfigUtente(int fkConfigUtente) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_t_intervento(");
		sql.append("data_inserimento, fk_config_utente_compilatore");
		sql.append(") VALUES(?, ?)");
		
		List<Object> params = new ArrayList<>();
		params.add(Date.valueOf(LocalDate.now()));
		params.add(fkConfigUtente);
		
		return AIKeyHolderUtil.updateWithGenKey("id_intervento", sql.toString(), params);
	}

	@Override
	public int createIntervento(TipoInterventoDatiTecnici interventoDatiTehnici) throws DuplicateRecordException {
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_t_intervento ");
		sql.append("(descrizione_intervento, localita, superficie_interessata, fk_config_utente_compilatore) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(interventoDatiTehnici.getDescrizione());
		parameters.add(interventoDatiTehnici.getLocalita());
		parameters.add(interventoDatiTehnici.getSuperficieInteressata());
		parameters.add(1);//mocked because is notnull in database
		
		return AIKeyHolderUtil.updateWithGenKey("id_intervento", sql.toString(), parameters);	
	}

	@Override
	public void updateIntervento(TipoInterventoDatiTecnici interventoDatiTehnici, Integer idIntervento, int fkConfigUtente) throws RecordNotFoundException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE idf_t_intervento ");
		sql.append("SET  descrizione_intervento= ?,localita= ?, superficie_interessata= ?, fascia_altimetrica=?, "
				+ "fk_config_utente_compilatore= ?,"
				+ " data_inizio_intervento = ? , data_fine_intervento = ?, data_inserimento = ? ");
		sql.append("WHERE id_intervento = ?");
		
        List<Object>parameters = new ArrayList<>();
		
		parameters.add(interventoDatiTehnici.getDescrizione());
		parameters.add(interventoDatiTehnici.getLocalita());
		parameters.add(interventoDatiTehnici.getSuperficieInteressata());
		parameters.add(interventoDatiTehnici.getFasciaAltimetrica());
		parameters.add(fkConfigUtente);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(java.sql.Date.valueOf(LocalDate.now().plusMonths(3).plusDays(12)));
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		
		logger.info("Data in intervento is updated");
	}
	
	@Override
	public Intervento findInterventoByIdIntervento(int idIntervento) {
		String sql = "SELECT * from idf_t_intervento WHERE id_intervento = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, interventoMapper, idIntervento);
	}

	@Override
	public void updateInterventoWithSuperficieInteressata(Integer idIntervento, BigDecimal superficieInteressata) {
		String sql = "UPDATE idf_t_intervento SET superficie_interessata = ? WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, superficieInteressata, idIntervento);
	}

	@Override
	public void updateInterventoWithFkSoggettoProfess(int idIntervento, int fkSoggettoProfess) {
		String sql = "UPDATE idf_t_intervento SET fk_soggetto_profess = ? WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, fkSoggettoProfess, idIntervento);
	}

	@Override
	public void deleteIntervento(Integer idIntervento) {

		String sql = "DELETE FROM idf_t_intervento WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}
	
	@Override
	public PaginatedList<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa, int page, int limit, String sort) {
		StringBuilder sql = getGeoPlPfaMainQuery();
		sql.append(" WHERE intervSel.idgeo_pl_pfa = ? ");
		geoPlPfaQueryGroupBy(sql);
		sql.append(getQuerySortingSegment(sort));
		
		return super.paginatedList(sql.toString(), Arrays.asList(idGeoPlPfa), interventoPianoMapper, page, limit);
	}
	
	@Override
	public List<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa) {
		StringBuilder sql = getGeoPlPfaMainQuery();
		sql.append(" WHERE intervSel.idgeo_pl_pfa = ? ");
		geoPlPfaQueryGroupBy(sql);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), interventoPianoMapper, idGeoPlPfa);
	}
	
	private StringBuilder getGeoPlPfaMainQuery() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT intervSel.id_intervento, plpfa.denominazione, intervSel.nr_progressivo_interv, intervSel.annata_silvana");
		sql.append(", ARRAY_AGG(partfor.idgeo_pl_particella_forest) AS idgeo_pl_particella_forest");
		sql.append(", ARRAY_AGG(forest.denominazione_particella) AS denominazione_particella");
		sql.append(", interv.data_inizio_intervento, interv.data_fine_intervento, interv.descrizione_intervento");
		sql.append(", interv.localita, interv.superficie_interessata, intervSel.m3_prelevati, stato.descr_stato_intervento, intervSel.flg_istanza_taglio");
		sql.append(" FROM idf_t_interv_selvicolturale intervSel");
		sql.append(" INNER JOIN idf_t_intervento interv ON intervSel.id_intervento = interv.id_intervento");
		sql.append(" LEFT JOIN idf_d_stato_intervento stato ON intervSel.fk_stato_intervento = stato.id_stato_intervento");
		sql.append(" LEFT JOIN idf_r_partfor_intervento partfor ON partfor.id_intervento = intervSel.id_intervento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest forest ON partfor.idgeo_pl_particella_forest = forest.idgeo_pl_particella_forest");
		sql.append(" LEFT JOIN  idf_geo_pl_pfa plpfa ON forest.idgeo_pl_pfa = plpfa.idgeo_pl_pfa");
		//add geoPlPfaQueryGroupBy(sql) in calling method
		return sql;
	}
	
	private void geoPlPfaQueryGroupBy(StringBuilder sql) {
		sql.append(" GROUP BY intervSel.id_intervento, interv.data_inizio_intervento, interv.data_fine_intervento");
		sql.append(" , interv.descrizione_intervento, interv.localita, interv.superficie_interessata, stato.descr_stato_intervento,plpfa.denominazione ");
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
		mappedColumnNames.put("nIntervento", "nr_progressivo_interv");
		mappedColumnNames.put("annataSilvana", "annata_silvana");
		mappedColumnNames.put("nParticelaForestale", "idgeo_pl_particella_forest");
		mappedColumnNames.put("dataInizioString", "data_inizio_intervento");
		mappedColumnNames.put("dataFineString", "data_fine_intervento");
		mappedColumnNames.put("descrizione", "descrizione_intervento");
		mappedColumnNames.put("localita", "localita");
		mappedColumnNames.put("superficieInteressata", "superficie_interessata");
		mappedColumnNames.put("m3Prelevati", "m3_prelevati");
		mappedColumnNames.put("descrStatoIntervento", "descr_stato_intervento");
		mappedColumnNames.put("comunicazioneDiTaglio", "flg_istanza_taglio");
		
		return mappedColumnNames.get(fieldName) != null ? mappedColumnNames.get(fieldName) : "nr_progressivo_interv";
	}
}