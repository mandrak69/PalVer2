package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.PlainUtenteSoggetto;

@Path("/utenti")
@Produces({ "application/json" })
public interface PlainUtenteApi {
	
	@GET
	@Produces({ "application/json" })
	public Response searchUtente(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response createUtente(PlainUtenteSoggetto body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateUtente(PlainUtenteSoggetto body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
}
