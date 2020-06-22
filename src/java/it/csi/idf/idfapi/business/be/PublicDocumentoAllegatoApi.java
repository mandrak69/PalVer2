package it.csi.idf.idfapi.business.be;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("public/documenti")
@Produces({ "application/json" })
public interface PublicDocumentoAllegatoApi {
	
	@GET
	@Path("/pfa/{idGeoPlPfa}")
	@Produces({"application/json"})
	public Response getDocumentiByGeoPlPfa(@PathParam("idGeoPlPfa") int geoPlPfa);
	
	@GET
	@Path("/download")
	public Response downloadDocumento(@QueryParam("idDocumento") int idDocumento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;
	
}