package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.TSoggetto;

@Path("/soggetti")
@Produces({ "application/json" })

public interface SoggettoApi {

	@GET
	@Path("/{idSoggetto}")
	@Produces({ "application/json" })
	public Response getSoggetto(@PathParam("idSoggetto") Integer idSoggetto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Produces({ "application/json" })
	public Response getSoggettoByPartialCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveSoggetto(TSoggetto body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response updateSoggetto(TSoggetto body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
	
	@GET
	@Path("/io")
	@Produces({ "application/json" })
	public Response getMyself(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo/pf")
	@Produces({ "application/json" })
	public Response getPersonaFisicaForSearch();
	
	@GET
	@Path("/bo/pg")
	@Produces({ "application/json" })
	public Response getPersonaGiurdicaForSearch();
	
	@GET
	@Path("/bo/prof")
	@Produces({ "application/json" })
	public Response getProfessionistaForSearch();
}
