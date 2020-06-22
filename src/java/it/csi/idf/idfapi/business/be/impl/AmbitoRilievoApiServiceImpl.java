package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.AmbitoRilievoApi;
import it.csi.idf.idfapi.business.be.impl.dao.AmbitoRilievoDAO;
import it.csi.idf.idfapi.dto.AmbitoRilievo;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class AmbitoRilievoApiServiceImpl extends SpringSupportedResource implements AmbitoRilievoApi {
	
	@Autowired
	AmbitoRilievoDAO ambitoRilievoDAO;

	

	@Override
	public Response getAllAmbitoRilievo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<AmbitoRilievo> ambito = ambitoRilievoDAO.findAllAmbitoRilievo();
		return Response.ok(ambito).build();
	}

}
