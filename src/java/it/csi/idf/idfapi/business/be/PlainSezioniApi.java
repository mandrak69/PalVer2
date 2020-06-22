package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.PlainParticelleCatastals;

@Path("/sezioni")
@Produces({ "application/json" })
public interface PlainSezioniApi {

	@POST
	@Path("/secondo/elencoParticelle")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	Response returnRicadenze(PlainParticelleCatastals body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/secondo/particella")
	@Produces({ "application/json" })
	Response getParticellaCatastale(@QueryParam("istatComune") String istatComune, @QueryParam("sezione") String sezione,
			@QueryParam("foglio") Integer foglio, @QueryParam("particella") String particella,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/prima/legale")
	@Produces({ "application/json" })
	Response getSocietaLegale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/sesto/istanzaTaglio")
	@Produces({ "application/json" })
	Response getIstanzaTaglio(@QueryParam("numIstanza") Integer numIstanza, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
}