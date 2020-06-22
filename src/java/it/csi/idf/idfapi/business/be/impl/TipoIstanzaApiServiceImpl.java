package it.csi.idf.idfapi.business.be.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.TipoIstanzaApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoIstanzaDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoIstanzaApiServiceImpl extends SpringSupportedResource implements TipoIstanzaApi {

	@Autowired
	private TipoIstanzaDAO tipoIstanzaDAO;
	
	@Override
	public Response getTipoTrasformazione() {
		try {
			return Response.ok(tipoIstanzaDAO.getTrasformazioneTipo()).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}
}
