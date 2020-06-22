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

import it.csi.idf.idfapi.dto.SpeciePfaIntervento;

@Path("/specie")

@Produces({ "application/json" })

public interface SpecieApi {

	@GET
	@Produces({ "application/json" })

	public Response getAllSpecie(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	
	@POST
	@Path("/{idIntervento}/specieIntervento")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveSpeciePfaIntervento(SpeciePfaIntervento specieInterv, @PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
    

	@Path("/{idSpecie}")
	@DELETE
	@Produces({ "application/json" })
	public Response deleteSpeciePfaIntervento(@PathParam("idSpecie") Integer idSpecie, @QueryParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@Path("/gruppo")
	@GET
	@Produces({ "application/json" })
	public Response findAllGruppoSpecie(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
}
