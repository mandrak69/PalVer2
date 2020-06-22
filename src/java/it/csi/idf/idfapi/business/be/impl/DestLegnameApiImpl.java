package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.DestLegnameApi;
import it.csi.idf.idfapi.business.be.impl.dao.DestLegnameDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class DestLegnameApiImpl extends SpringSupportedResource implements DestLegnameApi{
	
	@Autowired
	DestLegnameDAO destLegname;

	@Override
	public Response getDestLegnami(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return Response.ok(destLegname.findAllDestLegname()).build();
	}
}