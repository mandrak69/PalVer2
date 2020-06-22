package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PrioritaApi;
import it.csi.idf.idfapi.business.be.impl.dao.PrioritaDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PrioritaApiServiceImpl extends SpringSupportedResource implements PrioritaApi {

	@Autowired
	PrioritaDAO prioritaDAO;
	
	@Override
	public Response getAllPriorita(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok(prioritaDAO.findAllPriorita()).build();
	}

}
