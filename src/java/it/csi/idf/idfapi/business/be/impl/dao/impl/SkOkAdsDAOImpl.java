package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkAdsDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SkOkAdsDAOImpl implements SkOkAdsDAO {

	private String[] columnNames = { "flg_sez1", "flg_sez2", "flg_sez3", "flg_sez4" };
	private String tableName = "idf_cnf_sk_ok_ads";
	private String pkColumnName = "idgeo_pt_ads";

	public SkOkAdsDAOImpl() {

	}

	@Override
	public void insertFlgSez1(String codiceAds, int stepNum) {

		StringBuilder flagColumns = new StringBuilder();
		StringBuilder columnValues = new StringBuilder();

		for (int i = 0; i < columnNames.length; i++) {
			flagColumns.append("," + columnNames[i]);
			if (i < stepNum) {
				columnValues.append(",1");
			} else {
				columnValues.append(",0");
			}
		}

		StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s(", tableName));
		sql.append(pkColumnName).append(flagColumns).append(") VALUES (?").append(columnValues + ")");
		DBUtil.jdbcTemplate.update(sql.toString(), Integer.parseInt(codiceAds));
	}

	@Override
	public void updateStepFinished(Long codiceAds, int stepNumber) {
		// There are lot of plans which are already saved and doesn't have flags set
		try {
		if (skOdsExists(codiceAds)) {
			StringBuilder sql = new StringBuilder(String.format("UPDATE %s SET ", tableName));
			sql.append(String.format("%s = 1 ", columnNames[stepNumber-1])).append(getWhereClause());
			DBUtil.jdbcTemplate.update(sql.toString(), codiceAds);
		} else {
			insertFlgSez1(codiceAds.toString(), stepNumber);
		}
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public boolean isStepDone(Long codiceAds, int stepNumber) {
		StringBuilder sql = new StringBuilder(String.format("Select %s  FROM %s ", columnNames[stepNumber], tableName));
		sql.append(getWhereClause());
		int flagValue = DBUtil.jdbcTemplate.queryForInt(sql.toString(), codiceAds);
		return flagValue == 1;
	}

	@Override
	public int getLastStepDone(Long codiceAds) {

		StringBuilder sumStatementParams = new StringBuilder();
		// = IntStream.range(0, columnNames.length).mapToObj(i -> {if (i==0) return
		// columnNames[i]; else return "+".concat(columnNames[i]);} ).reduce("", (acc,
		// val)-> acc.concat(val));
		String plus = "";
		for (int i = 0; i < columnNames.length; i++) {
			sumStatementParams.append(plus + columnNames[i]);
			if (i == 0) {
				plus = "+";
			}
			;
		}

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("SELECT SUM(%s)", sumStatementParams)).append(String.format("FROM %s ", tableName))
				.append(getWhereClause());
		return DBUtil.jdbcTemplate.queryForInt(sql.toString(), codiceAds);
	}

	@Override
	public String getWhereClause() {
		return String.format(" WHERE %s = ?", pkColumnName);
	}

	public boolean skOdsExists(Long codiceAds) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM " + tableName).append(getWhereClause());
		return DBUtil.jdbcTemplate.queryForInt(sql.toString(), codiceAds) > 0;
	}

}
