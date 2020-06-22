package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import it.csi.idf.idfapi.dto.ExcelDTO;

@Path("/public/geoPlPfi")
@Produces({ "application/json" })
public interface PublicGeoPlPfaApi {
	
	@GET
	@Path("/search")
	@Produces({ "application/json" })
	public Response search(@Context UriInfo info, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/search/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getPfaSearchByID(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,
			@QueryParam("idComune") Integer idComune, @QueryParam("idPopolamento") Integer idPopolamento,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/searchExcel")
	@Produces("application/vnd.ms-excel")
	public Response generateExcel(ExcelDTO excel);	
}
