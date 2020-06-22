package it.csi.idf.idfapi.business.be.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.GeoPtAreaDiSaggioApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdsDAO;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.AberiCAMeDOMEnum;
import it.csi.idf.idfapi.util.AreaDiSaggioEnum;
import it.csi.idf.idfapi.util.DatiStazionaliEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.QueryUtil;
import it.csi.idf.idfapi.util.RelascopicaCompletaEnum;
import it.csi.idf.idfapi.util.RelascopicaSempliceEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.ValidationUtil;

public class GeoPtAreaDiSaggioApiServiceImpl extends SpringSupportedResource implements GeoPtAreaDiSaggioApi {
	private static final Logger logger = Logger.getLogger(GeoPtAreaDiSaggioApiServiceImpl.class);
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';

	@Autowired
	WrapperAdsDAO wrapperAdsDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	List<Object> parameters = null;

	@Override
	public Response search(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") Integer dettaglioAmbito [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @QueryParam("page") int page,
			@QueryParam("limit") int limit, @QueryParam("sort") String sort, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		try {
			
			if (!ValidationUtil.hasNotNullValue(new Object[][] {idComuneStr,istatProv,idCategoriaForestale, idAmbito,dettaglioAmbito, fromDate, toDate, tipoAds, statoScheda})) {
				return Response.serverError().entity("Deve essere almeno un parametro").build();
			}

		PaginatedList<AreaDiSaggio> ads;
		
		// It will have empty value and not null value. That will cause problem if only istatProv has value
			Integer idComune[] = new Integer[idComuneStr.length];
			for (int i = 0; i < idComune.length; i++) {
				idComune[i] = idComuneStr==null || idComuneStr[i].isEmpty() ? null: Integer.parseInt(idComuneStr[i]);
			}
		
			ads = wrapperAdsDAO.search(
					createWhereClauseForSearchAndPopulateParameters(idComune, istatProv, idCategoriaForestale, idAmbito,
							dettaglioAmbito, fromDate, toDate, tipoAds, statoScheda, sort),
					page, limit, parameters);
			return Response.ok(ads).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException("Validation error: Search params are not in correct format");
		}
	}

	private String getQuerySortingSegment(String sort) {
		String sortField = null;
		String sortOrder = null;

		StringBuilder sql = new StringBuilder();
		if (sort != null && !sort.isEmpty()) {
			sortOrder = sort.charAt(0) == ORDER ? DESCENDING : ASCENDING;
			sortField = sortOrder.equals(DESCENDING) ? sort.substring(1) : sort;
			if (sortField != null && !sortField.isEmpty()) {
				sql.append(" ORDER BY ");
				sortField = translateFieldName(sortField);
				sql.append(sortField).append(" ").append(sortOrder);
			}
		}
		return sql.toString();
	}

