package it.csi.idf.idfapi.business.be.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PublicDocumentoAllegatoApi;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PublicDocumentoAllegatoApiServiceImpl extends SpringSupportedResource implements PublicDocumentoAllegatoApi {

	@Autowired
	private DocumentoAllegatoDAO documentoDAO;
	
	@Override
	public Response getDocumentiByGeoPlPfa(int geoPlPfa) {
		try {
			return Response.ok(documentoDAO.findAllDocumentiByPfa(geoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	
	@Override
	public Response downloadDocumento(int idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		DocumentoAllegato documentoAllegato = documentoDAO.findDocumentoByID(idDocumento);
		
        httpResponse.addHeader("Content-Disposition", "attachment; filename=" + documentoAllegato.getNomeAllegato());
		return Response.ok().build();
	}
}