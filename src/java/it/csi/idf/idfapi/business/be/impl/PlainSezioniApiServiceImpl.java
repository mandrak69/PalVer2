package it.csi.idf.idfapi.business.be.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.opengis.geometry.Geometry;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PlainSezioniApi;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.service.AAEP;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.PRIMPA;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainParticelleCatastals;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PlainSezioniApiServiceImpl extends SpringSupportedResource implements PlainSezioniApi {

	@Autowired
	private AAEP aaep;

	@Autowired
	private SIGMATER sigmater;

	@Autowired
	private GSAREPORT gsaReport;

	@Autowired
	private ComuneDAO comuneDao;

	@Autowired
	private RicadenzaService ricadenzaService;

	@Autowired
	private PRIMPA primpa;

	@Override
	public Response returnRicadenze(PlainParticelleCatastals body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {

		PlainSecondoSezione secondSezione = new PlainSecondoSezione();
		if (!body.getParticelleCatastali().isEmpty()) {
			List<Geometry> geometries = new ArrayList<>();
			BigDecimal totalSurface = BigDecimal.ZERO;

			for (PlainParticelleCatastali particella : body.getParticelleCatastali()) {
				geometries.add(sigmater.getGeometryFromParticelleCatastali(particella));
				totalSurface = totalSurface.add(particella.getSupCatastale());
			}

			Geometry mergedGeometry = mergeGeometries(geometries);
			List<RicadenzaInformazioni> ricadenzaNatura2000 = gsaReport
					.determinaRicadenzaSuSicPerGeometriaGML(mergedGeometry);
			ricadenzaNatura2000.addAll(gsaReport.determinaRicadenzaSuZpsPerGeometriaGML(mergedGeometry));

			// TODO totalSurface is mocked, and is the sum of all surfaces in the list
			secondSezione.setTotaleSuperficieCatastale(totalSurface);
			secondSezione.setTotaleSuperficieTrasf(totalSurface);
			secondSezione.setRicadenzaAreeProtette(
					gsaReport.determinaRicadenzaSuAreeProtettePerGeometriaGML(mergedGeometry));
			secondSezione.setRicadenzaNatura2000(ricadenzaNatura2000);
			secondSezione.setRicadenzaPopolamentiDaSeme(ricadenzaService.getPopolamentiDaSemes(mergedGeometry));
			secondSezione.setRicadenzaCategorieForestali(ricadenzaService.getCategoriesForestali(mergedGeometry));
			secondSezione.setRicadenzaVincoloIdrogeologico(true);
		}

		return Response.ok(secondSezione).build();
	}

	@Override
	public Response getParticellaCatastale(String istatComune, String sezione, Integer foglio, String particella,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			PlainParticelleCatastali particelleCatastali = new PlainParticelleCatastali();

			particelleCatastali.setComune(comuneDao.findComuneResourceByIstatComune(istatComune));
			particelleCatastali.setSezione(sezione);
			particelleCatastali.setFoglio(foglio);
			particelleCatastali.setParticella(particella);

			return Response.ok(sigmater.getParticelleCatastali(particelleCatastali)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSocietaLegale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(aaep.getCompaniesForRichiedenteLegale()).build();
	}

	@Override
	public Response getIstanzaTaglio(Integer numIstanza, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return Response.ok(primpa.getIstanzeDiTaglio(numIstanza)).build();
	}

	private Geometry mergeGeometries(List<Geometry> geometries) {
		// TODO: MOCKED - return null
		return null;
	}
}
