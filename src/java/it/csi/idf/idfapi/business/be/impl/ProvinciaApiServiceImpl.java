package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.ProvinciaApi;
import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ProvinciaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.GeoPLProvincia;
import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class ProvinciaApiServiceImpl extends SpringSupportedResource implements ProvinciaApi {
	
	@Autowired
	private ProvinciaDAO provinciaDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;
	
	@Autowired
	private ComuneDAO comuneDAO;

	@Override
	public Response getAllProvincia(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		List<GeoPLProvincia> provincia = provinciaDAO.findAllProvincia();
		
		return Response.ok(provincia).build();
	}
	
	@Override
	public Response getAllProvinciaSearch(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
			List<GeoPLProvinciaSearch> provincia = provinciaDAO.findAllSearchProvincia();
		
		return Response.ok(provincia).build();
	}

	@Override
	public Response getProvinciaByID(String istatProv, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		GeoPLProvincia provincia;
		
		try {
			provincia = provinciaDAO.findProvinciaByIstatProv(istatProv);
			return Response.ok(provincia).build();
			
		} catch (RecordNotFoundException e) {
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO + istatProv);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		}
		
	}

	@Override
	public Response saveProvincia(GeoPLProvincia body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {

			return Response.ok(provinciaDAO.createProvincia(body)).build();

		} catch (DuplicateRecordException e) {
			Error err = new Error();
			err.setCode(409);
			err.setErrorMessage("studente " + body.getIstatProv() + "  gia' presente in archivio");
			return Response.serverError().status(409).entity(err).build();
		}
	}

	@Override
	public Response getProvinciaForBackOffice(HttpServletRequest httpRequest) {
		try {
			UserInfo userInfo = userSessionDAO.getUser(httpRequest);
			TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc());
			ConfigUtente confUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());

			if (confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.UFFICIO_TERRITORIALE.getValue()
					|| confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.COMUNE.getValue()) {
				return Response.ok(provinciaDAO.findSearchProvinciaByIstatProv(
						comuneDAO.findComuneResourceByID(soggetto.getFkComune()).getIstatProv())).build();
			}
			return Response.ok(provinciaDAO.findAllSearchProvincia()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
