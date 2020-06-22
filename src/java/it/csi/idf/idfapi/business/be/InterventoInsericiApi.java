package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.InterventoDatiTehnici;

@Path("/intervento")
@Produces({ "application/json" })
public interface InterventoInsericiApi {

	@GET
	@Path("/{idIntervento}/datiTehnici")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response getDatiTehniciForStepTwo(@PathParam("idIntervento") Integer idIntervento, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/datiTehnici")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response getForestParticlesForShooting(@QueryParam("idGeoPlForest") Integer idGeoPlParticellaForest, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	
	
	@Path("/{idIntervento}/datiTehnici")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertDatiTehnici(@PathParam("idIntervento") Integer idIntervento, 
			InterventoDatiTehnici intervDatiTehnici, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/{idIntervento}")
	@DELETE
	@Produces({ "application/json" })
	public Response deleteIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}
