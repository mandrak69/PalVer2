package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.PaginatedList;

@Transactional(rollbackFor=Exception.class)
public interface WrapperAdsDAO {
	
	PaginatedList<AreaDiSaggio> search(StringBuilder s,int page, int limit, List<Object>parameters);
	List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s,List<Object> parameters);
	PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,List<Object> parameters);
	List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters);
	List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters);
	List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s);
	List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s);
	AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	AreaDiSaggio findDatiStazionali1(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	AreaDISaggioDataStazionaliTwo findDatiStazionali2(String codiceADS) throws RecordNotFoundException;
	AreaDiSaggio findADSByCodiceADS(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio,TSoggetto tSoggetto) throws RecordNotFoundException;

	StepNumber getNumberOfLastCompletedStep(String codiceAds);

	void insertSuperficieDati1(AreaDiSaggio areaDiSaggio);
	void insertSuperficieDati2(AreaDiSaggio areaDiSaggio);
	void saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie);
	void saveAdsrelSpecieCAV(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(StringBuilder createWhereClauseForSearchCompleta,
			int page, int limit, List<Object> parameters);
	
	void insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	void updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie);
}
