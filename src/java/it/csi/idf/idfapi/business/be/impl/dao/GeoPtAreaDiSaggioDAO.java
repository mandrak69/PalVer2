package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.PaginatedList;


public interface GeoPtAreaDiSaggioDAO {
	PaginatedList<AreaDiSaggio> search(StringBuilder s,int page, int limit, List<Object>parameters);
	List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s,List<Object> parameters);
	PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,List<Object> parameters);
	List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters);
	List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters);
	PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(StringBuilder s, int page, int limit,List<Object> parameters);
	List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s);
	List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s);
	AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	AreaDiSaggio findDatiStazionali1(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	AreaDISaggioDataStazionaliTwo findDatiStazionali2(String codiceAds) throws RecordNotFoundException;
	AreaDiSaggio findADSByCodiceADS(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio,TSoggetto soggetto) throws RecordNotFoundException;
	int updateAreaDiSaggioD1(AreaDiSaggio areaDiSaggio);
	int updateAreaDiSaggioD2(AreaDiSaggio areaDiSaggio);

}
