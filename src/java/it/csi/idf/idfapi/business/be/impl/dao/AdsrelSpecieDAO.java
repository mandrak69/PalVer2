package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.PlainAdsrelSpecie;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;

public interface AdsrelSpecieDAO {
	
	List<RAdsrelSpecie> search(); 
	void insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) throws Exception;
	void updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	int saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie);
	int deleteByCodiceAdsNotCAV(Long idgeoPtAds);
	int deleteByCodiceAdsCAV(Long idgeoPtAds);
	int saveAdsrelSpecieCAV(RAdsrelSpecie radsrelSpecie);
	int saveAdsrelSpecieForRelascopica(RAdsrelSpecie plainAdsrelSpecie, Long codiceADS);
	int saveAdsrelSpecieAlberi(PlainAdsrelSpecie plainAdsrelSpecie, String codiceADS,Integer idSpecie);
	List<RAdsrelSpecie> searchByCodiceADS(String codiceADS);
	List<RAdsrelSpecie> searchAdsRelSpecByCodiceADSForCAV(String codiceADS);
	void setDataFineValidaForAdsSpecieDOMeCAM(Long idgeoPtAds);
	int updateAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie);
}
