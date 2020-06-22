package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/delege")
@Produces({ "application/json" })
public interface DelegaApi {
	
	@GET
	@Path("/mieiDelegati")
	@Produces({ "application/json" })
	public Response getUtenteDelegati(@QueryParam("codiceFiscale") String codiceFiscale, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/mieiCodiciDelegati")
	@Produces({ "application/json" })
	public Response getUtenteDelegatiCodici(@Context HttpServletRequest httpRequest);
}