	private StringBuilder createMainQueryForSearch() {

		return new StringBuilder(
				"SELECT ads.codice_ads, tipoads.descr_tipo_ads, comune.denominazione_comune, c.descrizione, am.descr_ambito,ads.data_ril,ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), "
				+ " stato_ads.id_stato_ads, stato_ads.id_stato_ads, stato_ads.descr_stato_ads, stato_ads.mtd_ordinamento, stato_ads.flg_visibile ")	
						.append("FROM idf_geo_pt_area_di_saggio ads ")
						.append("INNER JOIN idf_geo_pl_tipo_forestale geo ON ads.fk_tipo_forestale = geo.id_geo_tipo_forestale ")
						.append("INNER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = geo.id_geo_tipo_forestale ")
						.append("INNER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
						.append("INNER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
						.append("INNER JOIN idf_d_ambito_rilievo am ON am.id_ambito = ads.fk_ambito ")
						.append("INNER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
						.append("INNER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
						.append("INNER JOIN idf_d_stato_ads stato_ads ON stato_ads.id_stato_ads = ads.fk_stato_ads ");
	}

	private StringBuilder createMainQueryForSearch1() {
		return new StringBuilder(
				"SELECT ads.codice_ads, tipoads.descr_tipo_ads, comune.denominazione_comune, c.descrizione, am.descr_ambito,ads.data_ril, esp.descr_esposizione, b.tipo, ads.quota, ads.inclinazione,"
						+ " particella.denominazione_particella, proprieta.descr_proprieta, intervento.descr_intervento, superficie.raggio_mt, superficie.densita_campionamento, danno.descr_danno,"
						+ " assetto.descr_assetto, superficie.cod_stadio_sviluppo, superficie.nr_ceppaie, superficie.rinnovazione, superficie.rin_specie_prevalente,superficie.perc_copertura_chiome,"
						+ " superficie.cod_esbosco,superficie.dist_esbosco_fuori_pista,combustibile.perc_copertura_lettiera,combustibile.perc_copertura_erbacea,combustibile.perc_copertura_cespugli,"
						+ " superficie.dist_esbosco_su_pista,superficie.min_distanza_planimetrica, superficie.danni_perc,superficie.nr_piante_morte,superficie.perc_defogliazione,superficie.perc_ingiallimento,"
						+ " superficie.flg_pascolamento,dest.descr_destinazione,superficie.combust_p,superficie.fk_tipo_strutturale_princ, priorita.descr_priorita, "
						+ " combustibile.flg_principale,fertilita.descr_classe_fertilita,ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)),"
						+ " statoAds.id_stato_ads as ids_stato_ads, statoAds.id_stato_ads, statoAds.descr_stato_ads, statoAds.mtd_ordinamento, statoAds.flg_visibile ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_d_stato_ads statoAds ON statoAds.id_stato_ads = ads.fk_stato_ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_d_ambito_rilievo am ON am.id_ambito = ads.fk_ambito ")
								.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
								.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
								.append("LEFT OUTER JOIN idf_d_destinazione dest ON ads.fk_destinazione = dest.id_destinazione ")
								.append("LEFT OUTER JOIN idf_geo_pl_particella_forest particella ON ads.idgeo_pl_particella_forest = particella.idgeo_pl_particella_forest ")
								.append("LEFT OUTER JOIN idf_d_proprieta proprieta ON ads.fk_proprieta = proprieta.id_proprieta ")
								.append("LEFT OUTER JOIN idf_d_priorita priorita ON ads.fk_priorita = priorita.id_priorita ")
								.append("LEFT OUTER JOIN idf_d_tipo_intervento intervento ON ads.fk_tipo_intervento = intervento.id_tipo_intervento ")
								.append("LEFT OUTER JOIN idf_t_ads_superficie_nota superficie ON ads.idgeo_pt_ads = superficie.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_r_ads_combustibile combustibile ON combustibile.idgeo_pt_ads = superficie.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_assetto assetto ON ads.fk_assetto = assetto.id_assetto ")
								.append("LEFT OUTER JOIN idf_t_ads_relascopica relascopica ON ads.idgeo_pt_ads = relascopica.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_classe_fertilita fertilita ON relascopica.fk_classe_fertilita = fertilita.id_classe_fertilita ")
								.append("LEFT OUTER JOIN idf_d_danno danno ON ads.fk_danno = danno.id_danno ")
								.append("LEFT OUTER JOIN idf_d_esposizione esp ON ads.fk_esposizione= esp.id_esposizione  ");
	}

	private StringBuilder createMainQueryForSearch2() {
		return new StringBuilder(
				"SELECT ads.codice_ads, tipoads.descr_tipo_ads, comune.denominazione_comune, c.descrizione, am.descr_ambito,ads.data_ril, esp.descr_esposizione, b.tipo, ads.quota, ads.inclinazione,"
						+ " particella.denominazione_particella, proprieta.descr_proprieta, intervento.descr_intervento,"
						+ " assetto.descr_assetto, " + " dest.descr_destinazione, priorita.descr_priorita, "
						+ " ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), "
						+ " statoAds.id_stato_ads as ids_stato_ads, statoAds.id_stato_ads, statoAds.descr_stato_ads, statoAds.mtd_ordinamento, statoAds.flg_visibile ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_d_stato_ads statoAds ON statoAds.id_stato_ads = ads.fk_stato_ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_d_ambito_rilievo am ON am.id_ambito = ads.fk_ambito ")
								.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
								.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
								.append("LEFT OUTER JOIN idf_d_destinazione dest ON ads.fk_destinazione = dest.id_destinazione ")
								.append("LEFT OUTER JOIN idf_geo_pl_particella_forest particella ON ads.idgeo_pl_particella_forest = particella.idgeo_pl_particella_forest ")
								.append("LEFT OUTER JOIN idf_d_proprieta proprieta ON ads.fk_proprieta = proprieta.id_proprieta ")
								.append("LEFT OUTER JOIN idf_d_priorita priorita ON ads.fk_priorita = priorita.id_priorita ")
								.append("LEFT OUTER JOIN idf_d_tipo_intervento intervento ON ads.fk_tipo_intervento = intervento.id_tipo_intervento ")
								.append("LEFT OUTER JOIN idf_d_assetto assetto ON ads.fk_assetto = assetto.id_assetto ")
								.append("LEFT OUTER JOIN idf_d_esposizione esp ON ads.fk_esposizione= esp.id_esposizione  ");
	}

	private StringBuilder createRelascopicaQueryForSearch() {
		return new StringBuilder(
				"SELECT ads.codice_ads, t.id_specie, t.latino, s.nr_alberi_seme, s.nr_alberi_pollone, tipoads.descr_tipo_ads, s.note, relascopica.fattore_numerazione, comune.denominazione_comune, c.descrizione,ads.data_ril, esp.descr_esposizione, b.tipo, ads.quota, ads.inclinazione,"
						+ " particella.denominazione_particella, proprieta.descr_proprieta, strutturale.descr_tipo_strutturale, s.diametro, s.altezza, s.incremento, s.flg_pollone_seme, s.cod_tipo_campione,ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), "
						+ " statoAds.id_stato_ads as ids_stato_ads, statoAds.id_stato_ads, statoAds.descr_stato_ads, statoAds.mtd_ordinamento, statoAds.flg_visibile ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_d_stato_ads statoAds ON statoAds.id_stato_ads = ads.fk_stato_ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
								.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
								.append("LEFT OUTER JOIN idf_geo_pl_particella_forest particella ON ads.idgeo_pl_particella_forest = particella.idgeo_pl_particella_forest ")
								.append("LEFT OUTER JOIN idf_d_proprieta proprieta ON ads.fk_proprieta = proprieta.id_proprieta ")
								.append("LEFT OUTER JOIN idf_t_ads_relascopica relascopica ON relascopica.idgeo_pt_ads = ads.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_tipo_strutturale strutturale ON strutturale.id_tipo_strutturale = relascopica.fk_tipo_strutturale_princ ")
								.append("LEFT OUTER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_t_specie t ON t.id_specie = s.id_specie ")
								.append("LEFT OUTER JOIN idf_d_esposizione esp ON ads.fk_esposizione= esp.id_esposizione  ");

	}

	private StringBuilder createRelascopicaCompletaSearch() {
		return new StringBuilder(
				"SELECT ads.codice_ads, t.id_specie, s.diametro, s.altezza, s.incremento, s.flg_pollone_seme ")
						.append("FROM idf_geo_pt_area_di_saggio ads ")
						.append("INNER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
						.append("INNER JOIN idf_t_specie t ON t.id_specie = s.id_specie ");

	}

	private StringBuilder searchForAlberiCampioneDominante() {
		return new StringBuilder(
				"SELECT ads.codice_ads, s.id_specie, t.latino, s.qualita, s.diametro, s.altezza, s.incremento, s.eta, s.note, s.cod_tipo_campione, t.codice, t.cod_gruppo, s.flg_pollone_seme, t.latino ")
						.append("FROM idf_geo_pt_area_di_saggio ads ")
						.append("INNER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
						.append("INNER JOIN idf_t_specie t ON t.id_specie = s.id_specie ");

	}

	private StringBuilder createMainQueryForGetDatiStazionali1() {
		return new StringBuilder(
				"SELECT ads.codice_ads, fertilita.descr_classe_fertilita, superficie.densita_campionamento, c.id_categoria_forestale ,superficie.raggio_mt, ads.fk_tipo_forestale, assetto.id_assetto,"
						+ " superficie.fk_tipo_strutturale_princ,combustibile.perc_copertura_lettiera,combustibile.perc_copertura_erbacea,combustibile.perc_copertura_cespugli,"
						+ " superficie.perc_copertura_chiome,superficie.cod_stadio_sviluppo, superficie.nr_ceppaie, superficie.rinnovazione,superficie.rin_specie_prevalente ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_t_ads_superficie_nota superficie ON ads.idgeo_pt_ads = superficie.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_t_ads_relascopica relascopica ON relascopica.idgeo_pt_ads = ads.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_classe_fertilita fertilita ON fertilita.id_classe_fertilita = relascopica.fk_classe_fertilita ")
								.append("LEFT OUTER JOIN idf_r_ads_combustibile combustibile ON combustibile.idgeo_pt_ads = superficie.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_assetto assetto ON ads.fk_assetto = assetto.id_assetto ");
	}

	private StringBuilder createWhereClauseForSearchAndPopulateParameters(Integer idsComune[], String istatsProv[],
			Integer idsCategoriaForestale[], Integer idsAmbito[], Integer dettagliosAmbito[], String fromDates[], String toDates[],
			Integer tipoAds[], Integer statoScheda[], String sort) throws ParseException {
		String sep = "WHERE";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		parameters = new ArrayList<Object>()
				;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlPartCondition = new StringBuilder();
		for (int i = 0; i < idsComune.length; i++) {
			StringBuilder partialSQLCondition = new StringBuilder();
			QueryUtil.addConditionForNotNullParams(partialSQLCondition, parameters, true, "AND", 
					new it.csi.idf.idfapi.util.QueryParam(idsComune[i], "comune.id_comune"),
					new it.csi.idf.idfapi.util.QueryParam(istatsProv[i], "p.istat_prov")
					);
			QueryUtil.appendCondition(sqlPartCondition, "OR", partialSQLCondition);
		}		
		QueryUtil.addBrackets(sqlPartCondition);
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
				
		sqlPartCondition = new StringBuilder();
		QueryUtil.addConditionForNotNullParams(sqlPartCondition, parameters, true, "OR",idsCategoriaForestale, "c.id_categoria_forestale");
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
				
		/* TODO: Check is this is OK. It is strange that it is implemented in this way. Now user can add as many detaggiloAmbito as he want.   */
		sqlPartCondition = new StringBuilder();
		for (int i = 0; i < idsAmbito.length; i++) {
			StringBuilder partialSQLCondition = new StringBuilder();
			QueryUtil.addConditionForNotNullParams(partialSQLCondition, parameters, false, "OR", 
					new it.csi.idf.idfapi.util.QueryParam(idsAmbito[i], "am.id_ambito"),
					new it.csi.idf.idfapi.util.QueryParam(dettagliosAmbito[i], "am.id_ambito")
					);
			QueryUtil.appendCondition(sqlPartCondition, "OR", partialSQLCondition);
		}		
		QueryUtil.addBrackets(sqlPartCondition);
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		

		sqlPartCondition = new StringBuilder();
		for (int i = 0; i < fromDates.length; i++) {
			StringBuilder partialSQLCondition = new StringBuilder();
			QueryUtil.addConditionForNotNullParams(partialSQLCondition, parameters, true, "AND", 
					new it.csi.idf.idfapi.util.QueryParam(formatter.parse(fromDates[i]), "ads.data_ril", ">="),
					new it.csi.idf.idfapi.util.QueryParam(formatter.parse(toDates[i]), "ads.data_ril", "<="));
			QueryUtil.appendCondition(sqlPartCondition, "OR", partialSQLCondition);
		}		
		
		sqlPartCondition = new StringBuilder();
		QueryUtil.addConditionForNotNullParams(sqlPartCondition, parameters, true, "OR", tipoAds, "ads.fk_tipo_ads");
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		
		sqlPartCondition = new StringBuilder();
		QueryUtil.addConditionForNotNullParams(sqlPartCondition, parameters, true, "OR", statoScheda, "ads.fk_stato_ads");
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		QueryUtil.makeWhereQueryStatement(sql);
		
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return createMainQueryForSearch().append(sql).append(sortSql);
		}
		return createMainQueryForSearch().append(sql);
	}

	private StringBuilder createWhereClauseForSearch(String codiceAds, StringBuilder sb) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (codiceAds != null) {
			sql.append(sep).append(" ads.codice_ads = ?");
			parameters.add(codiceAds);
		}
		return sb.append(sql);
	}

	private StringBuilder createWhereClauseForSearchCAV(String codiceAds, StringBuilder sb, String sort) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (codiceAds != null) {
			sql.append(sep).append(" s.cod_tipo_campione = 'CAV'").append(" AND").append(" ads.codice_ads = ?");

			parameters.add(codiceAds);
		}
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return sb.append(sql).append(sortSql);
		}

		return sb.append(sql);
	}

	private StringBuilder createWhereClauseForSearchIn(List<String> codiceAdsList, StringBuilder sb) {
		String sep = "WHERE";
		StringBuilder sql = new StringBuilder();
		if (codiceAdsList != null) {
			sql.append(sep).append(" ads.codice_ads IN (");
			StringBuilder allCodices = new StringBuilder();
			for (String codice : codiceAdsList) {
				allCodices.append("'");
				allCodices.append(codice);
				allCodices.append("'");
				allCodices.append(',');
			}
			String allCodice = allCodices.substring(0, allCodices.toString().length() - 1);
			sql.append(allCodice);
			sql.append(")");
		}
		return sb.append(sql);
	}

	@Override
	public Response getAreaDiSaggioByCodiceAds(String codiceAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		AreaDiSaggio ads;
		try {
			ads = wrapperAdsDAO.findAreaDiSaggioByCodiceAds(
					createWhereClauseForSearch(codiceAds, createMainQueryForSearch1()), parameters);
		} catch (RecordNotFoundException e) {
			return Response.serverError().build();
		}
		return Response.ok(ads).build();
	}

	@Override
	public Response getADSByCodiceAds(boolean includeStep, String codiceAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		AreaDiSaggio ads;
		try {
			ads = wrapperAdsDAO.findADSByCodiceADS(createWhereClauseForSearch(codiceAds, createMainQueryForSearch2()),
					parameters);
			if (includeStep) {
				ads.setLastCompletedStep(wrapperAdsDAO.getNumberOfLastCompletedStep(codiceAds).getNextStep());
			}
		} catch (RecordNotFoundException e) {
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}
		return Response.ok(ads).build();
	}

	public Response getAlberiCampioneDominante(String codiceAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<AlberiCampioneDominanteDTO> ads = wrapperAdsDAO.findAlberiCampioneDominante(
				createWhereClauseForSearch(codiceAds, searchForAlberiCampioneDominante()), parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response searchExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<AreaDiSaggio> list;
		List<AreaDiSaggioEnum> columns = Arrays.asList(AreaDiSaggioEnum.values());

		list = wrapperAdsDAO.findAreaDiSaggioByCodiceAdsListExcel(
				createWhereClauseForSearchIn(excel.getCodiceADSList(), createMainQueryForSearch()));

		// Create a Sheet
		Sheet sheet = hwb.createSheet("Area di saggio");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (list != null) {
			Sheet sheetAlberiCamDom = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");
			Sheet sheetAlberiCav = hwb.createSheet("Area di saggio-Alberi Cavaletti");
			int rowNumDomCam  = 1;
			int rowNumCav  = 1;
			for (AreaDiSaggio area : list) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(area.getCodiceADS());

				row.createCell(1).setCellValue(area.getComune());

				row.createCell(2).setCellValue(area.getCategoriaForestale());

				row.createCell(3).setCellValue(area.getAmbitoDiRilevamento());

				row.createCell(4).setCellValue(area.getDettaglioAmbito());

				row.createCell(5).setCellValue(area.getTipologia());

				row.createCell(6).setCellValue(area.getDataRilevamento());
				
				rowNumDomCam = createExcelAlberiInfo(area.getCodiceADS(), hwb, true,sheetAlberiCamDom, rowNumDomCam);
				rowNumCav = createExcelAlberiInfo(area.getCodiceADS(), hwb, false, sheetAlberiCav, rowNumCav);

			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "area_di_saggio_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	@Override
	public Response exportAreaDiSaggionDetails(String codiceAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;

		AreaDiSaggio ads = null;
		try {
			ads = wrapperAdsDAO.findAreaDiSaggioByCodiceAds(
					createWhereClauseForSearch(codiceAds, createMainQueryForSearch1()), parameters);

			// Create a Sheet
			Sheet sheet = hwb.createSheet("Area di saggio");
			for (int i = 0; i < 8; i++) {
				sheet.setColumnWidth(i, 6000);
			}

			final int rowNum = 11;

			Font defaultFontTitle = hwb.createFont();
			defaultFontTitle.setFontHeightInPoints((short) 12);
			defaultFontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
			
			Font defaultFontValue = hwb.createFont();
			defaultFontValue.setFontHeightInPoints((short) 12);
			
			for (int i = 0; i < rowNum; i++) {
				List<String> columnNames = getColumnNamesForExportAreaDiSaggio(i);
				List<Object> rowValues = getRowValuesForAreaDiSaggioExport(ads, i);
				Row row = sheet.createRow(i);
				row.setHeightInPoints(30);
				CellStyle cellStyle = getCellStyleForExportAreaDiSaggio(hwb, i);
				CellStyle cellStyleVaue = hwb.createCellStyle();
				cellStyleVaue.cloneStyleFrom(cellStyle);
				cellStyleVaue.setFont(defaultFontValue);
				cellStyle.setFont(defaultFontTitle);
				for (int j = 0; j < 4; j++) { // in order to write all 8 columns in every row
					
					Cell columnNamecell = row.createCell(j * 2);
					Cell columnValueCell = row.createCell(j * 2 + 1);
					columnNamecell.setCellStyle(cellStyle);
					columnValueCell.setCellStyle(cellStyleVaue);
					if (columnNames.size() > j) {
						columnNamecell.setCellValue(getCellValueAsString(columnNames.get(j)));
						setCellValue(columnValueCell, rowValues.get(j));
					} else {
						columnNamecell.setCellValue("");
						columnValueCell.setCellValue("");
						;
					}
				}
			}

			Sheet sheetAlberiCamDom = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");
			createExcelAlberiInfo(codiceAds, hwb, true,sheetAlberiCamDom, 1);
			Sheet sheetAlberiCav = hwb.createSheet("Area di saggio-Alberi Cavaletti");
			createExcelAlberiInfo(codiceAds, hwb, false, sheetAlberiCav, 1);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				logger.fatal("An exception occurred.", e);
			}

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "area_di_saggio_" + codiceAds + "_" + formatDateTime;

			response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();

		} catch (RecordNotFoundException e) {
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}

	}

	private String getCellValueAsString(Object value) {
		return value != null ? value.toString() : "";
	}

	private void setCellValue(Cell cell, Object value) {

		if (value == null) {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			return;
		}

		if (value instanceof BigDecimal) {
			double doubleValue = ((BigDecimal) value).doubleValue();
			cell.setCellValue(doubleValue);
		}

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}
		if (value instanceof String) {
			cell.setCellValue((String) value);
		}

		if (value instanceof Byte) {
			cell.setCellValue((Byte) value);
		}

	}

	private CellStyle getCellStyleForExportAreaDiSaggio(HSSFWorkbook hwb, int rowNumber) {

		
//		defaultFont.setColor(IndexedColors.WHITE.getIndex());
		CellStyle defaultCellStyle = hwb.createCellStyle();
		defaultCellStyle.setFillPattern((short) FillPatternType.SOLID_FOREGROUND.ordinal());
//		defaultCellStyle.setFont(defaultFont);
		defaultCellStyle.setWrapText(true);
		defaultCellStyle.setBorderBottom((short) BorderStyle.THIN.ordinal());
		defaultCellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
		defaultCellStyle.setBorderLeft((short) BorderStyle.THIN.ordinal());
		defaultCellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
		defaultCellStyle.setBorderRight((short) BorderStyle.THIN.ordinal());
		defaultCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
		defaultCellStyle.setBorderTop((short) BorderStyle.THIN.ordinal());
		defaultCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());

		switch (rowNumber) {

		case 0:
		case 1:
		case 2:
			HSSFColor grey20 = this.setColor(hwb, IndexedColors.GREY_25_PERCENT.getIndex(), (byte)248, (byte)248, (byte)248);
			defaultCellStyle.setFillForegroundColor(grey20.getIndex());
//			defaultCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			break;

		case 3:
		case 4:
		case 5:
		case 6:
			HSSFColor grey40 = this.setColor(hwb, IndexedColors.GREY_40_PERCENT.getIndex(), (byte)245, (byte)245, (byte)245);
			defaultCellStyle.setFillForegroundColor(grey40.getIndex());
//			defaultCellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			break;
		case 7:
		case 8:
		case 9:
		case 10:			
			
			HSSFColor grey50 = this.setColor(hwb, IndexedColors.GREY_50_PERCENT.getIndex(), (byte)240, (byte)240, (byte)240);
			defaultCellStyle.setFillForegroundColor(grey50.getIndex());
			//defaultCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			break;
		}
				
		return defaultCellStyle;

	}
	
	public HSSFColor setColor(HSSFWorkbook workbook,short index, byte r,byte g, byte b){
	    HSSFPalette palette = workbook.getCustomPalette();
	    HSSFColor hssfColor = null;
	    try {
	        hssfColor= palette.findColor(r, g, b); 
	        System.out.println("hssf color: "+ hssfColor);
	        if (hssfColor == null ){
	        	palette.setColorAtIndex(index, r, g, b);
	            hssfColor = palette.getColor(index);
	        }
	    } catch (Exception e) {
	        logger.error(e);
	    }

	    return hssfColor;
	}

	private List<Object> getRowValuesForAreaDiSaggioExport(AreaDiSaggio ads, int row) {

		List<Object> valueList = new ArrayList<Object>();

		switch (row) {

		case 0:
			valueList.add(ads.getComune());
			valueList.add(ads.getUtmEst()); // .doubleValue());
			valueList.add(ads.getUtmNord()); // .doubleValue());
			valueList.add(ads.getQuota());
			break;
		case 1:
			valueList.add(ads.getEspozione());
			valueList.add(ads.getInclinazione());
			valueList.add(ads.getDensitaCamp());
			valueList.add(ads.getRaggioArea());
			break;
		case 2:
			valueList.add(ads.getParticellaForestale());
			valueList.add(ads.getProprieta());
			break;
		case 3:
			valueList.add(ads.getCategoriaForestale());
			valueList.add(ads.getTipoForestale());
			valueList.add(ads.getAssettoEvolutivoColturale());
			valueList.add(ads.getTipoStrutturale());
			break;
		case 4:
			valueList.add(ads.getStadioDiSviluppo());
			valueList.add(ads.getClasseDiFertilita());
			valueList.add(ads.getnCepaie());
			valueList.add(ads.getRinnovazione());
			break;
		case 5:
			valueList.add(ads.getSpeciePrevalenteRinnovazione());
			valueList.add(ads.getCoperturaChiome());
			valueList.add(ads.getCoperturaCespugli());
			valueList.add(ads.getCoperturaErbacea());
			break;
		case 6:
			valueList.add(ads.getLettiera());
			break;

		case 7:
			valueList.add(ads.getDestinazione());
			valueList.add(ads.getIntervento());
			valueList.add(ads.getPriorita());
			valueList.add(ads.getEsbosco());
			break;

		case 8:
			valueList.add(ads.getDefp());
			valueList.add(ads.getDesp());
			valueList.add(ads.getMdp());
			valueList.add(ads.getDannoPrevalente());
			break;

		case 9:
			valueList.add(ads.getIntesitaDanni());
			valueList.add(ads.getnPianteMorte());
			valueList.add(ads.getDefogliazione());
			valueList.add(ads.getIngiallimento());
			break;
		case 10:
			valueList.add(ads.getPascolamento());
			valueList.add(ads.getCombustibilePrincipale());
			valueList.add(ads.getCombustibileSecondario());
			valueList.add(ads.getTavola());
			break;

		}

		return valueList;
	}

	private List<String> getColumnNamesForExportAreaDiSaggio(int row) {

		List<String> columnName = new ArrayList<String>();

		switch (row) {

		case 0:
			columnName.add("Comune");
			columnName.add("UTM EST");
			columnName.add("UTM NORD");
			columnName.add("Quota (m s.l.m.)");
			break;

		case 1:

			columnName.add("Esposizione");
			columnName.add("Inclinazione (gradi)");
			columnName.add("Densita camp.");
			columnName.add("Raggio area");
			break;

		case 2:

			columnName.add("Particella forestale");
			columnName.add("Proprieta");
			break;

		case 3:

			columnName.add("Categoria forestale");
			columnName.add("Tipo forestale");
			columnName.add("Assetto evolutivo colturale");
			columnName.add("Tipo Strutturale");
			break;

		case 4:

			columnName.add("Stadio di sviluppo");
			columnName.add("Classe di fertilita");
			columnName.add("N. Ceppaie");
			columnName.add("Rinnovazione");
			break;

		case 5:

			columnName.add("Specie prevalente rinnovazione");
			columnName.add("Copertura chiome (%)");
			columnName.add("Copertura cespugli/ suffrutici (%)");
			columnName.add("Copertura erbacea (%)");
			break;

		case 6:

			columnName.add("Lettiera (%)");
			break;

		case 7:

			columnName.add("Destinazione");
			columnName.add("intervento");
			columnName.add("Priorita intervento");
			columnName.add("Esbosco");
			break;

		case 8:

			columnName.add("DEFP");
			columnName.add("DESP");
			columnName.add("MDP");
			columnName.add("Danno prevalente");
			break;

		case 9:

			columnName.add("Intensita danni (%)");
			columnName.add("N. Piante morte");
			columnName.add("Defogliazione");
			columnName.add("Ingiallimento");
			break;

		case 10:

			columnName.add("Pascolamento");
			columnName.add("Combustibile principale");
			columnName.add("Combustibile secondario");
			columnName.add("Tavola");
			break;

		}

		return columnName;

	}

	@Override
	public Response excelAlberiCAMeDOM(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		
		Sheet sheetAlberiCamDom = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");
		createExcelAlberiInfo(excel.getCodiceADSList().get(0), hwb, true, sheetAlberiCamDom, 1);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "alberi_cam_dom_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	private int createExcelAlberiInfo(String codiceAds, HSSFWorkbook hwb, boolean alberiDomCav, Sheet sheet, int rowNum) {
		List<AlberiCampioneDominanteDTO> list;
		
		List<AberiCAMeDOMEnum> columns = Arrays.asList(AberiCAMeDOMEnum.values());		
		list = wrapperAdsDAO.findAlberiCampioneDominante(
				createWhereClauseForSearch(codiceAds, searchForAlberiCampioneDominante()), parameters);
	
//		Sheet sheet = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		
		if (list != null) {
			for (AlberiCampioneDominanteDTO area : list) {
				if ((alberiDomCav && !area.getCodTipoCampione().equalsIgnoreCase("CAV")) || (!alberiDomCav && area.getCodTipoCampione().equalsIgnoreCase("CAV"))) {
					Row row = sheet.createRow(rowNum++);
					
					row.createCell(0).setCellValue(area.getCodiceAds());

					row.createCell(1).setCellValue(area.getCodTipoCampione());

					row.createCell(2).setCellValue(area.getSpecieLationo());

					row.createCell(3).setCellValue(area.getQualita());

					row.createCell(4).setCellValue(area.getDiametro());

					row.createCell(5).setCellValue(area.getAltezza());

					row.createCell(6).setCellValue(area.getIncremento());

					row.createCell(7).setCellValue(area.getEta());

					row.createCell(8).setCellValue(area.getNote());
				}
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		return rowNum;
	}

	@Override
	public Response excelAlberiCavallettati(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<AlberiCampioneDominanteDTO> list;
		List<AberiCAMeDOMEnum> columns = Arrays.asList(AberiCAMeDOMEnum.values());

		list = wrapperAdsDAO.findAlberiCampioneDominante(
				createWhereClauseForSearch(excel.getCodiceADSList().get(0), searchForAlberiCampioneDominante()),
				parameters);

		// Create a Sheet
		Sheet sheet = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (list != null) {
			for (AlberiCampioneDominanteDTO area : list) {
				if (area.getCodTipoCampione().equalsIgnoreCase("CAV")) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(1).setCellValue(area.getCodTipoCampione());

					row.createCell(2).setCellValue(area.getSpecieLationo());

					row.createCell(3).setCellValue(area.getQualita());

					row.createCell(4).setCellValue(area.getDiametro());

					row.createCell(5).setCellValue(area.getAltezza());

					row.createCell(6).setCellValue(area.getIncremento());

					row.createCell(7).setCellValue(area.getEta());

					row.createCell(8).setCellValue(area.getNote());
				}
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "alberi_cav_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	@Override
	public Response excelRelascopicaSemplice(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			HSSFWorkbook hwb = new HSSFWorkbook();
			ResponseBuilder response = null;
			List<RelascopicaSempliceDTO> list;

			list = wrapperAdsDAO.findRelascopica(
					createWhereClauseForSearch(codiceAds, createRelascopicaQueryForSearch()), parameters);

			// Create a Sheet
			Sheet sheet = hwb.createSheet("Area di saggio-Relascopica Semplice");

			// Create a Font for styling header cells
			Font headerFont = hwb.createFont();

			headerFont.setFontHeightInPoints((short) 16);
			headerFont.setColor(IndexedColors.WHITE.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = hwb.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setWrapText(true);
			headerCellStyle.setFillPattern((short) FillPatternType.SOLID_FOREGROUND.ordinal());
			headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			headerCellStyle.setBorderTop((short) BorderStyle.THIN.ordinal());
			headerCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());

			// Create a Row

			if (list != null) {

				if (list.size() > 0) {
					RelascopicaSempliceDTO area = list.get(0);
					createRelascopicaDetailsContent(sheet, area);
				}

				int rowNum = 7;

				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));
				Row mergeHeaderRow = sheet.createRow(rowNum++);
				Cell mergedHeader = mergeHeaderRow.createCell(1);
				mergedHeader.setCellStyle(headerCellStyle);
				mergedHeader.setCellValue("Conteggio Angolare");
				mergeHeaderRow.setHeightInPoints(24);

				Row headerRow = sheet.createRow(rowNum++);
				headerRow.setHeightInPoints(24);

				String relascopicaHeaders[] = { RelascopicaSempliceEnum.SPECIE.getValue(),
						RelascopicaSempliceEnum.N_ALBERI_CONTATI_SEME.getValue(),
						RelascopicaSempliceEnum.N_ALBERI_CONTATI_SEME.getValue(),
						RelascopicaSempliceEnum.NOTE.getValue() };

				for (int i = 1; i < relascopicaHeaders.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(relascopicaHeaders[i - 1]);
					cell.setCellStyle(headerCellStyle);
				}

				Row row;

				for (RelascopicaSempliceDTO area : list) {

					row = sheet.createRow(rowNum++);

					row.createCell(1).setCellValue(area.getSpecieLatino());
					row.createCell(2).setCellValue(area.getAlberiSeme());
					row.createCell(3).setCellValue(area.getAlberiPollone());
					row.createCell(4).setCellValue(area.getNote());
				}
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < 8; i++) {
				sheet.autoSizeColumn(i);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				logger.fatal("An exception occurred.", e);
			}

			LocalDateTime now = LocalDateTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

			String formatDateTime = now.format(formatter);

			String filename = "relascopica_semplice_" + formatDateTime;
			response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");

			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	private void createRelascopicaDetailsContent(Sheet sheet, RelascopicaSempliceDTO area) {
		// TODO Auto-generated method stub
		int rowNum = 1;

		Row row = sheet.createRow(rowNum++);

		row.createCell(0).setCellValue(RelascopicaSempliceEnum.COMUNE.getValue());
		row.createCell(1).setCellValue(area.getComune());
		row.createCell(2).setCellValue(RelascopicaSempliceEnum.PARTICELLA.getValue());
		row.createCell(3).setCellValue(area.getParticella());
		row.createCell(4).setCellValue(RelascopicaSempliceEnum.UTM_EST.getValue());
		row.createCell(5).setCellValue(area.getUtmEst() != null ? area.getUtmEst().doubleValue() : null);
		row.createCell(6).setCellValue(RelascopicaSempliceEnum.UTM_NORD.getValue());
		row.createCell(7).setCellValue(area.getUtmNord() != null ? area.getUtmNord().doubleValue() : null);

		row = sheet.createRow(rowNum++);

		row.createCell(0).setCellValue(RelascopicaSempliceEnum.QUOTA.getValue());
		row.createCell(1).setCellValue(area.getQuota());
		row.createCell(2).setCellValue(RelascopicaSempliceEnum.ESPOSIZIONE.getValue());
		row.createCell(3).setCellValue(area.getEspozione());
		row.createCell(4).setCellValue(RelascopicaSempliceEnum.INCLINAZIONE.getValue());
		row.createCell(5).setCellValue(area.getInclinazione());
		row.createCell(6).setCellValue(RelascopicaSempliceEnum.FATTORE_DI_NUMERAZIONE_RELASCOPICA.getValue());
		row.createCell(7).setCellValue(area.getFattoreNumerazione());

		row = sheet.createRow(rowNum++);
		row.createCell(0).setCellValue(RelascopicaSempliceEnum.CATEGORIA_FORESTALE.getValue());
		row.createCell(1).setCellValue(area.getCategoriaForestale());
		row.createCell(2).setCellValue(RelascopicaSempliceEnum.TIPO_FORESTALE.getValue());
		row.createCell(3).setCellValue(area.getTipoForestale());
		row.createCell(4).setCellValue(RelascopicaSempliceEnum.PROPRIETA.getValue());
		row.createCell(5).setCellValue(area.getProprieta());
		row.createCell(6).setCellValue(RelascopicaSempliceEnum.TIPO_STRUTTURALE.getValue());
		row.createCell(7).setCellValue(area.getTipoStrutturale());

		// Resize all columns to fit the content size
		for (int i = 0; i < 8; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public Response excelRelascopicaCompleta(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		// RelascopicaSempliceDTO has all data same as RelascopicaCompleta
		List<RelascopicaSempliceDTO> relascopicaList;

		relascopicaList = wrapperAdsDAO
				.findRelascopica(createWhereClauseForSearch(codiceAds, createRelascopicaQueryForSearch()), parameters);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Sheet sheet = hwb.createSheet("Area di saggio-Relascopica Completa");
		// Create a Sheet
		if (relascopicaList != null && relascopicaList.size() > 0) {
			RelascopicaSempliceDTO area = relascopicaList.get(0);
			createRelascopicaDetailsContent(sheet, area);
			sheet = hwb.createSheet("Alberi cavaleti");
			addRelascopicaAlberiCAvalleattatiSheet(hwb, sheet, relascopicaList);

			try {
				hwb.write(baos);
			} catch (IOException e) {
				logger.fatal("An exception occurred.", e);
			}
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "relascopica_completa_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	private void addRelascopicaAlberiCAvalleattatiSheet(HSSFWorkbook hwb, Sheet sheet,
			List<RelascopicaSempliceDTO> relascopicaList) {

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();
		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		List<RelascopicaCompletaEnum> columns = Arrays.asList(RelascopicaCompletaEnum.values());
		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (relascopicaList != null) {
			for (RelascopicaSempliceDTO area : relascopicaList) {

				if (area.getDiametro() != null || area.getAltezza() != null || area.getIncremento() != null) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(area.getSpecieLatino());
					row.createCell(1).setCellValue(area.getTipo());
					row.createCell(2).setCellValue(area.getDiametro());
					row.createCell(3).setCellValue(area.getAltezza());
					row.createCell(4).setCellValue(area.getIncremento());
				}
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public Response excelDatiStazionali(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<AreaDiSaggio> list;
		List<DatiStazionaliEnum> columns = Arrays.asList(DatiStazionaliEnum.values());

		list = wrapperAdsDAO.findAreaDiSaggioByCodiceAdsList(
				createWhereClauseForSearchIn(excel.getCodiceADSList(), createMainQueryForSearch1()));

		// Create a Sheet
		Sheet sheet = hwb.createSheet("Area di saggio-Dati Stazionali");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (list != null) {

			for (AreaDiSaggio area : list) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(area.getComune());
				row.createCell(1).setCellValue((area.getUtmEst() != null) ? area.getUtmEst().doubleValue() : 0);
				row.createCell(2).setCellValue((area.getUtmNord() != null) ? area.getUtmNord().doubleValue() : 0);
				row.createCell(3).setCellValue(area.getQuota());
				row.createCell(4).setCellValue(area.getEspozione());
				row.createCell(5).setCellValue(area.getInclinazione());
				row.createCell(6).setCellValue(area.getDensitaCamp());
				row.createCell(7).setCellValue(area.getRaggioArea());
				row.createCell(8).setCellValue(area.getParticellaForestale());
				row.createCell(9).setCellValue(area.getProprieta());
				row.createCell(10).setCellValue(area.getCategoriaForestale());
				row.createCell(11).setCellValue(area.getTipoForestale());
				row.createCell(12).setCellValue(area.getAssettoEvolutivoColturale());
				row.createCell(13).setCellValue(area.getTipoStrutturale());
				row.createCell(14).setCellValue(area.getStadioDiSviluppo());
				row.createCell(15).setCellValue(area.getClasseDiFertilita());
				row.createCell(16).setCellValue(area.getnCepaie());
				row.createCell(17).setCellValue(area.getRinnovazione());
				row.createCell(18).setCellValue(area.getSpeciePrevalenteRinnovazione());
				row.createCell(19).setCellValue(area.getCoperturaChiome());
				row.createCell(20).setCellValue(area.getCoperturaCespugli());
				row.createCell(21).setCellValue(area.getCoperturaErbacea());
				row.createCell(22).setCellValue(area.getLettiera());
				row.createCell(23).setCellValue(area.getDestinazione());
				row.createCell(24).setCellValue(area.getIntervento());
				row.createCell(25).setCellValue(area.getPriorita());
				row.createCell(26).setCellValue(area.getEsbosco());
				row.createCell(27).setCellValue(area.getDefp());
				row.createCell(28).setCellValue(area.getDesp());
				row.createCell(29).setCellValue(area.getMdp());
				row.createCell(30).setCellValue(area.getDannoPrevalente());
				row.createCell(31).setCellValue(area.getIntesitaDanni());
				row.createCell(32).setCellValue(area.getnPianteMorte());
				row.createCell(33).setCellValue(area.getDefogliazione());
				row.createCell(34).setCellValue(area.getIngiallimento());
				row.createCell(35).setCellValue(area.getPascolamento());
				row.createCell(36).setCellValue(area.getCombustibilePrincipale());
				row.createCell(37).setCellValue(area.getCombustibileSecondario());
				row.createCell(38).setCellValue("");// todo tavola

			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "area_di_saggio_dati_stazionali" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	// input.getFkSoggettoPf() == null || input.getFkSoggettoPg() == null
	private boolean validationObligatory(AreaDiSaggioDTO input) {
		return (input.getCodiceADS() == null || input.getAmbitoDiRilevamento() == null || input.getComune() == null
				|| input.getDataRilevamento() == null || input.getEspozione() == null || input.getInclinazione() == null
				|| input.getParticellaForestale() == null || input.getProprieta() == null || input.getQuota() == null
				|| input.getTipologiaDiRilievo() == null && input.getUtmEST() == null || input.getUtmNORD() == null
				|| input.getDettaglioAmbito() == null);
	}

	private boolean validationCodiceADS(AreaDiSaggioDTO input) {
		try {
			wrapperAdsDAO.findAreaDiSaggioByCodiceAds(
					createWhereClauseForSearch(input.getCodiceADS(), createMainQueryForSearch1()), parameters);
		} catch (RecordNotFoundException e) {
			return false;
		}
		return true;
	}

	@Override
	public Response insert(AreaDiSaggioDTO areaDiSaggio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			if (validationObligatory(areaDiSaggio)) {
				return BaseResponses.requiredField();
			}
			if (validationCodiceADS(areaDiSaggio)) {
				return BaseResponses.itemFound();
			}
			return Response
					.ok(wrapperAdsDAO.createAreaDiSaggio(areaDiSaggio,
							soggettoDAO.findSoggettoByCodiceFiscale(userSessionDAO.getUser(httpRequest).getCodFisc())))
					.build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		}
	}

	@Override
	@Transactional
	public Response insertSuperficieDati1(AreaDiSaggio areaDiSaggio, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		wrapperAdsDAO.insertSuperficieDati1(areaDiSaggio);
		return Response.ok().build();

	}

	@Override
	public Response getRelascopicaQueryForSearch(String codiceAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<RelascopicaSempliceDTO> ads = wrapperAdsDAO
				.findRelascopica(createWhereClauseForSearch(codiceAds, createRelascopicaQueryForSearch()), parameters);
		return Response.ok(ads).build();
	}

	public Response getRelascopicaCompleta(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<RelascopicaSempliceDTO> ads = wrapperAdsDAO.findRelascopicaCompleta(
				createWhereClauseForSearch(codiceAds, createRelascopicaCompletaSearch()), parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response insertSuperficieDati2(AreaDiSaggio areaDiSaggio, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		wrapperAdsDAO.insertSuperficieDati2(areaDiSaggio);
		return Response.ok().build();
	}

	public static String translateFieldName(String fieldName) {
		switch (fieldName) {
		case "codiceADS":
			return "codice_ads";
		case "comune":
			return "denominazione_comune";
		case "ambitoDiRilevamento":
			return "descr_ambito";
		case "dettaglioAmbito":
			return "descr_ambito";
		case "tipologia":
			return "descr_tipo_ads";
		case "dataRilevamento":
			return "data_ril";
		case "categoriaForestale":
			return "tipo";
		case "specie":
			return "id_specie";
		case "tipo":
			return "flg_pollone_seme";
		case "diametro":
			return "diametro";
		case "altezza":
			return "altezza";
		case "incremento":
			return "incremento";
		case "gruppo":
			return "cod_gruppo";
		case "seme":
			return "flg_pollone_seme";
		case "denominazionePfa":
			return "pfa.denominazione";
		case "provinciaPfa":
			return "prov.denominazione_prov";
		case "comunePfa":
			return "comune.denominazione_comune";

		default:
			return null;
		}
	}

	@Override
	public Response getNumberOfNextStep(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperAdsDAO.getNumberOfLastCompletedStep(codiceAds)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getRelascopicaCompletaSort(String codiceAds, int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		PaginatedList<RelascopicaSempliceDTO> ads = wrapperAdsDAO.findRelascopicaCompletaSort(
				createWhereClauseForSearchCompleta(codiceAds, createRelascopicaQueryForSearch(), sort), page, limit,
				parameters);
		return Response.ok(ads).build();
	}

	private StringBuilder createWhereClauseForSearchCompleta(String codiceAds,
			StringBuilder createRelascopicaCompletaSearch, String sort) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (codiceAds != null) {
			sql.append(sep).append(" ads.codice_ads = ?");
			parameters.add(codiceAds);
		}
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return createRelascopicaCompletaSearch().append(sql).append(sortSql);
		}

		return createRelascopicaCompletaSearch.append(sql);
	}

	private StringBuilder createWhereClauseForSearchCompletaCAV(String codiceAds,
			StringBuilder createRelascopicaCompletaSearch, String sort) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (codiceAds != null) {
			sql.append(sep).append(" s.cod_tipo_campione = 'CAV'").append(" AND").append(" ads.codice_ads = ?");
			parameters.add(codiceAds);
		}
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return createRelascopicaCompletaSearch.append(sql).append(sortSql);
		}

		return createRelascopicaCompletaSearch.append(sql);
	}

	@Override
	public Response getAlberiCAV(String codiceAds, int page, int limit, String sort, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		PaginatedList<AlberiCampioneDominanteDTO> ads = wrapperAdsDAO.findAlberiCAV(
				createWhereClauseForSearchCAV(codiceAds, searchForAlberiCampioneDominante(), sort), page, limit,
				parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response getRelascopicaCompletaSortCAV(String codiceAds, int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		PaginatedList<RelascopicaSempliceDTO> ads = wrapperAdsDAO.findRelascopicaCompletaSort(
				createWhereClauseForSearchCompletaCAV(codiceAds, createRelascopicaQueryForSearch(), sort), page, limit,
				parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response getSuperficieDati1(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		AreaDiSaggio ads;
		try {
			ads = wrapperAdsDAO.findDatiStazionali1(
					createWhereClauseForSearch(codiceAds, createMainQueryForGetDatiStazionali1()), parameters);
		} catch (RecordNotFoundException e) {
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}
		return Response.ok(ads).build();
	}

	@Override
	public Response getSuperficieDati2(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		AreaDISaggioDataStazionaliTwo ads;
		try {
			ads = wrapperAdsDAO.findDatiStazionali2(codiceAds);
		} catch (RecordNotFoundException e) {
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}
		return Response.ok(ads).build();
	}

}
