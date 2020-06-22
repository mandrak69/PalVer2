package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.dto.DichPropCatasto;
import it.csi.idf.idfapi.dto.PlainIntDTO;
import it.csi.idf.idfapi.dto.PlainStringDTO;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;
import it.csi.idf.idfapi.mapper.DichPropCatastoMapper;
import it.csi.idf.idfapi.mapper.PropCatastoIntColumnMapper;
import it.csi.idf.idfapi.mapper.PropCatastoMapper;
import it.csi.idf.idfapi.mapper.PropCatastoStringColumnMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PropCatastoDAOImpl implements PropCatastoDAO {
	
	private static final String SEZIONE_COLUMN_NAME = "sezione";
	private static final String FOGLIO_COLUMN_NAME = "foglio";
	private static final String PARTICELLA_COLUMN_NAME = "particella";
	
	private final PropCatastoMapper propCatastoMapper = new PropCatastoMapper();
	private final DichPropCatastoMapper dichPropCatastoMapper = new DichPropCatastoMapper();
	private final PropCatastoStringColumnMapper propCatastoSezioniMapper = new PropCatastoStringColumnMapper(SEZIONE_COLUMN_NAME);
	private final PropCatastoIntColumnMapper propCatastoFoglioMapper = new PropCatastoIntColumnMapper(FOGLIO_COLUMN_NAME);
	private final PropCatastoStringColumnMapper propCatastoParticellaMapper = new PropCatastoStringColumnMapper(PARTICELLA_COLUMN_NAME);
	
	@Override
	public List<PropCatasto> findAllCatasti() {
		
		String sql = "SELECT * FROM idf_geo_pl_prop_catasto";
		
		return DBUtil.jdbcTemplate.query(sql, propCatastoMapper);
	}

	@Override
	public PropCatasto findCatastoByID(Integer idGeoPlPropCatasto) throws RecordNotFoundException {
		
		String sql = "SELECT * FROM idf_geo_pl_prop_catasto c WHERE c.idgeo_pl_prop_catasto = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, propCatastoMapper, idGeoPlPropCatasto);
	}

	@Override
	public int insertPropCatasto(PropCatasto propCatasto) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_prop_catasto(");
		sql.append("idgeo_pl_pfa, fk_comune, sezione, foglio, allegato_catasto");
		sql.append(", sviluppo_catasto, particella, sup_catastale_mq, sup_cartografica_ha, fk_proprieta, intestato");
		sql.append(", qualita_coltura, flg_usi_civici, flg_possessi_contest, flg_livellari, data_inizio_validita");
		sql.append(", data_fine_validita, note, data_aggiornamento_datocatast, data_inserimento, fk_config_utente");
		sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(propCatasto.getIdGeoPlPfa());
		parameters.add(propCatasto.getFkComune());
		parameters.add(propCatasto.getSezione());
		parameters.add(propCatasto.getFoglio());
		parameters.add(propCatasto.getAllegatoCatasto());
		parameters.add(propCatasto.getSviluppoCatasto());
		parameters.add(propCatasto.getParticella());
		parameters.add(propCatasto.getSupCatastaleMq());
		parameters.add(propCatasto.getSupCartograficaHa());
		parameters.add(propCatasto.getFkProprieta());
		parameters.add(propCatasto.getIntestato());
		parameters.add(propCatasto.getQualitaColtura());
		parameters.add(propCatasto.getFlgUsiCivici());
		parameters.add(propCatasto.getFlgPossessiContest());
		parameters.add(propCatasto.getFlgLivellari());
		parameters.add(propCatasto.getDataInizioValidita() == null ? null : Date.valueOf(propCatasto.getDataInizioValidita()));
		parameters.add(propCatasto.getDataFineValidita()  == null ? null : Date.valueOf(propCatasto.getDataFineValidita()));
		parameters.add(propCatasto.getNote());
		parameters.add(propCatasto.getDataAggiornamentoDatocatast()  == null ? null : Date.valueOf(propCatasto.getDataAggiornamentoDatocatast()));
		parameters.add(propCatasto.getDataInserimento()  == null ? null : Date.valueOf(propCatasto.getDataInserimento()));
		//parameters.add(propCatasto.getGeometria());
		parameters.add(propCatasto.getFkConfigUtente());
		
		return AIKeyHolderUtil.updateWithGenKey("idgeo_pl_prop_catasto", sql.toString(), parameters);
	}

	@Override
	public List<PropCatasto> getPropCatastosByPropcatastoIntervento(List<PropcatastoIntervento> propcatastoIntervento) {
		
		if(propcatastoIntervento.isEmpty()) {
            return Collections.emptyList();
        }
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idgeo_pl_prop_catasto, idgeo_pl_pfa, fk_comune, sezione, foglio, allegato_catasto");
		sql.append(", sviluppo_catasto, particella, sup_catastale_mq, sup_cartografica_ha, fk_proprieta, intestato");
		sql.append(", qualita_coltura, flg_usi_civici, flg_possessi_contest, flg_livellari, data_inizio_validita");
		sql.append(", data_fine_validita, note, data_aggiornamento_datocatast, data_inserimento, fk_config_utente");
		sql.append(" FROM idf_geo_pl_prop_catasto WHERE idgeo_pl_prop_catasto IN(");
		
		for(int i = 0; i < propcatastoIntervento.size(); i++) {
			sql.append(propcatastoIntervento.get(i).getIdgeoPlPropCatasto());
			if(i != propcatastoIntervento.size() -1) {
				sql.append(", ");
			}
		}
		sql.append(")");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoMapper);
	}
	
	@Override
	public List<DichPropCatasto> getDichPropCatastosByPropcatastoIntervento(
			List<PropcatastoIntervento> propcatastoIntervento) {
		if(propcatastoIntervento.isEmpty()) {
            throw new IllegalArgumentException("List<PropcatastoIntervento> can not be empty!");
        }
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cat.sezione, cat.foglio, cat.particella, cat.sup_catastale_mq, cat.sup_cartografica_ha");
		sql.append(", cmn.denominazione_comune, prov.denominazione_prov");
		sql.append(" FROM idf_geo_pl_prop_catasto cat");
		sql.append(" JOIN idf_geo_pl_comune cmn ON cat.fk_comune = cmn.id_comune");
		sql.append(" JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov");
		sql.append(" WHERE cat.idgeo_pl_prop_catasto IN(");
		
		for(int i = 0; i < propcatastoIntervento.size(); i++) {
			sql.append(propcatastoIntervento.get(i).getIdgeoPlPropCatasto());
			if(i != propcatastoIntervento.size() -1) {
				sql.append(", ");
			}
		}
		sql.append(")");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), dichPropCatastoMapper);
	}

	@Override
	public void deletePropCatasto(int idGeoPlPropCatasto) {
		String sql = "DELETE FROM idf_geo_pl_prop_catasto WHERE idgeo_pl_prop_catasto = ?";
		DBUtil.jdbcTemplate.update(sql, idGeoPlPropCatasto);
	}

	@Override
	public List<PlainStringDTO> findSezioniByComune(int fkComune) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto WHERE fk_comune = ?");
		sql.append(" ORDER BY ");
		sql.append(SEZIONE_COLUMN_NAME);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoSezioniMapper, fkComune);
	}

	@Override
	public List<PlainIntDTO> findFogliByComune(int fkComune) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(FOGLIO_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto WHERE fk_comune = ?");
		sql.append(" ORDER BY ");
		sql.append(FOGLIO_COLUMN_NAME);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoFoglioMapper, fkComune);
	}

	@Override
	public List<PlainStringDTO> findParticelleByComune(int fkComune) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto WHERE fk_comune = ?");
		sql.append(" ORDER BY ");
		sql.append(PARTICELLA_COLUMN_NAME);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoParticellaMapper, fkComune);
	}

	@Override
	public void updateAllNoteOfOneIntervento(String annotazioni, int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_geo_pl_prop_catasto SET note = ?");
		sql.append(" WHERE idgeo_pl_prop_catasto IN(");
		sql.append("SELECT idgeo_pl_prop_catasto FROM idf_r_propcatasto_intervento");
		sql.append(" WHERE id_intervento = ?)");
		
		DBUtil.jdbcTemplate.update(sql.toString(), annotazioni, idIntervento);
	}
}
