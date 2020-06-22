package it.csi.idf.idfapi.business.be.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.GeoPlPfaApi;
import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlPfaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPlPfa;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class GeoPlPfaApiServiceImpl extends SpringSupportedResource implements GeoPlPfaApi {

	static final Logger logger = Logger.getLogger(GeoPlPfaApiServiceImpl.class);

	@Autowired
	private GeoPlPfaDAO geoPlPfaDAO;

	@Autowired
	private WrapperPlPfaDAO wrapperPlPfaDAO;

	@Override
	public Response search(@Context UriInfo info, HttpServletRequest httpRequest) {

		try {
			Map<String, String> queryParams = new HashMap<>();

			queryParams.put("istatProv", info.getQueryParameters().getFirst("istatProv"));
			queryParams.put("idComune", info.getQueryParameters().getFirst("idComune"));
			queryParams.put("denominazione", info.getQueryParameters().getFirst("denominazione"));
			queryParams.put("fromDate", info.getQueryParameters().getFirst("fromDate"));
			queryParams.put("toDate", info.getQueryParameters().getFirst("toDate"));

			queryParams.put("page", info.getQueryParameters().getFirst("page"));
			queryParams.put("limit", info.getQueryParameters().getFirst("limit"));
			queryParams.put("sort", info.getQueryParameters().getFirst("sort"));

			return Response.ok(wrapperPlPfaDAO.getPublicPianiForestaliSearch(queryParams)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPfaSearchByID(Integer idGeoPlPfa, HttpServletRequest httpRequest) {

		try {
			return Response.ok(wrapperPlPfaDAO.getPublicPfaSearchByID(idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getAllGeoPlPfa() {
		return Response.ok(geoPlPfaDAO.findAllGeoPlPfa()).build();
	}

	@Override
	public Response getGeoPlPfaByID(Integer idGeoPlPfa, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		logger.info("into getGeoPlPfaByID");

		try {
			return Response.ok(geoPlPfaDAO.findGeoPlPfaById(idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveGeoPlPfa(GeoPlPfa body) throws DuplicateRecordException {

		try {
			return Response.ok(geoPlPfaDAO.createGeoPlPfa(body)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response generateExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			ResponseBuilder response = Response.ok(wrapperPlPfaDAO.generateExcel(excel));

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "PFA_Search_Result_" + formatDateTime;

			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}