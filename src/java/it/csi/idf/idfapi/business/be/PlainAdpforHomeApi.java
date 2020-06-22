package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.PlainAdpforHome;

@Path("/idfistafor")
@Produces({ "application/json" })
public interface PlainAdpforHomeApi {

	@GET
	@Produces({ "application/json" })
	public Response getPlainHome(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateFromPlainHome(PlainAdpforHome body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo")
	@Produces({ "application/json" })
	public Response getBackOfficeHome(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/home")
	@Produces({ "application/json" })
	public Response getHomeData(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
