package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtAreaDiSaggioDAO;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.mapper.AlberiCampioneDominanteDTOMapper;
import it.csi.idf.idfapi.mapper.AlberiCavallettatiMapper;
import it.csi.idf.idfapi.mapper.AreaDiSaggioDTOMapper;
import it.csi.idf.idfapi.mapper.AreaDiSaggioMapper;
import it.csi.idf.idfapi.mapper.DatiStazionali1Mapper;
import it.csi.idf.idfapi.mapper.DatiStazionaliTwoMapper;
import it.csi.idf.idfapi.mapper.RelascopicaSempliceMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;

@Component
public class GeoPtAreaDiSaggioDAOImpl extends GenericDAO implements GeoPtAreaDiSaggioDAO {

	@Override
	public PaginatedList<AreaDiSaggio> search(StringBuilder s, int page, int limit, List<Object> parameters) {
		return super.paginatedList(s.toString(), parameters, new AreaDiSaggioDTOMapper(), page, limit);
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s) {
		return DBUtil.jdbcTemplate.query(s.toString(), new AreaDiSaggioMapper());

	}

	public AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		try {
			return DBUtil.jdbcTemplate.queryForObject(s.toString(), new AreaDiSaggioMapper(), parameters.toArray());
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s, List<Object> parameters) {
		return DBUtil.jdbcTemplate.query(s.toString(), new AlberiCampioneDominanteDTOMapper(), parameters.toArray());
	}

