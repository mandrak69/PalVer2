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

import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;

@Path("/interventi")
@Produces({"application/json"})
public interface InterventoApi {
	
	@GET
	@Produces({"application/json"})
	public Response getInterventi(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/getStepNumber/{idIntervento}")
	@Produces({ "application/json" })
	public Response getNumberOfNextStep(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}")
	@Produces({"application/json"})
	public Response getInterventoByID(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveIntervento(TipoInterventoDatiTecnici body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
    
	@PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateIntervento(Intervento body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@POST
	@Path("/interventiExcel")
	@Produces("application/vnd.ms-excel")
	public Response generateExcel(ExcelDTO excel,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getInterventiPianiByPlPfa(@PathParam("idGeoPlPfa") int idGeoPlPfa, @QueryParam("page") int page,
			@QueryParam("limit") int limit, @QueryParam("sort") String sort, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/pfa/{idGeoPlPfa}/prima")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response savePrimaPfaIntervento(PfaLottoLocalizza body, @PathParam("idGeoPlPfa") int idGeoPlPfa, @Context HttpServletRequest httpRequest);
}
