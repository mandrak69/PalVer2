package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.Comune;

@Path("/comuni")
@Produces({"application/json"})
public interface ComuneApi {
	
	@GET
	@Produces({"application/json"})
	public Response getAllComune();
	
	@GET
	@Path("/{idComune}")
	@Produces({"application/json"})
	public Response getComuneByID(@PathParam("idComune") Integer idComune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);

	@GET
	@Path("/provincia/{istatProv}")
	@Produces({"application/json"})
	public Response getComuniByProvincia(@PathParam("istatProv") String istatProv, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response saveComune(Comune body);
	
	@GET
	@Path("/bo/provincia/{istatProv}")
	@Produces({"application/json"})
	public Response getComuniByProvinciaForBackOfficeSearch(@PathParam("istatProv") String istatProv,  @Context HttpServletRequest httpRequest);
}
