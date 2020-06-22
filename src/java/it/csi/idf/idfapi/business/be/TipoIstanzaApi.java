package it.csi.idf.idfapi.business.be;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/tipiIstanza")
@Produces({ "application/json" })
public interface TipoIstanzaApi {
	
	@GET
	@Path("/trasf")
	@Produces({ "application/json" })
	public Response getTipoTrasformazione();
}
