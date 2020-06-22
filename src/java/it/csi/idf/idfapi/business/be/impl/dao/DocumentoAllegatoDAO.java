package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;

public interface DocumentoAllegatoDAO {

	List<DocumentoAllegato> findAllDocumenti();
	
	DocumentoAllegato findDocumentoByID(Integer idDocumentoAllegato);

	List<DocumentoAllegato> findAllDocumentiByPfa(Integer idGeoPlPfa);
	
	int createDocumentoAllegato(DocumentoAllegato documento);
	
	int createDocumentoAllegato(FatDocumentoAllegato documento);
	
	List<DocumentoAllegato> findDocumentiByIdIntervento(Integer idIntervento);

	void deleteDocumentoAllegatoByID(int idDocumentoAllegato);
	
	List<FatDocumentoAllegato> findDocumentiByIdAndTipos(Integer idIntervento, List<TipoAllegatoEnum> tipoAllegati);

	FatDocumentoAllegato uploadDocumento(int idIntervento, int tipoDocumento, MultipartFormDataInput form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);
	
	String deleteDocumentoById(Integer idDocumentoAllegato);
}