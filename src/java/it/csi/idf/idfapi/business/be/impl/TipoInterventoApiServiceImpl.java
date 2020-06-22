package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.TipoInterventoApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoInterventoApiServiceImpl extends SpringSupportedResource implements TipoInterventoApi {
	
	@Autowired
	TipoInterventoDAO tipoInterventoDAO;

	@Override
	public Response getAllTipoIntervento(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.findAllTipoIntervento()).build();
	}
}