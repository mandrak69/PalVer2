package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RelascopicaSempliceDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperRelascopicaDAO;
import it.csi.idf.idfapi.dto.PlainAdsrelSpecie;
import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;

@Component
public class WrapperRelascopicaDAOImpl implements WrapperRelascopicaDAO {

	@Autowired
	private RelascopicaSempliceDAO relascopicaSempliceDAO;

	@Autowired
	private AdsrelSpecieDAO adsrelSpecieDAO;

	@Transactional
	@Override
	public void insertRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice) {
		if (!relascopicaSempliceDAO.relascopicaExists(plainRelascopicaSemplice.getIdgeoPtAds())) {
			relascopicaSempliceDAO.saveRelascopicaSemplice(plainRelascopicaSemplice);
		} else {
			relascopicaSempliceDAO.updateRelascopica(plainRelascopicaSemplice);
		}
		adsrelSpecieDAO.deleteByCodiceAdsCAV(plainRelascopicaSemplice.getIdgeoPtAds());
		for (RAdsrelSpecie plainAdsrelSpecie : plainRelascopicaSemplice.getPlainAdsrelSpecie()) {
			adsrelSpecieDAO.saveAdsrelSpecieForRelascopica(plainAdsrelSpecie, plainRelascopicaSemplice.getIdgeoPtAds());
		}
	}

	@Override
	public void insertRelascopicaCompleta(PlainAdsrelSpecie plainAdsrelSpecie, String codiceAds, Integer idSpecie) {
		adsrelSpecieDAO.saveAdsrelSpecieAlberi(plainAdsrelSpecie, codiceAds, idSpecie);

	}

	public PlainRelascopicaSemplice getPlainRelascopicaByCodiceAds(String codiceAds) {
		PlainRelascopicaSemplice relSempl = new PlainRelascopicaSemplice();
		if (relascopicaSempliceDAO.relascopicaExists(Long.parseLong(codiceAds))) {
			relSempl = relascopicaSempliceDAO.getPlainRelascopicaSemplice(codiceAds);
			relSempl.setPlainAdsrelSpecie(adsrelSpecieDAO.searchAdsRelSpecByCodiceADSForCAV(codiceAds));
		}

		return relSempl;
	}

}
