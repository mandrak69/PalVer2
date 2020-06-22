package it.csi.idf.idfapi.business.be.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PublicComuneApi;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PublicComuneApiServiceImpl extends SpringSupportedResource implements PublicComuneApi {

	@Autowired
	private ComuneDAO comuneDAO;

	@Override
	public Response getComuniByProvincia(String istatProv) {
		try {
			return Response.ok(comuneDAO.findAllComuneByProvincia(istatProv)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}