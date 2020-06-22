package it.csi.idf.idfapi.business.be.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PropCatastoApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PropCatastoServiceApiImpl extends SpringSupportedResource implements PropCatastoApi{

	@Autowired
	private PropCatastoDAO propCatastoDao;
	

	@Override
	public Response getCatasti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		List<PropCatasto> listCatasti = propCatastoDao.findAllCatasti();
		return Response.ok(listCatasti).build();
	}

	@Override
	public Response getCatastiByID(Integer idGeoPlPropCatasto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			PropCatasto catasto = propCatastoDao.findCatastoByID(idGeoPlPropCatasto);
			return Response.ok(catasto).build();
		}catch(RecordNotFoundException e) {
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO + idGeoPlPropCatasto);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		}
	}

	@Override
	public Response saveCatasti(PropCatasto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
			return Response.ok(propCatastoDao.insertPropCatasto(body)).build();
	}

	@Override
	public Response getSezione(Integer comune, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findSezioniByComune(comune)).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getFoglio(Integer comune, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findFogliByComune(comune)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getParticella(Integer comune, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findParticelleByComune(comune)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
