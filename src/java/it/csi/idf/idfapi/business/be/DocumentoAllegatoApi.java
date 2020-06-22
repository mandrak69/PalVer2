package it.csi.idf.idfapi.business.be;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.dto.DocumentoAllegato;


@Path("/documenti")
@Produces({ "application/json" })
public interface DocumentoAllegatoApi {

	@GET
	@Produces({ "application/json" })
	public Response getDocumenti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idDocumentoAllegato}")
	@Produces({ "application/json" })
	public Response getDocumentoByID(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/{idGeoPlPfa}")
	@Produces({"application/json"})
	public Response getDocumentiByPfa(@PathParam("idGeoPlPfa") Integer idGeoPlPfa, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveDocumento(DocumentoAllegato body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateDocumento(DocumentoAllegato body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/download")
	public Response downloadDocumento(@QueryParam("idDocumento") int idDocumento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	public Response uploadDocumento(@QueryParam("intervento") int idIntervento, @QueryParam("tipo") int tipo,
			@MultipartForm MultipartFormDataInput form, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
		
	@GET
	@Path("/getDoc/{idDocumentoAllegato}")
	public Response downloadPortalDocumento(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,	
	@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
	@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws  ServiceException;
	
	@DELETE
	@Path("/delete/{idDocumentoAllegato}")
	public Response deleteDocumentoById(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,
	@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
	@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;
	
	
	@GET
	@Path("/dichiarazione/{idIntervento}/{tipoAllegato}")
	@Produces({ "application/json" })
	Response getDichiarazione(@PathParam("idIntervento") Integer idIntervento,@PathParam("tipoAllegato") Integer tipoAllegato,  HttpServletRequest httpRequest);

	
}
