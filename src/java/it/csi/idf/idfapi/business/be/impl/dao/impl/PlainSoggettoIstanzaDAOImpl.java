package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PlainSoggettoIstanzaDAO;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanza;
import it.csi.idf.idfapi.mapper.PlainSoggettoIstanzaMapper;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SoggettoTypeEnum;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;

@Component
public class PlainSoggettoIstanzaDAOImpl extends GenericDAO implements PlainSoggettoIstanzaDAO {
	
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';

	@Override
	public PaginatedList<PlainSoggettoIstanza> getAllIstances(int fkConfigUtente, Integer tipoAccreditamento, int page,
			int limit, String sort) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT if.id_istanza_intervento, if.nr_istanza_forestale, if.data_invio, c.denominazione_comune, si.descr_stato_istanza");
		sql.append(", sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione");
		sql.append(", c.id_comune, c.istat_comune, c.istat_prov, c.denominazione_comune");
		sql.append(" FROM idf_t_istanza_forest if");
		sql.append(" JOIN idf_cnf_config_utente cu ON if.fk_config_utente = cu.id_config_utente");
		sql.append(" JOIN idf_d_stato_istanza si ON if.fk_stato_istanza = si.id_stato_istanza");
		sql.append(" JOIN idf_r_soggetto_intervento sint ON if.id_istanza_intervento = sint.id_intervento");
		sql.append(" JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto");
		sql.append(" JOIN idf_geo_pl_comune c ON sog.fk_comune = c.id_comune");
		sql.append(" WHERE if.fk_config_utente = ? AND cu.fk_profilo_utente = ?");	
		if(tipoAccreditamento.equals(TipoAccreditamentoEnum.RICHIEDENTE.getValue())) {
			sql.append(" AND sint.id_tipo_soggetto = ");
			sql.append(SoggettoTypeEnum.RICHIEDENTE.getValue());
		} else if(tipoAccreditamento.equals(TipoAccreditamentoEnum.PROFESSIONISTA.getValue())) {
			sql.append(" AND sint.id_tipo_soggetto <> ");
			sql.append(SoggettoTypeEnum.RICHIEDENTE.getValue());
		}
		sql.append(getQuerySortingSegment(sort));
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(fkConfigUtente);
		parameters.add(ProfiloUtenteEnum.CITTADINO.getValue());

		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaMapper(), page, limit);
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
		switch (fieldName) {
			case "nrIstanzaForestale":
				return "nr_istanza_forestale";
			case "richiedente":
				return "codice_fiscale";
			case "dataInvio":
				return "data_invio";
			case "comune":
				return "denominazione_comune";
			case "stato":
				return "descr_stato_istanza";
			default:
				return "nr_istanza_forestale";
		}
	}

}
