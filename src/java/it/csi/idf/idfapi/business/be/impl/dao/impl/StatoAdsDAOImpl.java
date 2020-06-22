package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.StatoAdsDAO;
import it.csi.idf.idfapi.dto.StatoAds;
import it.csi.idf.idfapi.mapper.StatoAdsMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class StatoAdsDAOImpl implements StatoAdsDAO {

	@Override
	public List<StatoAds> search() {
		String SQL = "SELECT * FROM idf_d_stato_ads";
		return DBUtil.jdbcTemplate.query(SQL, new StatoAdsMapper());	
	}

}
