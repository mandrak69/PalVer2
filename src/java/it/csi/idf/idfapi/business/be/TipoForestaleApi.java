package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/tipoForestale")

@Produces({ "application/json" })
public interface TipoForestaleApi {
	@GET  
	@Produces({ "application/json" })

	 public Response getAllTipoForestale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/categoria/{categoriaForestale}")
	@Produces({"application/json"})
	public Response getTipoByCategoria(@PathParam("categoriaForestale") Integer categoriaForestale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
}
