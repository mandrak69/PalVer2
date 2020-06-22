package it.csi.idf.idfapi.business.be;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import it.csi.idf.idfapi.dto.StatoIntervento;

@Path("/statiInterventi")
@Produces({ "application/json" })
public interface StatoInterventoApi {
	
	@GET
	@Produces({ "application/json" })
	List<StatoIntervento> getStatoList();
}