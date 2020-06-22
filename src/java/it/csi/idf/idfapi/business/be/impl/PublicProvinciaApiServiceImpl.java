package it.csi.idf.idfapi.business.be.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PublicProvinciaApi;
import it.csi.idf.idfapi.business.be.impl.dao.ProvinciaDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PublicProvinciaApiServiceImpl  extends SpringSupportedResource implements PublicProvinciaApi {
	
	@Autowired
	private ProvinciaDAO provinciaDAO;
	
	@Override
	public Response getAllProvinciaSearch() {
		try {
			return Response.ok(provinciaDAO.findAllSearchProvincia()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}