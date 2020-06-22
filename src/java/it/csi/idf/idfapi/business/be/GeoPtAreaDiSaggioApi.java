package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.ExcelDTO;

@Path("/areaDiSaggio")
@Produces({ "application/json" })
public interface GeoPtAreaDiSaggioApi {
	
	@GET
	@Path("/{codiceAds}")
	@Produces({ "application/json" })
	public Response getNumberOfNextStep(@PathParam("codiceAds") String codiceAds, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search")
	@GET
	@Produces({ "application/json" })

	public Response search(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") Integer dettaglioAmbito [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/search")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response searchExcel(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/export/{codiceAds}")
	@GET
	@Produces("application/vnd.ms-excel")

	public Response exportAreaDiSaggionDetails(@PathParam("codiceAds") String codiceAds  , @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/excelDatiStazionali")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response excelDatiStazionali(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelAlberiCAMeDOM")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response excelAlberiCAMeDOM(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelAlberiCavallettati")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response excelAlberiCavallettati(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelRelascopicaSemplice/{codiceAds}")
	@GET
	@Produces("application/vnd.ms-excel")

	public Response excelRelascopicaSemplice(@PathParam("codiceAds") String codiceAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelRelascopicaCompleta/{codiceAds}")
	@GET
	@Produces("application/vnd.ms-excel")

	public Response excelRelascopicaCompleta(@PathParam("codiceAds") String codiceAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/search/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getAreaDiSaggioByCodiceAds(@PathParam("codiceAds") String codiceAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@Path("/searchADS/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getADSByCodiceAds(@QueryParam("step") boolean includeStep,  @PathParam("codiceAds") String codiceAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search/campione/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getAlberiCampioneDominante(@PathParam("codiceAds") String codiceAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@Path("/search/alberiCAV/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getAlberiCAV(@PathParam("codiceAds") String codiceAds,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search/relascopicaSemplice/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getRelascopicaQueryForSearch(@PathParam("codiceAds") String codiceAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search/relascopicaCompleta/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getRelascopicaCompletaSort(@PathParam("codiceAds") String codiceAds,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@Path("/search/relascopicaCompletaCAV/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getRelascopicaCompletaSortCAV(@PathParam("codiceAds") String codiceAds,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(AreaDiSaggioDTO areaDiSaggio,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/datiStazionali1/create")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertSuperficieDati1(AreaDiSaggio areaDiSaggio, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/datiStazionali1/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getSuperficieDati1(@PathParam("codiceAds") String codiceAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/datiStazionali2/{codiceAds}")
	@GET
	@Produces({ "application/json" })
	public Response getSuperficieDati2(@PathParam("codiceAds") String codiceAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);


	@Path("/datiStazionali2/create")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertSuperficieDati2(AreaDiSaggio areaDiSaggio, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
}
