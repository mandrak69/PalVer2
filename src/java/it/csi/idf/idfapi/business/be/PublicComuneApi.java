package it.csi.idf.idfapi.business.be;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("public/comuni")
@Produces({"application/json"})
public interface PublicComuneApi {
	
	@GET
	@Path("/provincia/{istatProv}")
	@Produces({"application/json"})
	public Response getComuniByProvincia(@PathParam("istatProv") String istatProv);
}