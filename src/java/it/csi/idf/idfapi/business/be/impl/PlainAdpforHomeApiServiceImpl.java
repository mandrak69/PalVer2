package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PlainAdpforHomeApi;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PlainAdpforHomeApiServiceImpl extends SpringSupportedResource implements PlainAdpforHomeApi {

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Override
	public Response getPlainHome(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			return Response.ok(wrapperAdpforHomeDAO.getAdpforHome(userSessionDAO.getUser(httpRequest))).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateFromPlainHome(PlainAdpforHome body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			wrapperAdpforHomeDAO.updateAdpforHome(userSessionDAO.getUser(httpRequest), body);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getBackOfficeHome(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperAdpforHomeDAO.getAdpforBackOfficeHome(userSessionDAO.getUser(httpRequest)))
					.build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getHomeData(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(wrapperAdpforHomeDAO.getHomeData(userSessionDAO.getUser(httpRequest))).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
