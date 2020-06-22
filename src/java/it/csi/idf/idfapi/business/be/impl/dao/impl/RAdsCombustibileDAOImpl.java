package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.RAdsCombustibileDAO;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class RAdsCombustibileDAOImpl implements RAdsCombustibileDAO {

	String[] adsCombustibileColumns = { "idgeo_pt_ads", "cod_combustibile", "flg_principale", "perc_copertura_lettiera",
			"perc_copertura_erbacea", "perc_copertura_cespugli" };
	String tableName = "idf_r_ads_combustibile";
	String pkColumnName = "idgeo_pt_ads";

	@Override
	public void saveCombustibile(AreaDiSaggio areaDiSaggio) {

		if (!adsCombustibileExists(areaDiSaggio)) {
			insertCombustibile(areaDiSaggio);
		} else {
			updateCombustibile(areaDiSaggio);
		}

	}

	private void insertCombustibile(AreaDiSaggio areaDiSaggio) {
		DBUtil.jdbcTemplate.update(DBUtil.createInsertQueryString(tableName, adsCombustibileColumns),
				getParametersForAllColumns(areaDiSaggio, false).toArray());
	}

	private void updateCombustibile(AreaDiSaggio areaDiSaggio) {
		DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, adsCombustibileColumns, pkColumnName),
				getParametersForAllColumns(areaDiSaggio, true).toArray());
	}

	public boolean adsCombustibileExists(AreaDiSaggio areaDiSaggio) {
		String countSQL = DBUtil.createCountByPkQuery(tableName, pkColumnName);
		return DBUtil.jdbcTemplate.queryForInt(countSQL, Long.parseLong(areaDiSaggio.getCodiceADS())) > 0;
	}

	private List<Object> getParametersForAllColumns(AreaDiSaggio areaDiSaggio, boolean updateQuery) {

		List<Object> parameters = new ArrayList<>();

		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
		parameters.add("0");// mocked primary key
		parameters.add(1);// mocked primary key
		parameters.add(Integer.parseInt(areaDiSaggio.getLettiera()));
		parameters.add(Integer.parseInt(areaDiSaggio.getCoperturaErbacea()));
		parameters.add(Integer.parseInt(areaDiSaggio.getCoperturaCespugli()));

		if (updateQuery) {
			parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
		}

		return parameters;
	}

}
