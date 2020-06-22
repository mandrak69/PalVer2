package it.csi.idf.idfapi.business.be.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.IstanzaForestAPI;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PlainSoggettoIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.dto.CompensationForesteDTO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainPrimaSezione;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.PlainSestoSezione;
import it.csi.idf.idfapi.dto.PlainTerzaSezione;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class IstanzaForestAPIServiceImpl extends SpringSupportedResource implements IstanzaForestAPI {

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;

	@Autowired
	private PlainSoggettoIstanzaDAO plainSoggettoIstanzaDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Override
	public Response getNumberOfNextStep(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getNumberOfNextStep(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIstanzeForConfigUtente(int page, int limit, String sort, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			UserInfo user = userSessionDAO.getUser(httpRequest);
			TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
			ConfigUtente configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());

			return Response.ok(plainSoggettoIstanzaDAO.getAllIstances(configUtente.getIdConfigUtente(),
					configUtente.getFkTipoAccreditamento(), page, limit, sort)).build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getPrimaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getPrimaSezione(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response savePrimaSezione(PlainPrimaSezione body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.savePrimaSezione(body, userSessionDAO.getUser(httpRequest))).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSecondoSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getSecondoSezione(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSecondoSezione(Integer idIntervento, PlainSecondoSezione body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.saveSecondoSezione(body, userSessionDAO.getUser(httpRequest), idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateSecondoSezione(Integer idIntervento, PlainSecondoSezione body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.updateSecondoSezione(body, userSessionDAO.getUser(httpRequest), idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response updatePrimaSezione(Integer idIntervento, PlainPrimaSezione body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.updatePrimaSezione(body, userSessionDAO.getUser(httpRequest), idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTerzaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getTerzaSezione(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveTerzaSezione(Integer idIntervento, PlainTerzaSezione body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response
					.ok(wrapperIstanzaDAO.saveTerzaSezione(idIntervento, userSessionDAO.getUser(httpRequest), body))
					.build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getQuartaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getQuartaSezione(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveQuartaSezione(Integer idIntervento, CompensationForesteDTO compensationForesteDTO,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.saveQuartaSezione(idIntervento, compensationForesteDTO);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getQuintaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getQuintaSezione(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveQuintaSezione(Integer idIntervento, TSoggetto soggetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.saveQuintaSezione(idIntervento, soggetto);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSestoSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getSestoSezione(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSestoSezione(Integer idIntervento, PlainSestoSezione plainSestoSezione,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.saveSestoSezione(idIntervento, userSessionDAO.getUser(httpRequest).getCodFisc(),
					plainSestoSezione);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response backOfficeSearch(UriInfo info, HttpServletRequest httpRequest) {
		try {
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("tipoIstanza", info.getQueryParameters().getFirst("tipoIstanza"));
			queryParams.put("numIstanza", info.getQueryParameters().getFirst("numIstanza"));
			queryParams.put("statoIstanza", info.getQueryParameters().getFirst("statoIstanza"));
			queryParams.put("annoIstanza", info.getQueryParameters().getFirst("annoIstanza"));
			queryParams.put("dataPresDa", info.getQueryParameters().getFirst("dataPresDa"));
			queryParams.put("dataPresA", info.getQueryParameters().getFirst("dataPresA"));
			queryParams.put("prov", info.getQueryParameters().getFirst("prov"));
			queryParams.put("comune", info.getQueryParameters().getFirst("comune"));
			queryParams.put("sezione", info.getQueryParameters().getFirst("sezione"));
			queryParams.put("foglio", info.getQueryParameters().getFirst("foglio"));
			queryParams.put("particella", info.getQueryParameters().getFirst("particella"));
			queryParams.put("pf", info.getQueryParameters().getFirst("pf"));
			queryParams.put("pg", info.getQueryParameters().getFirst("pg"));
			queryParams.put("prof", info.getQueryParameters().getFirst("prof"));
			queryParams.put("aapp", info.getQueryParameters().getFirst("aapp"));
			queryParams.put("rn2k", info.getQueryParameters().getFirst("rn2k"));
			queryParams.put("popSeme", info.getQueryParameters().getFirst("popSeme"));
			queryParams.put("vincIdro", info.getQueryParameters().getFirst("vincIdro"));
			queryParams.put("compNec", info.getQueryParameters().getFirst("compNec"));
			queryParams.put("compO1", info.getQueryParameters().getFirst("compO1"));
			queryParams.put("compO2", info.getQueryParameters().getFirst("compO2"));
			queryParams.put("compO3", info.getQueryParameters().getFirst("compO3"));
			queryParams.put("compO4", info.getQueryParameters().getFirst("compO4"));
			queryParams.put("compO5", info.getQueryParameters().getFirst("compO5"));
			queryParams.put("govForm", info.getQueryParameters().getFirst("govForm"));
			queryParams.put("catFor", info.getQueryParameters().getFirst("catFor"));
			queryParams.put("ubicazione", info.getQueryParameters().getFirst("ubicazione"));
			queryParams.put("tipTrasf", info.getQueryParameters().getFirst("tipTrasf"));
			queryParams.put("calcEconDa", info.getQueryParameters().getFirst("calcEconDa"));
			queryParams.put("calcEconA", info.getQueryParameters().getFirst("calcEconA"));
			queryParams.put("page", info.getQueryParameters().getFirst("page"));
			queryParams.put("limit", info.getQueryParameters().getFirst("limit"));
			queryParams.put("sort", info.getQueryParameters().getFirst("sort"));

			return Response.ok(
					wrapperIstanzaDAO.backOfficeSearch(queryParams, userSessionDAO.getUser(httpRequest).getCodFisc()))
					.build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTuttiElencaForIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getTuttiElencaForIntervento(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDataInvioIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getDataInvioIstanza(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.invioIstanza(idIntervento, body,
					userSessionDAO.getSoggetoFromUser(userSessionDAO.getUser(httpRequest)));
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaVerificata(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.updateIstanzaVerificata(idIntervento,
					userSessionDAO.getSoggetoFromUser(userSessionDAO.getUser(httpRequest)));
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaRifiutata(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.updateIstanzaRifiutata(idIntervento,
					userSessionDAO.getSoggetoFromUser(userSessionDAO.getUser(httpRequest)));
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtenteForIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getUtenteForIstanza(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getCeoIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		
		try {
			return Response.ok(wrapperIstanzaDAO.getCeoIstanza(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {

			wrapperIstanzaDAO.associareDocumenti(idIntervento, body,
					userSessionDAO.getSoggetoFromUser(userSessionDAO.getUser(httpRequest)));
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
