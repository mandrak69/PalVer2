package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.GovernoApi;
import it.csi.idf.idfapi.business.be.impl.dao.GovernoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class GovernoApiImpl extends SpringSupportedResource implements GovernoApi {
	
	@Autowired
	GovernoDAO governoDAO;

	@Override
	public Response getAllGoverni(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok(governoDAO.findAllGoverni()).build();
	}
}