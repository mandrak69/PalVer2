package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoAdsDAO;
import it.csi.idf.idfapi.dto.TipoAds;
import it.csi.idf.idfapi.mapper.TipoAdsMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoAdsDAOImpl implements TipoAdsDAO {

	@Override
	public List<TipoAds> findAllTipoAds() {
		String SQL = "SELECT * FROM idf_d_tipo_ads";
		return DBUtil.jdbcTemplate.query(SQL, new TipoAdsMapper());
	}
}
