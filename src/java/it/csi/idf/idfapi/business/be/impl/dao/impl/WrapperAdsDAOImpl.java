package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtAreaDiSaggioDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RAdsCombustibileDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkAdsDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SuperficieNotaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdsDAO;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.TipologiaEnum;

@Component
public class WrapperAdsDAOImpl extends GenericDAO implements WrapperAdsDAO {
	@Autowired
	private GeoPtAreaDiSaggioDAO geoPtAreaDiSaggioDAO;

	@Autowired
	private RAdsCombustibileDAO combustibile;

	@Autowired
	private SuperficieNotaDAO superficie;

	@Autowired
	private SkOkAdsDAO skOkAdsDAO;

	@Autowired
	private AdsrelSpecieDAO adsrelSpecieDAO;

	@Override
	public PaginatedList<AreaDiSaggio> search(StringBuilder s, int page, int limit, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.search(s, page, limit, parameters);
	}

	@Override
	public List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s, List<Object> parameters) {

		return geoPtAreaDiSaggioDAO.findAlberiCampioneDominante(s,parameters);
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.findRelascopica(s, parameters);
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.findRelascopicaCompleta(s, parameters);
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s) {
		return geoPtAreaDiSaggioDAO.findAreaDiSaggioByCodiceAdsList(s);
	}

	@Override
	public AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.findAreaDiSaggioByCodiceAds(s, parameters);
	}
	
	@Override
	public AreaDiSaggio findDatiStazionali1(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.findDatiStazionali1(s, parameters);
	}
	
	@Override
	public AreaDISaggioDataStazionaliTwo findDatiStazionali2(String codiceADS)
			throws RecordNotFoundException {		
		return geoPtAreaDiSaggioDAO.findDatiStazionali2(codiceADS);
	}
	
	@Override
	public int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.createAreaDiSaggio(areaDiSaggio, soggetto);
	}

	@Override
	public StepNumber getNumberOfLastCompletedStep(String codiceAds) {
		return new StepNumber(skOkAdsDAO.getLastStepDone(Long.parseLong(codiceAds)));
	}

	@Override
	public void insertSuperficieDati1(AreaDiSaggio areaDiSaggio) {
		if (areaDiSaggio.getTipologia().equalsIgnoreCase(TipologiaEnum.TEMPORANEA.getValue())
				&& areaDiSaggio.getCodiceADS() != null) {
			
			superficie.saveSuperficie(areaDiSaggio);			
			combustibile.saveCombustibile(areaDiSaggio);
			geoPtAreaDiSaggioDAO.updateAreaDiSaggioD1(areaDiSaggio);
			if (!skOkAdsDAO.skOdsExists(Long.parseLong(areaDiSaggio.getCodiceADS()))) {
				skOkAdsDAO.insertFlgSez1(areaDiSaggio.getCodiceADS(), 1);
			} else {
				skOkAdsDAO.updateStepFinished(Long.parseLong(areaDiSaggio.getCodiceADS()), 1);
			}

		}
	}

	@Override
	public void insertSuperficieDati2(AreaDiSaggio areaDiSaggio) {
		geoPtAreaDiSaggioDAO.updateAreaDiSaggioD2(areaDiSaggio);
		superficie.updateSuperficieDatiStazionaliTwo(areaDiSaggio);
		skOkAdsDAO.updateStepFinished(Long.parseLong(areaDiSaggio.getCodiceADS()), 2);
	}

	@Override
	public void saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie) {
		try {
		adsrelSpecieDAO.saveAdsrelSpecieDOMeCAM(radsrelSpecie);
		skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 3);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) {
		RAdsrelSpecie radsrelSpecie = null;
		if (listOfRAdsrelSpecie.size()> 0) {
			radsrelSpecie = listOfRAdsrelSpecie.get(0);
			adsrelSpecieDAO.setDataFineValidaForAdsSpecieDOMeCAM(radsrelSpecie.getIdgeoPtAds());
		}
		for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
			RAdsrelSpecie rAdsrelSpecie = (RAdsrelSpecie) iterator.next();
			adsrelSpecieDAO.saveAdsrelSpecieDOMeCAM(rAdsrelSpecie);
		}
		if ( radsrelSpecie != null) {skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 3);}
	}
	
	@Transactional
	public void updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) {

		RAdsrelSpecie radsrelSpecie = null;
		if (listOfRAdsrelSpecie.size()> 0) {
			radsrelSpecie = listOfRAdsrelSpecie.get(0);
			adsrelSpecieDAO.deleteByCodiceAdsNotCAV(radsrelSpecie.getIdgeoPtAds());
		}
		
		for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
			RAdsrelSpecie rAdsrelSpecie = (RAdsrelSpecie) iterator.next();
			adsrelSpecieDAO.saveAdsrelSpecieDOMeCAM(rAdsrelSpecie);
		}
		if ( radsrelSpecie != null) {skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 3); }		
	}
	
	
	
	@Transactional
	@Override
	public void saveAdsrelSpecieCAV(List<RAdsrelSpecie> listOfRAdsrelSpecie) {
						
		
		RAdsrelSpecie radsrelSpecie = null;
		if (listOfRAdsrelSpecie.size()> 0) {
			radsrelSpecie = listOfRAdsrelSpecie.get(0);
			adsrelSpecieDAO.deleteByCodiceAdsCAV(radsrelSpecie.getIdgeoPtAds());
			
			for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
				RAdsrelSpecie rAdsrelSpecieForSave = (RAdsrelSpecie) iterator.next();
				adsrelSpecieDAO.saveAdsrelSpecieCAV(rAdsrelSpecieForSave);
			}
			
			skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 4);
		}

		
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s) {
		return geoPtAreaDiSaggioDAO.findAreaDiSaggioByCodiceAdsListExcel(s);
	}

	@Override
	public PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(
			StringBuilder createWhereClauseForSearchCompleta, int page, int limit, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.findRelascopicaCompletaSort(createWhereClauseForSearchCompleta, page, limit,
				parameters);
	}

	@Override
	public AreaDiSaggio findADSByCodiceADS(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.findADSByCodiceADS(s, parameters);
	}

	@Override
	public PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,
			List<Object> parameters) {
	
		return geoPtAreaDiSaggioDAO.findAlberiCAV(s, page, limit,
				parameters);
	}
}
