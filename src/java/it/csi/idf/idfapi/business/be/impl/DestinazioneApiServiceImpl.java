package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.DestinazioneApi;
import it.csi.idf.idfapi.business.be.impl.dao.DestinazioneDAO;
import it.csi.idf.idfapi.dto.Destinazione;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class DestinazioneApiServiceImpl extends SpringSupportedResource implements DestinazioneApi {
	
	@Autowired
	DestinazioneDAO destinazioneDAO;

	@Override
	public Response getAllDestinazione(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Destinazione> destinazione = destinazioneDAO.findAllDestinazione();
		return Response.ok(destinazione).build();
	}

}
