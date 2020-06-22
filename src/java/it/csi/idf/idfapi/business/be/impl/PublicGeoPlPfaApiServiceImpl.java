package it.csi.idf.idfapi.business.be.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PublicGeoPlPfaApi;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PublicGeoPlPfaApiServiceImpl  extends SpringSupportedResource implements PublicGeoPlPfaApi {
	
	@Autowired
	private WrapperPlPfaDAO wrapperPlPfaDAO;

	@Override
	public Response search(UriInfo info, HttpServletRequest httpRequest) {

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
	public Response getPfaSearchByID(Integer idGeoPlPfa, Integer idComune, Integer idPopolamento,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperPlPfaDAO.getPublicPfaSearchByID(idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response generateExcel(ExcelDTO excel) {
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