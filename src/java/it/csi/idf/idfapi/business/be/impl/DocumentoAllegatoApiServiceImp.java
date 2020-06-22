package it.csi.idf.idfapi.business.be.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import it.csi.idf.idfapi.business.be.DocumentoAllegatoApi;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.service.FileGenerator;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexTrasformazioni;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;

public class DocumentoAllegatoApiServiceImp extends SpringSupportedResource implements DocumentoAllegatoApi {

	@Autowired
	private DocumentoAllegatoDAO documentoDao;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private FileGenerator fileGenerator;


	@Override
	public Response getDocumenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<DocumentoAllegato> documentoList = documentoDao.findAllDocumenti();

		return Response.ok(documentoList).build();
	}

	@Override
	public Response getDocumentoByID(Integer idDocumentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(documentoDao.findDocumentoByID(idDocumentoAllegato)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@Override
	public Response saveDocumento(DocumentoAllegato body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(documentoDao.createDocumentoAllegato(body)).build();
	}

	@Override
	public Response updateDocumento(DocumentoAllegato body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDocumentiByPfa(Integer idGeoPlPfa, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(documentoDao.findAllDocumentiByPfa(idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response downloadDocumento(int idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

		DocumentoAllegato documentoAllegato = documentoDao.findDocumentoByID(idDocumento);
		return Response.ok(documentoAllegato).build();
	}

	@Override
	public Response uploadDocumento(int idIntervento, int tipoDocumento, MultipartFormDataInput form,

			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		FatDocumentoAllegato docWithId = documentoDao.uploadDocumento(idIntervento, tipoDocumento, form,
				securityContext, httpHeaders, httpRequest);
		if (docWithId != null) {
			return Response.ok(docWithId).build();
		} else {
			return Response.serverError().build();
		}

	}

	@Override
	public Response deleteDocumentoById(Integer idDocumentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException {
		String uidDel = documentoDao.deleteDocumentoById(idDocumentoAllegato);
		if (uidDel != null) {
			return Response.ok(uidDel).build();

		} else {
			return Response.serverError().build();
		}

	}

	@Override
	public Response downloadPortalDocumento(Integer idDocumentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ServiceException {

		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		try {

			MetadatiIndexTrasformazioni metadatiIndexTrasf = new MetadatiIndexTrasformazioni();
			DocumentoAllegato documentoAllegato = documentoDao.findDocumentoByID(idDocumentoAllegato);
			metadatiIndexTrasf.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
			metadatiIndexTrasf.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAleggato()));
			Content contDownloaded = indexSrvHelper.downFile(documentoAllegato.getUidIndex(), metadatiIndexTrasf);

			if (contDownloaded == null) {

				return Response.serverError().build();
			} else {

				String path = "C:\\tmp\\myfile.jpeg";
				try (FileOutputStream stream = new FileOutputStream(path)) {
					stream.write(contDownloaded.getContent());
				}
				return Response.ok().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDichiarazione(Integer idIntervento, Integer tipoAllegato, HttpServletRequest httpRequest) {
		try {
			TipoAllegatoEnum tipoAllegatoEnum = null;
			for (TipoAllegatoEnum e : TipoAllegatoEnum.values()) {
				if (e.getValue() == tipoAllegato) {
					tipoAllegatoEnum = e;
				}
			}

			return BaseResponses.successResponse(fileGenerator.generateDichiarazione(tipoAllegatoEnum, idIntervento));
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
		}
	}

}