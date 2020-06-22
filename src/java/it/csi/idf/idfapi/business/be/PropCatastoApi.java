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

import it.csi.idf.idfapi.dto.PropCatasto;



@Path("/catasti")
@Produces({"application/json"})
public interface PropCatastoApi {
	
	@GET
	@Produces({"application/json"})
	public Response getCatasti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idCatasto}")
	@Produces({"application/json"})
	public Response getCatastiByID(@PathParam("idCatasto") Integer idGeoPlPropCatasto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveCatasti(PropCatasto body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Path("/sezione")
	@Produces({"application/json"})
	public Response getSezione(@QueryParam("comune") Integer comune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/foglio")
	@Produces({"application/json"})
	public Response getFoglio(@QueryParam("comune") Integer comune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/particella")
	@Produces({"application/json"})
	public Response getParticella(@QueryParam("comune") Integer comune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
}