	@Override
	public int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pt_area_di_saggio(");
		sql.append("idgeo_pt_ads, codice_ads, fk_tipo_ads, fk_ambito, data_ril, fk_comune, quota, fk_esposizione, ");// 8
		sql.append("inclinazione, idgeo_pl_particella_forest, fk_proprieta, fk_soggetto_pg, fk_soggetto, fk_settore, ");// 6
		sql.append(
				"fk_assetto,fk_comunita_montana,fk_tipo_forestale,fk_destinazione,fk_priorita,data_inizio_validita,fk_danno,fk_tipo_intervento,geometria, fk_stato_ads) ");// 9
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromText('POINT(");
		sql.append(areaDiSaggio.getUtmEST());
		sql.append(" ");
		sql.append(areaDiSaggio.getUtmNORD());
		sql.append(")', 32632)");
		sql.append(", ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
		parameters.add(areaDiSaggio.getCodiceADS());
		parameters.add(Integer.parseInt(areaDiSaggio.getTipologiaDiRilievo()));
		parameters.add(Integer.parseInt(areaDiSaggio.getAmbitoDiRilevamento()));
		parameters.add(areaDiSaggio.getDataRilevamento() == null ? null
				: java.sql.Date.valueOf(areaDiSaggio.getDataRilevamento()));
		parameters.add(Integer.parseInt(areaDiSaggio.getComune()));
		parameters.add(Integer.parseInt(areaDiSaggio.getQuota()));
		parameters.add(Integer.parseInt(areaDiSaggio.getEspozione()));
		parameters.add(Integer.parseInt(areaDiSaggio.getInclinazione()));
		parameters.add(Integer.parseInt(areaDiSaggio.getParticellaForestale()));
		parameters.add(Integer.parseInt(areaDiSaggio.getProprieta()));
		parameters.add(soggetto.getIdSoggetto());
		parameters.add(0);
		parameters.add(0);
		parameters.add(9);
		parameters.add(1); // Mocked Comunita montana
		parameters.add(1);// mocked fk_tipo_forestale
		parameters.add(0);
		parameters.add(0);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(0);
		parameters.add(1);
		parameters.add(1); // Bozza Status  TODO: VS Add Enumration

		return AIKeyHolderUtil.updateWithGenKey("idgeo_pt_ads", sql.toString(), parameters);
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters) {
		return DBUtil.jdbcTemplate.query(s.toString(), new RelascopicaSempliceMapper(), parameters.toArray());
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters) {
		return DBUtil.jdbcTemplate.query(s.toString(), new RelascopicaSempliceMapper(), parameters.toArray());
	}

	@Override
	public int updateAreaDiSaggioD1(AreaDiSaggio areaDiSaggio) {

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_geo_pt_area_di_saggio SET");
		update.append(" fk_tipo_forestale = ?");
		update.append(", fk_assetto= ?");
		update.append(" where idgeo_pt_ads = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(Integer.parseInt(areaDiSaggio.getTipoForestale()));
		parameters.add(Integer.parseInt(areaDiSaggio.getAssettoEvolutivoColturale()));
		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public int updateAreaDiSaggioD2(AreaDiSaggio areaDiSaggio) {

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_geo_pt_area_di_saggio SET");
		update.append(" fk_destinazione = ?");
		update.append(", fk_tipo_intervento = ?");
		update.append(", fk_priorita = ?");
		update.append(", fk_danno = ?");
		update.append(" where idgeo_pt_ads = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(Integer.parseInt(areaDiSaggio.getDestinazione()));
		parameters.add(Integer.parseInt(areaDiSaggio.getIntervento()));
		parameters.add(Integer.parseInt(areaDiSaggio.getPriorita()));
		parameters.add(Integer.parseInt(areaDiSaggio.getDannoPrevalente()));
		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s) {
		return DBUtil.jdbcTemplate.query(s.toString(), new AreaDiSaggioDTOMapper());
	}

	@Override
	public PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(StringBuilder s, int page, int limit,
			List<Object> parameters) {
		return super.paginatedList(s.toString(), parameters, new RelascopicaSempliceMapper(), page, limit);
	}

	@Override
	public AreaDiSaggio findADSByCodiceADS(StringBuilder s, List<Object> parameters) throws RecordNotFoundException {
		try {
			return DBUtil.jdbcTemplate.queryForObject(s.toString(), new AreaDiSaggioDTOMapper(), parameters.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecordNotFoundException();
		}

	}

	@Override
	public PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,
			List<Object> parameters) {
		return super.paginatedList(s.toString(), parameters, new AlberiCavallettatiMapper(), page, limit);
	}

	@Override
	public AreaDiSaggio findDatiStazionali1(StringBuilder s, List<Object> parameters) throws RecordNotFoundException {
		try {
			return DBUtil.jdbcTemplate.queryForObject(s.toString(), new DatiStazionali1Mapper(), parameters.toArray());
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public AreaDISaggioDataStazionaliTwo findDatiStazionali2(String codiceAds) throws RecordNotFoundException {
		try {

			StringBuilder sql = new StringBuilder();
			List<Object> parameters = new ArrayList<>();
			createMainQueryForDatiStazionaliPartTwo(codiceAds, sql);
			addWhereClauseForDatiStazionaliPartTwo(codiceAds, sql, parameters);

			return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DatiStazionaliTwoMapper(),
					parameters.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecordNotFoundException();
		}
	}

	private void createMainQueryForDatiStazionaliPartTwo(String codiceAds, StringBuilder sql) {
		sql.append("SELECT ads.codice_ads, ads.fk_destinazione, ads.fk_tipo_intervento, ads.fk_priorita, ads.fk_danno,")
				.append(" asn.cod_esbosco, asn.dist_esbosco_fuori_pista, asn.dist_esbosco_su_pista, asn.min_distanza_planimetrica, asn.danni_perc, asn.nr_piante_morte, asn.perc_defogliazione, asn.perc_ingiallimento, asn.flg_pascolamento")
				.append(" FROM idf_geo_pt_area_di_saggio ads ")
				.append(" INNER JOIN idf_t_ads_superficie_nota asn ON ads.idgeo_pt_ads = asn.idgeo_pt_ads");
	}

	private void addWhereClauseForDatiStazionaliPartTwo(String codiceAds, StringBuilder sql, List<Object> parameters) {
		String sep = " WHERE";
		if (codiceAds != null) {
			sql.append(sep).append(" ads.codice_ads = ?");
			parameters.add(codiceAds);
		}
	}

}
