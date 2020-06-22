package it.csi.idf.idfapi.business.be.impl.dao.impl;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.mapper.ComuneMapper;
import it.csi.idf.idfapi.mapper.ComuneResourceMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ComuneDAOImpl implements ComuneDAO {
	
	private final ComuneMapper comuneMapper = new ComuneMapper();
	private final ComuneResourceMapper comuneResourceMapper = new ComuneResourceMapper();
	private static final String ORDER_BY_DENOMINAZIONE_COMUNE = " ORDER BY denominazione_comune";

	@Override
	public List<ComuneResource> findAllComune() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune FROM idf_geo_pl_comune");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper);
	}

	@Override
	public Comune findComuneByID(Integer idComune) {
		//TODO: Check if this needs to be used in IDFINV, IDFPFA
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_geo_pl_comune");
		sql.append(" WHERE id_comune = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), comuneMapper, idComune);
	}
	
	@Override
	public Comune findComuneByName(String name) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_geo_pl_comune");
		sql.append(" WHERE denominazione_comune = ?");
		
		List<Comune> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneMapper, name);
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(comunes.isEmpty()) {
			return null;
		} else {
			return comunes.get(0);
		}
	}
	
	@Override
	public int createComune(Comune comune) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_comune (");
		sql.append("id_comune, istat_comune, istat_prov, denominazione_comune, fk_area_forestale");
		sql.append(", codice_belfiore, data_inizio_validita, data_fine_validita");
		sql.append(") VALUES (?,?,?,?,?,?,?,?)");
		
		List<Object>parameters= new ArrayList<>();
		parameters.add(comune.getIdComune());
		parameters.add(comune.getIstatComune());
		parameters.add(comune.getIstatProv());
		parameters.add(comune.getDenominazioneComune());
		parameters.add(comune.getFk_area_forestale());
		parameters.add(comune.getCodiceBelfiore());
		parameters.add(comune.getDataInizioValidita() == null ? null : Date.valueOf(comune.getDataInizioValidita()));
		parameters.add(comune.getDataFineValidita() == null ? null : Date.valueOf(comune.getDataFineValidita()));
		
		return	DBUtil.jdbcTemplate.update(sql.toString(),parameters.toArray());
	}

	@Override
	public ComuneResource findComuneResourceByID(Integer idComune) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE id_comune = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		
		List<ComuneResource> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, idComune);
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(comunes.isEmpty()) {
			return null;
		} else {
			return comunes.get(0);
		}
	}
	
	@Override
	public List<ComuneResource> findAllComuneByProvincia(String istatProv) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE istat_prov = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, istatProv);
	}

	@Override
	public ComuneResource findComuneResourceByIstatComune(String istatComune) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE istat_comune = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);

		List<ComuneResource> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, istatComune);
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(comunes.isEmpty()) {
			return null;
		} else {
			return comunes.get(0);
		}
	}
}
