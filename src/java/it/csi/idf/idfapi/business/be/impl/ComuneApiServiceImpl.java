package it.csi.idf.idfapi.business.be.impl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.ComuneApi;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class ComuneApiServiceImpl extends SpringSupportedResource implements ComuneApi {
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	ComuneDAO comuneDao = null;

	public ComuneDAO getComuneDao() {
		return comuneDao;
	}

	@Autowired
	public void setComuneDao(ComuneDAO comuneDao) {
		this.comuneDao = comuneDao;
	}

	@Override
	public Response getAllComune() {
		List<ComuneResource> com = getComuneDao().findAllComune();
		return Response.ok(com).build();
	}

	@Override
	public Response getComuneByID(Integer idComune, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(getComuneDao().findComuneResourceByID(idComune)).build();		
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getComuniByProvincia(String istatProv, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(getComuneDao().findAllComuneByProvincia(istatProv)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
    public Response saveComune(Comune body) {
		try {
			return Response.ok(getComuneDao().createComune(body)).build();			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getComuniByProvinciaForBackOfficeSearch(String istatProv, HttpServletRequest httpRequest) {
		try {
			UserInfo userInfo = userSessionDAO.getUser(httpRequest);
			TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc());
			ConfigUtente confUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());

			if (confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.COMUNE.getValue()) {
				return Response.ok(Collections.singletonList(comuneDao.findComuneResourceByID(soggetto.getFkComune()))).build();
			}
			return Response.ok(getComuneDao().findAllComuneByProvincia(istatProv)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
