package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.IstanzaForest;
import it.csi.idf.idfapi.mapper.BOSearchResultMapper;
import it.csi.idf.idfapi.mapper.ConfigUtenteMapper;
import it.csi.idf.idfapi.mapper.IstanzaForestMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.Q;

@Component
public class IstanzaForestDAOImpl extends GenericDAO implements IstanzaForestDAO {

	private static final String COMUNE = "comune";
	private static final String TIP_TRASF = "tipTrasf";
	private static final String UBICAZIONE = "ubicazione";
	private static final String CAT_FOR = "catFor";
	private static final String GOV_FORM = "govForm";
	private static final String CALC_ECON_A = "calcEconA";
	private static final String CALC_ECON_DA = "calcEconDa";
	private static final String DATA_PRES_A = "dataPresA";
	private static final String DATA_PRES_DA = "dataPresDa";
	private static final String VINC_IDRO = "vincIdro";
	private static final String STATO_ISTANZA = "statoIstanza";
	private static final String ANNO_ISTANZA = "annoIstanza";

	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	private final IstanzaForestMapper istanzaMapper = new IstanzaForestMapper();
	private final BOSearchResultMapper boSearchResultMapper = new BOSearchResultMapper();

	@Override
	public int createIstanzaForest(IstanzaForest istanzaForest) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_t_istanza_forest(");
		sql.append("id_istanza_intervento, fk_sogg_settore_regionale, fk_stato_istanza, nr_istanza_forestale");
		sql.append(", data_invio, data_verifica, data_protocollo, nr_protocollo, data_ult_agg, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente, fk_tipo_istanza");
		sql.append(") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		try {
			StringBuilder sqlupit = Q.insertObject("idf_t_istanza_forest",istanzaForest);
			System.out.println("sqlupit."+sqlupit.toString());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Object> params = new ArrayList<>();
		params.add(istanzaForest.getIdIstanzaIntervento());
		params.add(istanzaForest.getFkSoggSettoreRegionale());
		params.add(istanzaForest.getFkStatoIstanza());
		params.add(istanzaForest.getNrIstanzaForestale());
		params.add(istanzaForest.getDataInvio() == null ? null : Date.valueOf(istanzaForest.getDataInvio()));
		params.add(istanzaForest.getDataVerifica() == null ? null : Date.valueOf(istanzaForest.getDataVerifica()));
		params.add(istanzaForest.getDataProtocollo() == null ? null : Date.valueOf(istanzaForest.getDataProtocollo()));
		params.add(istanzaForest.getNrProtocollo());
		params.add(istanzaForest.getDataUltAgg() == null ? null : Date.valueOf(istanzaForest.getDataUltAgg()));
		params.add(
				istanzaForest.getDataInserimento() == null ? null : Date.valueOf(istanzaForest.getDataInserimento()));
		params.add(istanzaForest.getDataAggiornamento() == null ? null
				: Date.valueOf(istanzaForest.getDataAggiornamento()));
		params.add(istanzaForest.getFkConfigUtente());
		params.add(istanzaForest.getFkTipoIstanza());
		
		System.out.println("stari upit"+sql.toString());
		
		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public int getNumberOfInstanceType(int instanceTypeId) {
		return DBUtil.jdbcTemplate.queryForObject(
				"SELECT COUNT(id_istanza_intervento) FROM idf_t_istanza_forest WHERE fk_tipo_istanza = ?",
				Integer.class, instanceTypeId);
	}

	@Override
	public IstanzaForest getByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_t_istanza_forest WHERE id_istanza_intervento = ?";

		return DBUtil.jdbcTemplate.queryForObject(sql, istanzaMapper, idIntervento);
	}

	@Override
	public void updateInvioIstanza(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET data_invio = ?, fk_stato_istanza = ? ");
		sql.append(", data_aggiornamento = ? ");
		sql.append(" WHERE id_istanza_intervento = ? ");

		List<Object> parameters = new ArrayList<>();
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(InstanceStateEnum.INVIATA.getStateValue());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}

	@Override
	public void updateIstanzaTo(Integer idIntervento, InstanceStateEnum instanceStateEnum) {
		StringBuilder sql = new StringBuilder();
		
		List<Object> parameters = new ArrayList<>();
		switch (instanceStateEnum) {

		case RIFIUTATA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ? ");
			parameters.add(InstanceStateEnum.RIFIUTATA.getStateValue());
			break;
		case VERIFICATA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?,data_verifica = ? ");
			parameters.add(InstanceStateEnum.VERIFICATA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			break;
		default:
			
		}
		
		sql.append("  WHERE id_istanza_intervento = ? ");
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}

	@Override
	public PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams) throws ParseException {
		List<Object> parameters = new ArrayList<>();
		String sql = createWhereClauseForSearchAndPopulateParameters(queryParams, backOfficeSearchJoinTables(),
				parameters);
		return super.paginatedList(sql, parameters, boSearchResultMapper, Integer.parseInt(queryParams.get("page")),
				Integer.parseInt(queryParams.get("limit")));
	}

	private String backOfficeSearchJoinTables() {
		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT distinct ifo.nr_istanza_forestale,i.id_intervento,ifo.data_invio, ifo.id_istanza_intervento, ");
		sql.append(" TO_CHAR(ifo.data_inserimento, 'YYYY') as anno_istanza , ");
		sql.append(" ti.id_tipo_istanza, ti.descr_dett_tipoistanza,sti.id_stato_istanza, sti.descr_stato_istanza, ");
		sql.append(" s.id_soggetto, s.codice_fiscale, s.partita_iva, s.nome, s.cognome, s.denominazione, ");
		sql.append(
				" aapp.id_intervento as intervento_aapp, rn.id_intervento as intervento_rn, pop.id_intervento as intervento_pop, ");
		sql.append(" it.flg_vinc_idro, it.flg_compensazione, it.compenzazione_euro,comune_info,transf_info  ");
		sql.append(" FROM idf_t_istanza_forest ifo  ");
		sql.append(" LEFT JOIN idf_t_intervento i ON ifo.id_istanza_intervento = i.id_intervento  ");
		sql.append(" LEFT JOIN idf_t_interv_trasformazione it ON i.id_intervento = it.id_intervento  ");

		sql.append(
				" INNER JOIN (SELECT idfr.id_intervento,idfr.id_soggetto,idfr.fk_config_utente,codice_fiscale,partita_iva, nome, cognome, denominazione ,ifts.fk_comune ");
		sql.append(" FROM idf_r_soggetto_intervento idfr ");
		sql.append(" LEFT JOIN idf_t_soggetto ifts using (id_soggetto)  ) s ON i.id_intervento = s.id_intervento  ");

		sql.append(" LEFT JOIN idf_r_intervento_aapp aapp ON i.id_intervento = aapp.id_intervento  ");
		sql.append(" LEFT JOIN idf_r_intervento_rn_2000 rn ON i.id_intervento = rn.id_intervento  ");
		sql.append(" LEFT JOIN idf_r_intervento_pop_seme pop ON i.id_intervento = pop.id_intervento ");
		sql.append(" LEFT JOIN idf_r_intervento_catfor cat ON i.id_intervento = cat.id_intervento  ");

		sql.append(" LEFT JOIN idf_d_tipo_istanza ti ON ifo.fk_tipo_istanza = ti.id_tipo_istanza ");
		sql.append(" LEFT JOIN idf_d_stato_istanza sti ON ifo.fk_stato_istanza = sti.id_stato_istanza ");

		return sql.toString();
	}

	private String createWhereClauseForSearchAndPopulateParameters(Map<String, String> queryParams, String joinedTables,
			List<Object> parameters) throws ParseException {
		StringBuilder sql = new StringBuilder();
		sql.append(joinedTables);
		String sep = " WHERE ";

		if (queryParams.get(GOV_FORM) != null || queryParams.get(CAT_FOR) != null || queryParams.get(UBICAZIONE) != null
				|| queryParams.get(TIP_TRASF) != null) {
			sql.append(
					" INNER JOIN (SELECT id_intervento,STRING_AGG  ('{id:'||id_parametro_trasf||'}',',') transf_info FROM idf_r_paramtrasf_trasformazion ");

			sql.append(sep).append(" id_parametro_trasf IN(");
			StringBuilder additionalSql = new StringBuilder();
			if (queryParams.get(GOV_FORM) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(GOV_FORM))).append(",");
			}
			if (queryParams.get(CAT_FOR) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(CAT_FOR))).append(",");
			}
			if (queryParams.get(UBICAZIONE) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(UBICAZIONE))).append(",");
			}
			if (queryParams.get(TIP_TRASF) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(TIP_TRASF))).append(",");
			}
			sql.append(additionalSql.toString().substring(0, additionalSql.length() - 1)).append(")");
			sep = " AND ";
		} else {
			sql.append(
					" LEFT JOIN (SELECT id_intervento,STRING_AGG  ('{id:'||id_parametro_trasf||'}',',') transf_info FROM idf_r_paramtrasf_trasformazion ");

		}

		sql.append(" group by id_intervento) pt ON i.id_intervento = pt.id_intervento ");

		sep = " WHERE ";

		if (queryParams.get("prov") != null) {
			sql.append(
					" INNER JOIN ( SELECT inCom.id_intervento,STRING_AGG  ('{\"id_comune\":'||inCom.id_comune || ',\"' ||'istat_comune\":\"'|| inCom.istat_comune || '\",\"' || 'istat_prov\":\"'|| inCom.istat_prov || '\",\"' ||'denominazione_comune\":\"'|| inCom.denominazione_comune||'\"}',',')comune_info ");

			sql.append(
					"FROM ( SELECT id_intervento,idgeo_pl_prop_catasto,id_comune,istat_comune,istat_prov,denominazione_comune FROM idf_r_propcatasto_intervento idfr ");
			sql.append(" LEFT JOIN idf_geo_pl_prop_catasto using (idgeo_pl_prop_catasto) ");
			sql.append(" LEFT JOIN idf_geo_pl_comune on id_comune=fk_comune  ");

			sql.append(sep).append(" istat_prov = ?");
			parameters.add((queryParams.get("prov")));
			sep = " AND ";
			if (queryParams.get(COMUNE) != null) {
				sql.append(sep).append(" id_comune = ?");
				parameters.add(Integer.parseInt(queryParams.get(COMUNE)));
				sep = " AND ";
			}

			if (queryParams.get("foglio") != null) {
				sql.append(sep).append(" foglio = ?");
				parameters.add(Integer.parseInt(queryParams.get("foglio")));
				sep = " AND ";
			}

			if (queryParams.get("particella") != null) {
				sql.append(sep).append(" particella = ? ");
				parameters.add(queryParams.get("particella"));
				sep = " AND ";
			}

			if (queryParams.get("sezione") != null) {
				sql.append(sep).append(" sezione = ? ");
				parameters.add(queryParams.get("sezione"));
				sep = " AND ";
			}
			sql.append("  ) inCom group by id_intervento) cmn ON cmn.id_intervento=i.id_intervento ");
			sep = " WHERE ";

		} else {

			sql.append(
					" LEFT JOIN ( SELECT inCom.id_intervento,STRING_AGG  ('{\"id_comune\":'||inCom.id_comune || ',\"' ||'istat_comune\":\"'|| inCom.istat_comune || '\",\"' || 'istat_prov\":\"'|| inCom.istat_prov || '\",\"' ||'denominazione_comune\":\"'|| inCom.denominazione_comune||'\"}',',')comune_info ");

			sql.append(
					"FROM ( SELECT id_intervento,idgeo_pl_prop_catasto,id_comune,istat_comune,istat_prov,denominazione_comune FROM idf_r_propcatasto_intervento idfr ");
			sql.append(" LEFT join idf_geo_pl_prop_catasto using (idgeo_pl_prop_catasto) ");
			sql.append(" LEFT join idf_geo_pl_comune on id_comune=fk_comune ) inCom ");
			sql.append("  group by id_intervento) cmn ON cmn.id_intervento=i.id_intervento ");
		}

		sep = " WHERE ";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if (queryParams.get("numIstanza") != null) {
			sql.append(sep).append(" ifo.nr_istanza_forestale = ? ");
			parameters.add(Integer.parseInt(queryParams.get("numIstanza")));
			sep = " AND ";
		}

		if (queryParams.get(STATO_ISTANZA) != null) {
			sql.append(sep).append(" ifo.fk_stato_istanza = ? ");
			parameters.add(Integer.parseInt(queryParams.get(STATO_ISTANZA)));
			sep = " AND ";
		}

		if (queryParams.get(ANNO_ISTANZA) != null) {
			sql.append(sep).append(" TO_CHAR(ifo.data_inserimento, 'YYYY') = ? ");
			parameters.add(queryParams.get(ANNO_ISTANZA));
			sep = " AND ";
		}

		if (queryParams.get(DATA_PRES_DA) != null && queryParams.get(DATA_PRES_A) != null) {
			Date dateDa = java.sql.Date.valueOf(queryParams.get(DATA_PRES_DA));
			Date dateA = java.sql.Date.valueOf(queryParams.get(DATA_PRES_A));
			sql.append(sep).append(" ( ifo.data_invio >= ? AND ifo.data_invio <= ? ) ");
			parameters.add(dateDa);
			parameters.add(dateA);
			sep = " AND ";
		} else if (queryParams.get(DATA_PRES_DA) != null) {
			Date date = java.sql.Date.valueOf(queryParams.get(DATA_PRES_DA));
			sql.append(sep).append(" ifo.data_invio >= ? ");
			parameters.add(date);
			sep = " AND ";
		} else if (queryParams.get(DATA_PRES_A) != null) {
			Date date = java.sql.Date.valueOf(queryParams.get(DATA_PRES_A));
			sql.append(sep).append(" ifo.data_invio <= ? ");
			parameters.add(date);
			sep = " AND ";
		}

		
		if (queryParams.get("pf") != null || queryParams.get("pg") != null || queryParams.get("prof") != null) {
			sql.append(sep).append(" s.id_soggetto IN(");
			StringBuilder tempSql = new StringBuilder();
			if (queryParams.get("pf") != null) {
				tempSql.append(queryParams.get("pf")).append(",");
			}
			if (queryParams.get("pg") != null) {
				tempSql.append(queryParams.get("pg")).append(",");
			}
			if (queryParams.get("prof") != null) {
				tempSql.append(queryParams.get("prof")).append(",");
			}
			sql.append(tempSql.toString().substring(0, tempSql.length() - 1)).append(")");
			sep = " AND ";
		}

		if (queryParams.get("aapp") != null) {
			int aapp1 = Integer.parseInt(queryParams.get("aapp"));
			String aapp = queryParams.get("aapp");
			if (aapp1 == 0) {
				sql.append(sep)
						.append(" aapp.id_intervento IN ( SELECT DISTINCT(id_intervento) FROM idf_t_intervento) ");
			} else {
				sql.append(sep).append(" aapp.codice_aapp = ? ");
				parameters.add(aapp);
			}
			sep = " AND ";
		}

		if (queryParams.get("rn2k") != null) {
			int rn2k1 = Integer.parseInt(queryParams.get("rn2k"));
			String rn2k = queryParams.get("rn2k");
			if (rn2k1 == 0) {
				sql.append(sep).append(" rn.id_intervento IN(SELECT DISTINCT(id_intervento) FROM idf_t_intervento) ");
			} else {
				sql.append(sep).append(" rn.codice_rn_2000 = ? ");
				parameters.add(rn2k);
			}
			sep = " AND ";
		}

		if (queryParams.get("popSeme") != null) {
			String popSeme = queryParams.get("popSeme");
			if (popSeme == "ALL") {
				sql.append(sep).append(" pop.id_intervento IN(SELECT DISTINCT(id_intervento) FROM idf_t_intervento) ");
			} else {
				sql.append(sep).append(" pop.id_popolamento_seme = ? ");
				parameters.add(popSeme);
			}
			sep = " AND ";
		}

		if (queryParams.get(VINC_IDRO) != null && queryParams.get(VINC_IDRO).equals("1")) {
			sql.append(sep).append(" it.flg_vinc_idro = ? ");
			parameters.add(queryParams.get(VINC_IDRO));
			sep = " AND ";
		}

		if (queryParams.get("compNec") != null) {
			if (Boolean.valueOf(queryParams.get("compNec"))) {
				sql.append(sep).append(" it.flg_compensazione IN ('M', 'F') ");
				sep = " AND ";
			} else {
				sql.append(sep).append(" it.flg_compensazione = 'N' ");
				sep = " AND ";
				if (Boolean.valueOf(queryParams.get("compO1"))) {
					sql.append(sep).append(" flg_art7_a = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO2"))) {
					sql.append(sep).append(" flg_art7_b = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO3"))) {
					sql.append(sep).append(" flg_art7_c = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO4"))) {
					sql.append(sep).append(" flg_art7_d = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO5"))) {
					sql.append(sep).append(" flg_art7_d_bis = '1' ");
				}
			}
		}


		if (queryParams.get(CALC_ECON_DA) != null && queryParams.get(CALC_ECON_A) != null) {
			sql.append(sep).append(" ( it.compenzazione_euro >= ? AND it.compenzazione_euro <= ? )");
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_DA)));
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_A)));
		} else if (queryParams.get(CALC_ECON_DA) != null) {
			sql.append(sep).append(" it.compenzazione_euro >= ? ");
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_DA)));
		} else if (queryParams.get(CALC_ECON_A) != null) {
			sql.append(sep).append(" it.compenzazione_euro <= ? ");
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_A)));
		}

		return sql.append(getQuerySortingSegment(queryParams.get("sort"))).toString();
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
		mappedColumnNames.put("tipologiaIstanza", "fk_tipo_istanza");
		mappedColumnNames.put("numeroIstanza", "nr_istanza_forestale");
		mappedColumnNames.put(STATO_ISTANZA, "fk_stato_istanza");
		mappedColumnNames.put(ANNO_ISTANZA, "data_inserimento");
		mappedColumnNames.put("dataPresentazione", "data_invio");
		mappedColumnNames.put("prov", "provincia");
		mappedColumnNames.put(COMUNE, "denominazione_comune");
		mappedColumnNames.put("richiedente", "codice_fiscale");
		mappedColumnNames.put("areeProtette", "");
		mappedColumnNames.put("natura2000", "");
		mappedColumnNames.put("populamenti", "");
		mappedColumnNames.put("vincIdrogeologico", "");
		mappedColumnNames.put("compensazione", "");
		mappedColumnNames.put("euro", "");
		return mappedColumnNames.get(fieldName) != null ? mappedColumnNames.get(fieldName) : "nr_istanza_forestale";
	}

	@Override
	public ConfigUtente getUtenteForIstanzaById(Integer idIntervento) {
		String sql = "SELECT * FROM idf_t_istanza_forest WHERE id_istanza_intervento = ?";

		IstanzaForest res = DBUtil.jdbcTemplate.queryForObject(sql, istanzaMapper, idIntervento);
		String sql1 = "SELECT * FROM idf_cnf_config_utente  WHERE id_config_utente = ? ";
		ConfigUtente conUt = DBUtil.jdbcTemplate.queryForObject(sql1, new ConfigUtenteMapper(),
				res.getFkConfigUtente());
		return conUt;
	}
}