package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.AdsrelSpecieApi;
import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdsDAO;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class AdsrelSpecieServiceImpl extends SpringSupportedResource implements AdsrelSpecieApi {

	static final Logger logger = Logger.getLogger(AdsrelSpecieServiceImpl.class);

	@Autowired
	AdsrelSpecieDAO adsrelSpecieDao;

	@Autowired
	WrapperAdsDAO wrapperAdsDAO;

	@Override
	public Response getAllSpecie(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			List<RAdsrelSpecie> specie = adsrelSpecieDao.search();
			return Response.ok(specie).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}
	}

	@Override
	public Response insertAlberiCAMDOM(List<RAdsrelSpecie> radsrelSpecie, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			wrapperAdsDAO.insertAllSpecieDomeCAM(radsrelSpecie);

		} catch (Exception e) {
			logger.info(e.getMessage());
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	@Override
	public Response updateAlberiCAMeDOM(List<RAdsrelSpecie> radsrelSpecieList, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperAdsDAO.updateAllSpecieDomeCAM(radsrelSpecieList);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@Override
	public Response saveAlberiCAV(List<RAdsrelSpecie> alberiCAVList, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			
			wrapperAdsDAO.saveAdsrelSpecieCAV(alberiCAVList);
			return Response.ok().build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			if (e.getCause()!= null) {
				logger.error(e.getCause().getMessage());
			}
			return Response.serverError().build();
		}

	}

	@Override
	public Response getAllSpecieByCodiceADS(String codiceAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			List<RAdsrelSpecie> specie = adsrelSpecieDao.searchByCodiceADS(codiceAds);
			return Response.ok(specie).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}
	}

	public Response getAllSpecieCAVByCodiceADS(@PathParam("codiceAds") String codiceAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		try {
			List<RAdsrelSpecie> specie = adsrelSpecieDao.searchAdsRelSpecByCodiceADSForCAV(codiceAds);
			return Response.ok(specie).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}

	}

}
