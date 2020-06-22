package it.csi.idf.idfapi.business.be.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.DelegaApi;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class DelegaApiServiceImpl extends SpringSupportedResource implements DelegaApi {
	
	@Autowired
	private DelegaDAO delegaDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private SoggettoDAO soggettoDAO;

	@Override
	public Response getUtenteDelegati(String codiceFiscale, HttpServletRequest httpRequest) {
		try {
			boolean alreadyInDB = false;
			List<Delega> delege = delegaDAO.getMieiDelegati(userSessionDAO.getSoggetoFromUser(userSessionDAO.getUser(httpRequest)).getFkConfigUtente());
			for(Delega delega : delege) {
				if(codiceFiscale.equals(delega.getCfDelegante()) ) {
					alreadyInDB = true;
				}
			}

			if(!alreadyInDB) {
				throw new ValidationException("Questo codice non e specificato come delegato");
			} 
			return Response.ok(soggettoDAO.findFatSoggettoByCodiceFiscale(codiceFiscale)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtenteDelegatiCodici(HttpServletRequest httpRequest) {
		try {
			List<Delega> delege = delegaDAO.getMieiDelegati(userSessionDAO.getSoggetoFromUser(userSessionDAO.getUser(httpRequest)).getFkConfigUtente());
			List<String> codici = new ArrayList<>();
			for(Delega delega : delege) {
				codici.add(delega.getCfDelegante());
			}
			return Response.ok(codici).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
