package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.InterventoInsericiApi;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDatiTecniciDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class InterventoInsericiApiServisImpl extends SpringSupportedResource implements InterventoInsericiApi {

	@Autowired
	WrapperInterventoDAO wrapperInterventoDAO;

	@Autowired
	UserSessionDAO userSessionDAO;

	@Autowired
	TipoInterventoDatiTecniciDAO tipoInterventoDatiTecniciDAO;

	@Autowired
	IntervSelvicoulturaleDAO intervSelvicolturaleDAO;

	@Autowired
	GeoPlParticellaForestDAO geoPlParticellaForestDAO;

	@Override
	public Response insertDatiTehnici(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperInterventoDAO.saveSecondStep(intervDatiTehnici, userSessionDAO.getUser(httpRequest), idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response deleteIntervento(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			wrapperInterventoDAO.deleteIntervento(idIntervento);
			return Response.ok().entity("Intervento deleted successfully").build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDatiTehniciForStepTwo(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			InterventoDatiTehnici interventoDatiTehnici = new InterventoDatiTehnici();

			TipoInterventoDatiTecnici tipoIntervDatiTechici = tipoInterventoDatiTecniciDAO
					.findTipoIntervDatiTehnici(idIntervento);

			IntervSelvicolturale intervSelvicolturale = intervSelvicolturaleDAO
					.findInterventoSelvicolturale(idIntervento);

			interventoDatiTehnici.setTipoIntervento(tipoIntervDatiTechici);
			interventoDatiTehnici.setIntervSelvicolturale(intervSelvicolturale);

			return Response.ok(interventoDatiTehnici).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getForestParticlesForShooting(Integer idGeoPlParticellaForest, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(geoPlParticellaForestDAO.getForestParticleById(idGeoPlParticellaForest)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
