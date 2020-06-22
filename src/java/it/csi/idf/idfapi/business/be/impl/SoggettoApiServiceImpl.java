package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.SoggettoApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.ValidationUtil;

public class SoggettoApiServiceImpl extends SpringSupportedResource implements SoggettoApi {

	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public Response getSoggetto(@PathParam("idSoggetto") Integer idSoggetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		TSoggetto soggetto;
		try {
			soggetto = soggettoDAO.findSoggettoById(idSoggetto);
			return Response.ok(soggetto).build();
		} catch (RecordNotFoundException e) {
			return BaseResponses.itemNotFound();
		}
	}

	@Override
	public Response updateSoggetto(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			if (validationObligatory(body)) {
				return BaseResponses.requiredField();
			}
			
			if (validateRequiredFields(body) != null) {
				return validateRequiredFields(body);
			}
			return Response.ok(soggettoDAO.updateSoggetto(body)).build();

		} catch (Exception e) {
			return Response.serverError().build();
		} 
	}

	@Override
	public Response saveSoggetto(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			if (validationObligatory(body)) {
				return BaseResponses.requiredField();
			}

			if (validateRequiredFields(body) != null)
				return validateRequiredFields(body);
			return Response.ok(soggettoDAO.createSoggetto(body)).build();
		} catch (Exception e) {
			return Response.serverError().build();

		} 
	}

	@Override
	public Response getSoggettoByPartialCodiceFiscale(String codiceFiscale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		if(codiceFiscale == null || codiceFiscale.length() > 16) {
			return BaseResponses.badInputParameters();
		}
		return Response.ok(soggettoDAO.findByPartialCodiceFiscale(codiceFiscale)).build();
	}

	@Override
	public Response getMyself(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(soggettoDAO.findFatSoggettoByCodiceFiscale(userSessionDAO.getUser(httpRequest).getCodFisc())).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getPersonaFisicaForSearch() {
		try {
			return Response.ok(soggettoDAO.getPersFisicaForBOSearch()).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getPersonaGiurdicaForSearch() {
		try {
			return Response.ok(soggettoDAO.getPersGiuridicaForBOSearch()).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getProfessionistaForSearch() {
		try {
			return Response.ok(soggettoDAO.getProfessForBOSearch()).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	private boolean validationObligatory(TSoggetto input) {
		return input.getNrTelefonico() == null || input.geteMail() == null || input.getCodiceFiscale() == null
				|| input.getNome() == null || input.getCognome() == null;
	}

	private Response validateRequiredFields(TSoggetto input) {
		if (input.getNrTelefonico().length() < 7 || input.getNrTelefonico().length() > 20) {
			return BaseResponses.invalidLength();
		}

		if (!ValidationUtil.isEMail(input.geteMail())) {
			return BaseResponses.invalidEmail();
		}
		return null;
	}
}
