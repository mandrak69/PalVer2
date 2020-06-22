package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DichiarazioneSummaryDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoLnLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasformazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoAappDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoCatforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoPopSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoRn2000DAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.MacroCatforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParametroApplDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParametroTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParamtrasfTrasformazionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PartforInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PfPgDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PopolamentoSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkSelvDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SupForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoParametroTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ZonaAltimetricaDAO;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.BoscoHeadings;
import it.csi.idf.idfapi.dto.BoscoSubHeadings;
import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.dto.CompensationForesteDTO;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DichAltriPareri;
import it.csi.idf.idfapi.dto.DichAutorizzazione;
import it.csi.idf.idfapi.dto.DichCompensazione;
import it.csi.idf.idfapi.dto.DichDissensi;
import it.csi.idf.idfapi.dto.DichIstanzaTaglio;
import it.csi.idf.idfapi.dto.DichNota;
import it.csi.idf.idfapi.dto.DichProprieta;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.GeneratedFileParameters;
import it.csi.idf.idfapi.dto.GeoLnLottoIntervento;
import it.csi.idf.idfapi.dto.GeoPlLottoIntervento;
import it.csi.idf.idfapi.dto.GeoPtLottoIntervento;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.IntervTrasf;
import it.csi.idf.idfapi.dto.IntervTrasformazione;
import it.csi.idf.idfapi.dto.InterventoAapp;
import it.csi.idf.idfapi.dto.InterventoCatfor;
import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.dto.InterventoRn2000;
import it.csi.idf.idfapi.dto.InvioIstanzaDTO;
import it.csi.idf.idfapi.dto.IstanzaCompensazione;
import it.csi.idf.idfapi.dto.IstanzaForest;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.dto.ParametroAppl;
import it.csi.idf.idfapi.dto.ParametroTrasf;
import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;
import it.csi.idf.idfapi.dto.PfPg;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainPrimaSezione;
import it.csi.idf.idfapi.dto.PlainQuintaSezione;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.PlainSestoSezione;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.PlainTerzaSezione;
import it.csi.idf.idfapi.dto.PopolamentoSeme;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.SceltiParameter;
import it.csi.idf.idfapi.dto.SoggettoIntervento;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TerzaSezioneSaveResult;
import it.csi.idf.idfapi.dto.TipoParametroTrasf;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;
import it.csi.idf.idfapi.util.EnumUtil;
import it.csi.idf.idfapi.util.HeadingsDescriptionEnum;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.PlPfaEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SoggettoTypeEnum;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.TipoParametroApplEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import it.csi.idf.idfapi.util.ValidationUtil;
import it.csi.idf.idfapi.util.VincIdroEnum;

@Component
public class WrapperIstanzaDAOImpl implements WrapperIstanzaDAO {

	private static final String SEZIONE_1_VALID_MSG = "Sezione 1 deve essere completata";
	private static final String SEZIONE_2_VALID_MSG = "Sezione 2 deve essere completata";
	private static final String SEZIONE_3_VALID_MSG = "Sezione 3 deve essere completata";
	private static final String SEZIONE_4_VALID_MSG = "Sezione 4 deve essere completata";
	private static final String SEZIONE_5_VALID_MSG = "Sezione 5 deve essere completata";
	private static final String DATE_FORMAT = "yyyy-mm-dd";
	@Autowired
	DichiarazioneSummaryDAO dichiarazioneSummaryDAO;

	@Autowired
	private CategoriaForestaleDAO categoriaForestaleDAO;

	@Autowired
	private ComuneDAO comuneDAO;

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	private GeoLnLottoInterventoDAO geoLnLottoInterventoDAO;

	@Autowired
	private GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;

	@Autowired
	private GeoPtLottoInterventoDAO geoPtLottoInterventoDAO;

	@Autowired
	private GSAREPORT gsareport;

	@Autowired
	private InterventoDAO interventoDAO;

	@Autowired
	private IntervTrasformazioneDAO intervTrasformazioneDAO;

	@Autowired
	private IntervTrasfDAO intervTrasfDAO;

	@Autowired
	private InterventoAappDAO interventoAappDAO;

	@Autowired
	private InterventoCatforDAO interventoCatforDAO;

	@Autowired
	private InterventoPopSemeDAO interventoPopSemeDAO;

	@Autowired
	private InterventoRn2000DAO interventoRn2000DAO;

	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;

	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@Autowired
	private ParametroApplDAO parametroApplDAO;

	@Autowired
	private ParametroTrasfDAO parametroTrasfDAO;

	@Autowired
	private ParamtrasfTrasformazionDAO paramtrasfTrasformazionDAO;

	@Autowired
	private PfPgDAO pfPgDAO;

	@Autowired
	private PopolamentoSemeDAO popolamentoSemeDAO;

	@Autowired
	private PropCatastoDAO propCatastoDAO;

	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;

	@Autowired
	private MacroCatforDAO macroCatforDAO;

	@Autowired
	private RicadenzaService ricadenzaService;

	@Autowired
	private SkOkTrasfDAO skOkTrasfDAO;

	@Autowired
	private SkOkSelvDAO skOkSelvDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private SoggettoInterventoDAO soggettoInterventoDAO;

	@Autowired
	private SupForestaleDAO supForestaleDAO;

	@Autowired
	private TipoForestaleDAO tipoForestaleDAO;

	@Autowired
	private TipoParametroTrasfDAO tipoParametroTrasfDAO;

	@Autowired
	private ZonaAltimetricaDAO zonaAltimetricaDAO;
	
	@Autowired
	private PartforInterventoDAO partforInterventoDAO;


	@Override
	public StepNumber getNumberOfNextStep(Integer idIntervento) {
		return new StepNumber(skOkTrasfDAO.sumFlgSeziones(idIntervento));
	}

	@Override
	public PlainPrimaSezione getPrimaSezione(int idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException {

		PlainPrimaSezione plainPrimaSezione = new PlainPrimaSezione();

		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);

		plainPrimaSezione
				.setPersonaDatiOption(TipoTitolaritaEnum.getTitolarita(soggetoIntervento.getFkTipoTitolarita()));

		TSoggetto soggetto = soggettoDAO.findSoggettoById(soggetoIntervento.getIdSoggetto());

		plainPrimaSezione.setCodiceFiscale(soggetto.getCodiceFiscale());
		plainPrimaSezione.setNome(soggetto.getNome());
		plainPrimaSezione.setCognome(soggetto.getCognome());
		plainPrimaSezione.setDenominazione(soggetto.getDenominazione());
		plainPrimaSezione.setIndirizzo(soggetto.getIndirizzo());
		plainPrimaSezione.setCivico(soggetto.getCivico());
		plainPrimaSezione.setCap(soggetto.getCap());
		plainPrimaSezione.setNrTelefonico(soggetto.getNrTelefonico());
		plainPrimaSezione.seteMail(soggetto.geteMail());
		plainPrimaSezione.setPartitaIva(soggetto.getPartitaIva());
		plainPrimaSezione.setPec(soggetto.getPec());

		if (soggetto.getFkComune() != null) {
			plainPrimaSezione.setComune(comuneDAO.findComuneResourceByID(soggetto.getFkComune()));
		}

		IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);

		plainPrimaSezione.setTipoIstanzaId(istanzaForest.getFkTipoIstanza());
		plainPrimaSezione.setTipoIstanzaDescr(TipoIstanzaEnum.getEnum(istanzaForest.getFkTipoIstanza()).toString());

		return plainPrimaSezione;
	}

	@Override
	public PlainSezioneId savePrimaSezione(PlainPrimaSezione body, UserInfo user)
			throws RecordNotUniqueException, ValidationException {

		primaSezioneValidation(body);

		TSoggetto compilerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		ConfigUtente configUtente = configUtenteDAO.getCofigUtenteById(compilerSoggetto.getFkConfigUtente());

		int idIntervento = interventoDAO.createInterventoWithConfigUtente(compilerSoggetto.getFkConfigUtente());

		intervTrasformazioneDAO.createIntervTrasformazioneWithConfigUtente(idIntervento,
				compilerSoggetto.getFkConfigUtente());

		istanzaForestDAO
				.createIstanzaForest(getIstanzaForest(compilerSoggetto.getFkConfigUtente(), body, idIntervento));

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());

		if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RF)
				&& configUtente.getFkTipoAccreditamento().equals(TipoAccreditamentoEnum.RICHIEDENTE.getValue())) {
			soggetto = compilerSoggetto;
		} else if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RF)
				&& configUtente.getFkTipoAccreditamento().equals(TipoAccreditamentoEnum.PROFESSIONISTA.getValue())) {
			throw new ValidationException();
		}

		if (soggetto != null) {

			if (!body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RF)) {
				updateSoggettoPrimaSez(compilerSoggetto.getFkConfigUtente(), body, soggetto);
			}

			associateNaturalLegalPerson(compilerSoggetto.getFkConfigUtente(), body, soggetto.getIdSoggetto(),
					idIntervento, body.getPersonaDatiOption());
			associatePersonCompanyIfRG(compilerSoggetto, body, soggetto.getIdSoggetto());
		} else {
			int idSoggetto = insertSoggetoPrimaSez(compilerSoggetto.getFkConfigUtente(), body);
			associateNaturalLegalPerson(compilerSoggetto.getFkConfigUtente(), body, idSoggetto, idIntervento,
					body.getPersonaDatiOption());

			associatePersonCompanyIfRG(compilerSoggetto, body, idSoggetto);
		}

		skOkTrasfDAO.insertFlgSez1(idIntervento);

		return new PlainSezioneId(idIntervento);
	}

	@Override
	public void updatePrimaSezione(PlainPrimaSezione body, UserInfo user, int idIntervento)
			throws ValidationException, RecordNotUniqueException {
		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		primaSezioneValidation(body);

		TSoggetto compilerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());

		if (soggetto != null) {

			if (!body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RF)) {
				updateSoggettoPrimaSez(compilerSoggetto.getFkConfigUtente(), body, soggetto);
			}
			associatePersonCompanyIfRG(compilerSoggetto, body, soggetto.getIdSoggetto());
			updateNaturalLegalPerson(soggetto.getIdSoggetto(), body.getPersonaDatiOption(), idIntervento);
		} else {
			if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RF)) {
				throw new ValidationException();
			}

			int idSoggetto = insertSoggetoPrimaSez(compilerSoggetto.getFkConfigUtente(), body);
			updateNaturalLegalPerson(idSoggetto, body.getPersonaDatiOption(), idIntervento);

			associatePersonCompanyIfRG(compilerSoggetto, body, idSoggetto);
		}
	}

	@Override
	public PlainSecondoSezione getSecondoSezione(Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		int superficieInteresata = interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata();
		BigDecimal superficie;
		if (superficieInteresata != 0) {
			superficie = BigDecimal.valueOf((long) superficieInteresata);
		} else {
			superficie = null;
		}

		PlainSecondoSezione plainSecondoSezione = new PlainSecondoSezione();
		plainSecondoSezione.setTotaleSuperficieCatastale(superficie);
		plainSecondoSezione.setTotaleSuperficieTrasf(superficie);

		List<RicadenzaInformazioni> ricadenzaNatura2000 = retrieveRicadenzaNatura2000Sic(idIntervento);
		ricadenzaNatura2000.addAll(retrieveRicadenzaNatura2000Zps(idIntervento));

		plainSecondoSezione.setRicadenzaAreeProtette(retrieveRicadenzaAreeProtette(idIntervento));
		plainSecondoSezione.setRicadenzaNatura2000(ricadenzaNatura2000);

		plainSecondoSezione.setRicadenzaPopolamentiDaSeme(retrieveRicadenzaPopolamentiDaSeme(idIntervento));
		plainSecondoSezione.setRicadenzaCategorieForestali(retrieveRicadenzaCategorieForestali(idIntervento));

		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);

		if (intervTrasformazione != null) {
			plainSecondoSezione.setRicadenzaVincoloIdrogeologico(intervTrasformazione.getFlgVincIdro() == 1);
		}
		List<PropCatasto> propCatastos = propCatastoDAO.getPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento));

		plainSecondoSezione.setParticelleCatastali(mapPropCatastosToParticelleCatastali(propCatastos));

		if (!propCatastos.isEmpty()) {
			plainSecondoSezione.setAnnotazioni(propCatastos.get(0).getNote());
		}

		return plainSecondoSezione;
	}

	@Override
	public void saveSecondoSezione(PlainSecondoSezione body, UserInfo user, int idIntervento)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento, soggetto.getFkConfigUtente(),
				body.getAnnotazioni());

		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());

		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento, soggetto);
		saveRicadenzaVincoloIdrogeologico(body.isRicadenzaVincoloIdrogeologico(), idIntervento);

		IntervTrasf intervTrasf = new IntervTrasf();
		intervTrasf.setFkIntervento(idIntervento);
		intervTrasf.setDataInserimento(LocalDate.now());
		intervTrasfDAO.insertIntervTrasf(intervTrasf);

		skOkTrasfDAO.updateFlgSez2(idIntervento);
	}

	@Override
	public void updateSecondoSezione(PlainSecondoSezione body, UserInfo user, Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {
		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento, soggetto.getFkConfigUtente(),
				body.getAnnotazioni());

		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());

		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		for (RicadenzaInformazioni ricadenza : body.getRicadenzaAreeProtette()) {
			InterventoAapp interventoAapp = new InterventoAapp();
			interventoAapp.setIdIntervento(idIntervento);
			interventoAapp.setCodiceAapp(ricadenza.getCodiceAmministrativo());
			interventoAappDAO.insertInterventoAapp(interventoAapp);
		}

		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		for (RicadenzaInformazioni ricadenza : body.getRicadenzaNatura2000()) {
			InterventoRn2000 interventoRn2000 = new InterventoRn2000();
			interventoRn2000.setIdIntervento(idIntervento);
			interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
			interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
		}

		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		for (RicadenzaInformazioni ricadenza : body.getRicadenzaPopolamentiDaSeme()) {
			InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
			interventoPopSeme.setIdIntervento(idIntervento);
			interventoPopSeme.setIdPopolamentoSeme(popolamentoSemeDAO
					.getByCodiceAmministrativo(ricadenza.getCodiceAmministrativo()).getIdPopolamentoSeme());
			interventoPopSemeDAO.insertInterventoPopSeme(interventoPopSeme);
		}

		interventoCatforDAO.deleteInterventosById(idIntervento);
		for (RicadenzaInformazioni ricadenza : body.getRicadenzaCategorieForestali()) {
			InterventoCatfor interventoCatfor = new InterventoCatfor();
			interventoCatfor.setIdIntervento(idIntervento);
			interventoCatfor.setIdCategoriaForestale(categoriaForestaleDAO
					.getByCodiceAmministratico(ricadenza.getCodiceAmministrativo()).getIdCategoriaForestale());
			interventoCatfor.setFkConfigUtente(soggetto.getFkConfigUtente());
			interventoCatforDAO.insertInterventoCatfor(interventoCatfor);
		}

		if (body.isRicadenzaVincoloIdrogeologico()) {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.YES.getValue(), idIntervento);
		} else {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.NO.getValue(), idIntervento);
		}
	}

	@Override
	public PlainTerzaSezione getTerzaSezione(int idIntervento) throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez2Done(idIntervento)) {
			throw new ValidationException(SEZIONE_2_VALID_MSG);
		}

		PlainTerzaSezione plainTerzaSezione = new PlainTerzaSezione();
		List<BoscoHeadings> boscoHeadingList = new ArrayList<>();
		List<TipoParametroTrasf> headings = tipoParametroTrasfDAO.getTipoParametroTrasfs();
		List<ParametroTrasf> subHeadings = parametroTrasfDAO.getParametroTrasfs();

		for (TipoParametroTrasf heading : headings) {
			List<BoscoSubHeadings> boscoSubHeadingList = new ArrayList<>();
			for (ParametroTrasf subHeading : subHeadings) {
				if (subHeading.getFkTipoParametroTrasf().equals(heading.getIdTipoParametroTrasf())
						&& subHeading.getFlgVisible() == 1) {
					boscoSubHeadingList.add(getBoscoSubHeadings(subHeading));
				}
			}
			if (heading.getFlgVisible() == 1) {
				boscoHeadingList.add(getBoscoHeading(heading, boscoSubHeadingList));
			}
		}
		plainTerzaSezione.setHeadings(boscoHeadingList);
		preselectTheRightOptions(idIntervento, plainTerzaSezione);

		return plainTerzaSezione;
	}

	@Override
	public TerzaSezioneSaveResult saveTerzaSezione(int idIntervento, UserInfo user, PlainTerzaSezione body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez2Done(idIntervento)) {
			throw new ValidationException(SEZIONE_2_VALID_MSG);
		}

		TerzaSezioneSaveResult response = new TerzaSezioneSaveResult();

		response.setWarning(preselectedOptionsWarning(idIntervento, body.getHeadings()));

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		BigDecimal calculation = getBaseCalculation(idIntervento);
		List<ParamtrasfTrasformazion> paramtrasfTrasformazions = paramtrasfTrasformazionDAO
				.getParamtrasfTrasformazionsByIdIntervento(idIntervento);
		List<ParamtrasfTrasformazion> checkedParamtrasf = new ArrayList<>();
		for (BoscoHeadings heading : body.getHeadings()) {
			List<BoscoSubHeadings> checkedSubHeadings = getSubHeadingsfWithCheckedValue(heading);

			if (!checkedSubHeadings.isEmpty()) {
				BoscoSubHeadings filteredSubHeading = getFilteredCheckedSubHeadings(heading);
				calculation = calculation
						.multiply(filteredSubHeading != null ? filteredSubHeading.getValore() : BigDecimal.ONE);
				for (BoscoSubHeadings checkedSubHeading : checkedSubHeadings) {
					checkedParamtrasf.add(mapSubheadingToParatrasf(idIntervento, soggetto.getFkConfigUtente(),
							checkedSubHeading.getId()));
				}
			}
		}
		executeParamtrasfTrasformazion(paramtrasfTrasformazions, checkedParamtrasf);

		intervTrasformazioneDAO.setCompenzazioneEuro(calculation, idIntervento);
		skOkTrasfDAO.updateFlgSez3(idIntervento);
		return response;
	}

	@Override
	public CompensationForesteDTO getQuartaSezione(Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez3Done(idIntervento)) {
			throw new ValidationException(SEZIONE_3_VALID_MSG);
		}

		IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
		if (intervTrasf != null) {
			CompensationForesteDTO compensationForesteDTO = new CompensationForesteDTO();
			// Error report (error 11/12)

			compensationForesteDTO.setNecessaria(false);
			compensationForesteDTO.setNonNecessaria(false);

			if (intervTrasf.getFlgCompensazione() == null
					&& interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata() < 500) {
				compensationForesteDTO.setNonNecessaria(true);
				compensationForesteDTO.setFlgA(true);
			} else if (intervTrasf.getFlgCompensazione() != null) {
				switch (EnumUtil.stringToEnum(intervTrasf.getFlgCompensazione(), SuperficieCompensationEnum.class)) {
				case F:
					compensationForesteDTO.setNecessaria(true);
					compensationForesteDTO.setCompFisica(true);
					break;
				case M:
					compensationForesteDTO.setNecessaria(true);
					compensationForesteDTO.setCompMonetaria(true);
					break;
				case N:
					compensationForesteDTO.setNonNecessaria(true);
					setFlgArts(idIntervento, intervTrasf, compensationForesteDTO);
					break;
				default:
				}
			}

			return compensationForesteDTO;
		}
		return null;
	}

	@Override
	public void saveQuartaSezione(Integer idIntervento, CompensationForesteDTO compensationForesteDTO)
			throws ValidationException, RecordNotUniqueException {
		if (!skOkTrasfDAO.isFlgSez3Done(idIntervento)) {
			throw new ValidationException(SEZIONE_3_VALID_MSG);
		}

		if ((!compensationForesteDTO.isNecessaria() && !compensationForesteDTO.isNonNecessaria())
				|| (!compensationForesteDTO.isFlgA() && !compensationForesteDTO.isFlgB()
						&& !compensationForesteDTO.isFlgC() && !compensationForesteDTO.isFlgD()
						&& !compensationForesteDTO.isFlgDbis() && !compensationForesteDTO.isCompFisica()
						&& !compensationForesteDTO.isCompMonetaria())
				|| idIntervento == null) {
			throw new IllegalArgumentException("Illegal parameters");
		}

		if (skOkTrasfDAO.sumFlgSeziones(idIntervento) >= 4
				&& !SuperficieCompensationEnum.N.toString()
						.equals(intervTrasformazioneDAO.getIntervTrasfById(idIntervento).getFlgCompensazione())
				&& compensationForesteDTO.isNecessaria()) {
			skOkTrasfDAO.resetFlgToStep4(idIntervento);
		}

		skOkTrasfDAO.updateFlgSez4(idIntervento);

		if (compensationForesteDTO.isNecessaria()) {
			if (compensationForesteDTO.isCompFisica()) {
				intervTrasformazioneDAO.updateCompensazioneNecessaria(SuperficieCompensationEnum.F, idIntervento);
			} else {
				intervTrasformazioneDAO.updateCompensazioneNecessaria(SuperficieCompensationEnum.M, idIntervento);
			}
		}
		skOkTrasfDAO.resetFlgToStep4(idIntervento);

		if (compensationForesteDTO.isNonNecessaria()) {
			intervTrasformazioneDAO.updateCompensazioneNonNecessaria(compensationForesteDTO, idIntervento);
			skOkTrasfDAO.updateFlgSez5(idIntervento);
		}
	}

	@Override
	public PlainQuintaSezione getQuintaSezione(Integer idIntervento)
			throws RecordNotUniqueException, DuplicateRecordException, RecordNotFoundException, ValidationException {
		if (!skOkTrasfDAO.isFlgSez4Done(idIntervento)) {
			throw new ValidationException(SEZIONE_4_VALID_MSG);
		}

		List<SceltiParameter> sceltiParametri = parametroTrasfDAO.getSceltoParemeterByParamtrasfTrasformazion(
				paramtrasfTrasformazionDAO.getParamtrasfTrasformazionsByIdIntervento(idIntervento));

		PlainQuintaSezione plainQuintaSezione = new PlainQuintaSezione();
		plainQuintaSezione.setBaseValue(Long.valueOf(
				parametroApplDAO.getParamertroByTipo(TipoParametroApplEnum.BASE_CALCOLO_EURO).getParametroApplNum()));
		plainQuintaSezione.setSuperficie(BigDecimal
				.valueOf(interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata()));
		plainQuintaSezione.setSceltiParametri(filterSceltiParametri(sceltiParametri));
		IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
		if (intervTrasf != null) {
			plainQuintaSezione.setTotale(intervTrasf.getCompenzazioneEuro());
		}
		plainQuintaSezione.setSoggettoProfess(getFkSoggettoProfess(idIntervento));

		return plainQuintaSezione;
	}

	@Override
	public void saveQuintaSezione(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotUniqueException, ValidationException {
		if (!skOkTrasfDAO.isFlgSez4Done(idIntervento)) {
			throw new ValidationException(SEZIONE_4_VALID_MSG);
		}

		validateQuintaSezione(soggetto);
		int fkSoggettoProfess;

		TSoggetto soggettoFromDB = soggettoDAO.findSoggettoByCodiceFiscale(soggetto.getCodiceFiscale());

		if (soggettoFromDB == null) {
			soggetto.setFlgSettoreRegionale((byte) 0); // NOT NULL constraint
			fkSoggettoProfess = soggettoDAO.createSoggetto(soggetto);
		} else {
			if (!areTwoSoggettosTheSame(soggetto, soggettoFromDB)) {
				soggettoDAO.updateSoggettoProfess(soggetto);
			}
			fkSoggettoProfess = soggettoFromDB.getIdSoggetto();
		}

		interventoDAO.updateInterventoWithFkSoggettoProfess(idIntervento, fkSoggettoProfess);
		skOkTrasfDAO.updateFlgSez5(idIntervento);
	}

	@Override
	public PlainSestoSezione getSestoSezione(Integer idIntervento) throws RecordNotUniqueException {
		List<ParametroAppl> parametroAppl = parametroApplDAO.getParamertriByTipo(TipoParametroApplEnum.DICHIARAZIONI);
		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
		PlainSestoSezione plainSestoSezione = new PlainSestoSezione();

		plainSestoSezione.setEtichetta(parametroAppl.get(0).getParametroApplChar());
		plainSestoSezione.setProprieta(retrieveDichProprieta(parametroAppl.get(1), parametroAppl.get(2),
				intervTrasformazione.getFlgProprieta()));
		plainSestoSezione
				.setDissensi(retrieveDichDissensi(parametroAppl.get(3), intervTrasformazione.getFlgDissensi()));
		plainSestoSezione.setPaesaggistica(retrieveDichPaesaggistica(parametroAppl.get(4), intervTrasformazione));
		plainSestoSezione
				.setVincIdrogeologico(retrieveDichVincIdrogeologico(parametroAppl.get(5), intervTrasformazione));
		plainSestoSezione.setValIncidenza(retrieveDichValIncidenza(parametroAppl.get(6), intervTrasformazione,
				interventoRn2000DAO.getInterventosByIdIntervento(idIntervento)));
		plainSestoSezione.setAltriPareri(retrieveDichAltriPareri(parametroAppl.get(7), intervTrasformazione));
		plainSestoSezione.setCompFisica(retrieveDichCompFisica(parametroAppl.get(8), intervTrasformazione));
		plainSestoSezione.setCompMonetaria(retrieveDichCompMonetaria(parametroAppl.get(9), intervTrasformazione));
		plainSestoSezione.setAltroAllega(retrieveDichAltroAllega());
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_MONETARIA);
		tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		plainSestoSezione.setAllegati(documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento, tipoAllegati));
		plainSestoSezione.setIstanzi(retrieveDichIstanzi(parametroAppl.get(10), idIntervento, intervTrasformazione));
		plainSestoSezione.setNota(retrieveDichNota(intervTrasformazione));
		return plainSestoSezione;
	}

	@Override
	public void saveSestoSezione(Integer idIntervento, String codFiscale, PlainSestoSezione plainSestoSezione)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez5Done(idIntervento)) {
			throw new ValidationException(SEZIONE_5_VALID_MSG);
		}

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(codFiscale);

		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_MONETARIA);
		tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		documentoAllegatiManipulation(plainSestoSezione.getAllegati(), soggetto.getFkConfigUtente(), idIntervento,
				tipoAllegati);
		sestoSezioneValidation(plainSestoSezione);
		intervTrasformazioneDAO.updateWithDichiarazioni(retrieveIntervTrasformazioneForDich(plainSestoSezione),
				idIntervento);

		istanzaComensazioneManipulation(plainSestoSezione.getIstanzi(), soggetto.getFkConfigUtente(), idIntervento);
		skOkTrasfDAO.updateFlgSez6(idIntervento);
	}

	@Override
	public PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, String codiceFiscale)
			throws ParseException, RecordNotUniqueException, ValidationException {
		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(codiceFiscale);
		ConfigUtente configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());

		validateSearchParameters(queryParams, configUtente, soggetto);

		return istanzaForestDAO.backOfficeSearch(queryParams);
	}

	@Override
	public List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento) {
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_MONETARIA);
		tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.DOCUMENTO_IDENTITA);
		return documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento, tipoAllegati);
	}

	@Override
	public InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento) {
		return new InvioIstanzaDTO(istanzaForestDAO.getByIdIntervento(idIntervento).getDataInvio());
	}

	@Override
	public void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);

		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
			tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE);
			tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA);
			tipoAllegati.add(TipoAllegatoEnum.DOCUMENTO_IDENTITA);

			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);


			// TODO Send e-mail
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateInvioIstanza(idIntervento);
	}

	@Transactional
	@Override
	public PlainSezioneId saveLocalizzaLotto(PfaLottoLocalizza body, UserInfo user, Integer idGeoPlPfa)
			throws RecordNotUniqueException {

		TSoggetto compilerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

		// TODO not sure in which more tables do I need to save data

		int idIntervento = interventoDAO.createInterventoWithConfigUtente(compilerSoggetto.getFkConfigUtente());
		
		intervSelvicoulturaleDAO.insertIntervSelvicolturale(
				retrieveIntervSelvicolturaleForInsert(idGeoPlPfa, compilerSoggetto, idIntervento), idIntervento);
		
		// TODO taking mocked idgeo forest particella, should be depending on geometry
		// inserting into idf_r_partfor_intervento
		
		int idGeoParticelleForestali = 15;
		for (PlainParticelleCatastali tempParticella : body.getParticelleCatastali()) {
			partforInterventoDAO.createParforInterv(idGeoParticelleForestali, idIntervento);
			idGeoParticelleForestali++;
			}
		

		geoPlLottoInterventoDAO.insertGeoPlLottoIntervento(retireveGeoPlLottoInterventoForInsert(idIntervento));

		geoPtLottoInterventoDAO.insertGeoPtLottoIntervento(retireveGeoPtLottoInterventoForInsert(idIntervento));

		geoLnLottoInterventoDAO.insertGeoLnLottoIntervento(retireveGeoLnLottoInterventoForInsert(idIntervento));

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento, soggetto.getFkConfigUtente(),
				null);
		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());

		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento, soggetto);
		saveRicadenzaVincoloIdrogeologico(body.isRicadenzaVincoloIdrogeologico(), idIntervento);

		skOkSelvDAO.insertFlgSez1(idIntervento);

		return new PlainSezioneId(idIntervento);
	}

	private GeoLnLottoIntervento retireveGeoLnLottoInterventoForInsert(int idIntervento) {
		GeoLnLottoIntervento geoLnLottoIntervento = new GeoLnLottoIntervento();
		geoLnLottoIntervento.setDatainserimento(LocalDate.now());
		geoLnLottoIntervento.setIdIntervento(idIntervento);
		return geoLnLottoIntervento;
	}

	private GeoPtLottoIntervento retireveGeoPtLottoInterventoForInsert(int idIntervento) {
		GeoPtLottoIntervento geoPtLottoIntervento = new GeoPtLottoIntervento();
		geoPtLottoIntervento.setDatainserimento(LocalDate.now());
		geoPtLottoIntervento.setIdIntervento(idIntervento);
		return geoPtLottoIntervento;
	}

	private GeoPlLottoIntervento retireveGeoPlLottoInterventoForInsert(int idIntervento) {
		GeoPlLottoIntervento geoPlLottoIntervento = new GeoPlLottoIntervento();
		geoPlLottoIntervento.setDatainserimento(LocalDate.now());
		geoPlLottoIntervento.setFkIntervento(idIntervento);

		return geoPlLottoIntervento;
	}

	private void primaSezioneValidation(PlainPrimaSezione body) throws ValidationException {
		if (body.getTipoIstanzaId() == null || body.getTipoIstanzaDescr() == null || body.getPersonaDatiOption() == null
				|| body.getCodiceFiscale() == null) {
			throw new ValidationException();
		}
		if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.PF)
				&& (body.getCognome() == null || body.getNome() == null)) {
			throw new ValidationException();
		}
		if (!body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RF)
				&& ((body.getIndirizzo() == null || body.getNrTelefonico() == null || body.geteMail() == null
						|| body.getCivico() == null || body.getCap() == null || body.getComune() == null)
						|| (body.getIndirizzo().length() > 50 || body.getCivico().length() > 10
								|| body.getCap().length() != 5 || body.getNrTelefonico().length() > 20
								|| body.geteMail().length() > 50)
						|| !ValidationUtil.isEMail(body.geteMail()))) {
			throw new ValidationException();
		}
		if ((body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RG)
				|| body.getPersonaDatiOption().equals(TipoTitolaritaEnum.PG))
				&& (body.getPartitaIva() == null || body.getPartitaIva().length() > 11 || body.getPec() == null
						|| body.getPec().length() > 50 || body.getDenominazione() == null
						|| body.getDenominazione().length() > 200)) {
			throw new ValidationException();
		}
	}

	private void updateSoggettoPrimaSez(int compilerSoggettoConfigUtente, PlainPrimaSezione body, TSoggetto soggetto)
			throws RecordNotUniqueException {
		soggetto.setCodiceFiscale(body.getCodiceFiscale());
		soggetto.setIndirizzo(body.getIndirizzo());
		soggetto.setNrTelefonico(body.getNrTelefonico());
		soggetto.seteMail(body.geteMail());
		soggetto.setDataAggiornamento(LocalDate.now());
		soggetto.setFkConfigUtente(compilerSoggettoConfigUtente);
		soggetto.setCivico(body.getCivico());
		soggetto.setCap(body.getCap());

		if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.PF)) {
			soggetto.setCognome(body.getCognome());
			soggetto.setNome(body.getNome());
		} else if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RG)
				|| body.getPersonaDatiOption().equals(TipoTitolaritaEnum.PG)) {
			soggetto.setPartitaIva(body.getPartitaIva());
			soggetto.setDenominazione(body.getDenominazione());
			soggetto.setPec(body.getPec());
		}

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			soggetto.setFkComune(comuneResource.getIdComune());
		}

		soggettoDAO.updateSoggetto(soggetto);
	}

	private int insertSoggetoPrimaSez(int compilerSoggettoConfigUtente, PlainPrimaSezione body)
			throws RecordNotUniqueException {
		TSoggetto newSoggetto = new TSoggetto();

		newSoggetto.setCodiceFiscale(body.getCodiceFiscale());
		newSoggetto.setIndirizzo(body.getIndirizzo());
		newSoggetto.setNrTelefonico(body.getNrTelefonico());
		newSoggetto.seteMail(body.geteMail());
		newSoggetto.setDataInserimento(LocalDate.now());
		newSoggetto.setFkConfigUtente(compilerSoggettoConfigUtente);
		newSoggetto.setCivico(body.getCivico());
		newSoggetto.setCap(body.getCap());
		newSoggetto.setFlgSettoreRegionale((byte) 0);

		if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.PF)) {
			newSoggetto.setCognome(body.getCognome());
			newSoggetto.setNome(body.getNome());
		} else if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RG)
				|| body.getPersonaDatiOption().equals(TipoTitolaritaEnum.PG)) {
			newSoggetto.setPartitaIva(body.getPartitaIva());
			newSoggetto.setDenominazione(body.getDenominazione());
			newSoggetto.setPec(body.getPec());
		}

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			newSoggetto.setFkComune(comuneResource.getIdComune());
		}
		return soggettoDAO.createSoggetto(newSoggetto);
	}

	private IstanzaForest getIstanzaForest(int compilerSoggettoConfigUtente, PlainPrimaSezione body, int idIntervento) {
		IstanzaForest istanzaForest = new IstanzaForest();

		istanzaForest.setIdIstanzaIntervento(idIntervento);
		istanzaForest.setDataInserimento(LocalDate.now());
		istanzaForest.setFkStatoIstanza(InstanceStateEnum.BOZZA.getStateValue());
		istanzaForest.setFkTipoIstanza(body.getTipoIstanzaId());
		istanzaForest.setNrIstanzaForestale(istanzaForestDAO.getNumberOfInstanceType(body.getTipoIstanzaId()) + 1);
		istanzaForest.setFkConfigUtente(compilerSoggettoConfigUtente);
		return istanzaForest;
	}

	private void associateNaturalLegalPerson(int compilerSoggettoConfigUtente, PlainPrimaSezione body, int idSoggetto,
			int idIntervento, TipoTitolaritaEnum tipoTitolarita) {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();

		soggettoIntervento.setIdIntervento(idIntervento);
		soggettoIntervento.setIdSoggetto(idSoggetto);
		if (tipoTitolarita.equals(TipoTitolaritaEnum.RF) || tipoTitolarita.equals(TipoTitolaritaEnum.RG)) {
			soggettoIntervento.setIdTipoSoggetto(SoggettoTypeEnum.RICHIEDENTE.getValue());
		} else {
			soggettoIntervento.setIdTipoSoggetto(SoggettoTypeEnum.TECNICO_FORESTALE.getValue());
		}
		soggettoIntervento.setFkConfigUtente(compilerSoggettoConfigUtente);
		soggettoIntervento.setDataInizioValidita(LocalDate.now());
		soggettoIntervento.setFkTipoTitolarita(TipoTitolaritaEnum.getTitolaritaOption(body.getPersonaDatiOption()));

		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);
	}

	private void associatePersonCompanyIfRG(TSoggetto compilerSoggetto, PlainPrimaSezione body, int idSoggetto)
			throws RecordNotUniqueException {
		if (body.getPersonaDatiOption().equals(TipoTitolaritaEnum.RG)) {

			PfPg pfPgFromDB = pfPgDAO.getBySoggettoPfAndSoggettoPg(compilerSoggetto.getIdSoggetto(), idSoggetto);

			if (pfPgFromDB == null) {
				PfPg pfPg = new PfPg();
				pfPg.setIdSoggettoPf(compilerSoggetto.getIdSoggetto());
				pfPg.setIdSoggettoPg(idSoggetto);
				pfPg.setDataInserimento(LocalDate.now());
				pfPg.setDataInizioValidita(LocalDate.now());
				pfPg.setFkConfigUtente(compilerSoggetto.getFkConfigUtente());

				pfPgDAO.createPfPg(pfPg);
			}
		}
	}

	private void updateNaturalLegalPerson(int idSoggetto, TipoTitolaritaEnum personaDatiOption, int idIntervento) {
		soggettoInterventoDAO.updateWithIdSoggettoAndFkTipoTitolarita(idSoggetto,
				TipoTitolaritaEnum.getTitolaritaOption(personaDatiOption), idIntervento);
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaAreeProtette(Integer idIntervento) {
		List<InterventoAapp> interventoAapps = interventoAappDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> ricadenzeAapp = gsareport.cercaTutteLePotettePerFiltri();
		List<RicadenzaInformazioni> response = new ArrayList<>();
		for (InterventoAapp interventoAapp : interventoAapps) {
			RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(interventoAapp.getCodiceAapp());
			if (ricadenzeAapp.contains(ricadenzaInformazioni)) {
				response.add(ricadenzeAapp.get(ricadenzeAapp.indexOf(ricadenzaInformazioni)));
			}
		}

		return response;
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Sic(Integer idIntervento) {
		List<InterventoRn2000> interventoNaturas2000 = interventoRn2000DAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> ricadenzeNature = gsareport.cercaTuttiSic();
		List<RicadenzaInformazioni> response = new ArrayList<>();
		for (InterventoRn2000 interventoNatura : interventoNaturas2000) {
			RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(interventoNatura.getCodiceRn2000());
			if (ricadenzeNature.contains(ricadenzaInformazioni)) {
				response.add(ricadenzeNature.get(ricadenzeNature.indexOf(ricadenzaInformazioni)));
			}
		}

		return response;
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Zps(Integer idIntervento) {
		List<InterventoRn2000> interventoNaturas2000 = interventoRn2000DAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> ricadenzeNature = gsareport.cercaTuttiZps();
		List<RicadenzaInformazioni> response = new ArrayList<>();
		for (InterventoRn2000 interventoNatura : interventoNaturas2000) {
			RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(interventoNatura.getCodiceRn2000());
			if (ricadenzeNature.contains(ricadenzaInformazioni)) {
				response.add(ricadenzeNature.get(ricadenzeNature.indexOf(ricadenzaInformazioni)));
			}
		}
		return response;
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaPopolamentiDaSeme(Integer idIntervento) {
		List<InterventoPopSeme> interventoPopSemes = interventoPopSemeDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> response = new ArrayList<>();
		if (!interventoPopSemes.isEmpty()) {
			List<PopolamentoSeme> poplamentoSemes = popolamentoSemeDAO.getAllByInterventoPopSemes(interventoPopSemes);
			List<RicadenzaInformazioni> ricadenzePopSeme = ricadenzaService.cercaTuttiPopolamentiDaSeme();

			for (PopolamentoSeme poplamentoSeme : poplamentoSemes) {
				RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(
						poplamentoSeme.getCodiceAmministrativo());
				if (ricadenzePopSeme.contains(ricadenzaInformazioni)) {
					response.add(ricadenzePopSeme.get(ricadenzePopSeme.indexOf(ricadenzaInformazioni)));
				}
			}
		}
		return response;
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaCategorieForestali(Integer idIntervento) {
		List<InterventoCatfor> interventoCatfors = interventoCatforDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> response = new ArrayList<>();

		if (!interventoCatfors.isEmpty()) {
			List<RicadenzaInformazioni> ricadenzePopSeme = ricadenzaService.cercaTutteCategorieForestali();
			List<CategoriaForestale> categorieForestale = categoriaForestaleDAO
					.getAllByInterventoCatfors(interventoCatfors);

			for (CategoriaForestale categoria : categorieForestale) {
				RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(
						categoria.getCodiceAmministrativo());
				if (ricadenzePopSeme.contains(ricadenzaInformazioni)) {
					response.add(ricadenzePopSeme.get(ricadenzePopSeme.indexOf(ricadenzaInformazioni)));
				}
			}
		}
		return response;
	}

	public List<PlainParticelleCatastali> mapPropCatastosToParticelleCatastali(List<PropCatasto> propCatastos)
			throws RecordNotUniqueException {
		List<PlainParticelleCatastali> response = new ArrayList<>();

		for (PropCatasto propCatasto : propCatastos) {
			PlainParticelleCatastali particelleCatastali = new PlainParticelleCatastali();
			particelleCatastali.setId(propCatasto.getIdGeoPlPropCatasto());

			ComuneResource comune = comuneDAO.findComuneResourceByID(propCatasto.getFkComune());
			particelleCatastali.setComune(comune);

			particelleCatastali.setSezione(propCatasto.getSezione());
			particelleCatastali.setFoglio(propCatasto.getFoglio());
			particelleCatastali.setParticella(propCatasto.getParticella());
			particelleCatastali.setSupCatastale(propCatasto.getSupCatastaleMq());
			particelleCatastali.setToDelete(false);

			response.add(particelleCatastali);
		}

		return response;
	}

	private void insertOrDeleteParticelleCatastale(List<PlainParticelleCatastali> particelleCatastali, int idIntervento,
			int fkConfigUtente, String note) throws RecordNotUniqueException {
		for (PlainParticelleCatastali particella : particelleCatastali) {

			if (particella.isToDelete() && !particella.getId().equals(0) && particella.getId() != null) {
				propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
				propCatastoDAO.deletePropCatasto(particella.getId());
			} else if ((particella.getId() == null || particella.getId().equals(0)) && !particella.isToDelete()) {
				PropCatasto propCatasto = new PropCatasto();
				propCatasto.setFkComune(comuneDAO
						.findComuneResourceByIstatComune(particella.getComune().getIstatComune()).getIdComune());
				propCatasto.setSezione(particella.getSezione());
				propCatasto.setFoglio(particella.getFoglio());
				propCatasto.setParticella(particella.getParticella());
				propCatasto.setSupCatastaleMq(particella.getSupCatastale());
				propCatasto.setSupCartograficaHa(particella.getSupCatastale());
				propCatasto.setNote(note);
				// required fields
				propCatasto.setFkProprieta(23);
				propCatasto.setFlgLivellari((byte) 0);
				propCatasto.setFlgPossessiContest((byte) 0);
				propCatasto.setFlgUsiCivici((byte) 0);
				propCatasto.setDataInizioValidita(LocalDate.now());

				PropcatastoIntervento propcatastoIntervento = new PropcatastoIntervento();
				propcatastoIntervento.setIdgeoPlPropCatasto(propCatastoDAO.insertPropCatasto(propCatasto));
				propcatastoIntervento.setIdIntervento(idIntervento);
				propcatastoIntervento.setFkConfigUtente(fkConfigUtente);
				propcatastoIntervento.setDataInserimento(LocalDate.now());
				propcatastoInterventoDAO.insertPropcatastoIntervento(propcatastoIntervento);
			} else {
				propCatastoDAO.updateAllNoteOfOneIntervento(note, idIntervento);
			}
		}
	}

	private void saveRicadenzaVincoloIdrogeologico(boolean isVincoloIdrogeologico, int idIntervento) {
		if (isVincoloIdrogeologico) {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.YES.getValue(), idIntervento);
		} else {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.NO.getValue(), idIntervento);
		}
	}

	private void saveRicadenzeCategorieForestale(List<RicadenzaInformazioni> ricadenze, int idIntervento,
			TSoggetto soggetto) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoCatfor interventoCatfor = new InterventoCatfor();
			interventoCatfor.setIdIntervento(idIntervento);
			interventoCatfor.setIdCategoriaForestale(categoriaForestaleDAO
					.getByCodiceAmministratico(ricadenza.getCodiceAmministrativo()).getIdCategoriaForestale());
			interventoCatfor.setFkConfigUtente(soggetto.getFkConfigUtente());
			interventoCatforDAO.insertInterventoCatfor(interventoCatfor);
		}
	}

	private void saveRicadenzePopolamentiSeme(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
			interventoPopSeme.setIdIntervento(idIntervento);
			interventoPopSeme.setIdPopolamentoSeme(popolamentoSemeDAO
					.getByCodiceAmministrativo(ricadenza.getCodiceAmministrativo()).getIdPopolamentoSeme());
			interventoPopSemeDAO.insertInterventoPopSeme(interventoPopSeme);
		}
	}

	private void saveRicadenzeNature2000(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoRn2000 interventoRn2000 = new InterventoRn2000();
			interventoRn2000.setIdIntervento(idIntervento);
			interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
			interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
		}
	}

	private void saveRicadenzeAreeProtette(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoAapp interventoAapp = new InterventoAapp();
			interventoAapp.setIdIntervento(idIntervento);
			interventoAapp.setCodiceAapp(ricadenza.getCodiceAmministrativo());
			interventoAappDAO.insertInterventoAapp(interventoAapp);
		}
	}

	private BoscoHeadings getBoscoHeading(TipoParametroTrasf heading, List<BoscoSubHeadings> boscoSubHeadingList) {
		BoscoHeadings boscoHeading = new BoscoHeadings();

		boscoHeading.setId(heading.getIdTipoParametroTrasf());
		boscoHeading.setName(heading.getTipoParemetroTrasf());
		boscoHeading.setVisibility(true);
		boscoHeading.setSubheadings(boscoSubHeadingList);
		return boscoHeading;
	}

	private BoscoSubHeadings getBoscoSubHeadings(ParametroTrasf subHeading) {
		BoscoSubHeadings boscoSubHeading = new BoscoSubHeadings();

		boscoSubHeading.setId(subHeading.getIdParametroTrasf());
		boscoSubHeading.setName(subHeading.getDescParametroTrasf());
		boscoSubHeading.setVisibility(true);
		boscoSubHeading.setValore(subHeading.getValore());
		boscoSubHeading.setChecked(false);
		return boscoSubHeading;
	}

	private void preselectTheRightOptions(Integer idIntervento, PlainTerzaSezione plainTersaSezione)
			throws RecordNotUniqueException {
		List<ParamtrasfTrasformazion> paramtrasfs = paramtrasfTrasformazionDAO
				.getParamtrasfTrasformazionsByIdIntervento(idIntervento);
		if (!paramtrasfs.isEmpty()) {
			preselectOptionsFromDatabase(idIntervento, plainTersaSezione.getHeadings(), paramtrasfs);
		}

		int locationSubHeadingId = getLocationSubHeadingId(idIntervento);
		for (BoscoHeadings heading : plainTersaSezione.getHeadings()) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.B.toString())) {
					setCategoryForestOption(idIntervento, subHeading);
				}
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.C.toString())
						&& subHeading.getId() == locationSubHeadingId) {
					subHeading.setChecked(true);
				}
			}
			setDestinazioniOption(idIntervento, heading);
		}
	}

	private void preselectOptionsFromDatabase(Integer idIntervento, List<BoscoHeadings> headings,
			List<ParamtrasfTrasformazion> paramtrasfs) {
		for (BoscoHeadings heading : headings) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (paramtrasfs.contains(new ParamtrasfTrasformazion(subHeading.getId(), idIntervento))) {
					subHeading.setChecked(true);
				}
			}
		}
	}

	private String preselectedOptionsWarning(int idIntervento, List<BoscoHeadings> headings)
			throws RecordNotUniqueException {

		int locationSubHeadingId = getLocationSubHeadingId(idIntervento);

		StringBuilder sb = new StringBuilder();

		for (BoscoHeadings heading : headings) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.B.toString())
						&& !isSetCategoryForestOption(idIntervento, subHeading)) {
					sb.append("CATEGORIA FORESTALE - preselezione del sistema modificata;\r\n");
				}
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.C.toString())
						&& subHeading.getId() == locationSubHeadingId && !subHeading.isChecked()) {
					sb.append("UBICAZIONE - preselezione del sistema modificata;\r\n");
				}
			}
			if (!isSetDestinazioniOption(idIntervento, heading)) {
				sb.append("DESTINAZIONI, FUNZIONI, VINCOLI - preselezione del sistema modificata;");
			}
		}

		if (sb.toString().length() != 0) {
			return sb.toString();
		}
		return null;
	}

	private BigDecimal getBaseCalculation(int idIntervento) {
		ParametroAppl parametroAppl = parametroApplDAO.getParamertroByTipo(TipoParametroApplEnum.BASE_CALCOLO_EURO);
		return BigDecimal.valueOf((long) parametroAppl.getParametroApplNum()).multiply(BigDecimal
				.valueOf((long) interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata()));
	}

	private List<BoscoSubHeadings> getSubHeadingsfWithCheckedValue(BoscoHeadings heading) {
		List<BoscoSubHeadings> subHeadings = new ArrayList<>();
		for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
			if (subHeading.isChecked()) {
				subHeadings.add(subHeading);
			}
		}
		return subHeadings;
	}

	private BoscoSubHeadings getFilteredCheckedSubHeadings(BoscoHeadings heading) {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())
				|| heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.E.toString())) {
			BigDecimal maxValue = BigDecimal.ZERO;
			BoscoSubHeadings responseSubHeading = null;
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (subHeading.isChecked() && maxValue.compareTo(subHeading.getValore()) < 0) {
					maxValue = subHeading.getValore();
					responseSubHeading = subHeading;
				}
			}
			return responseSubHeading;
		} else {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (subHeading.isChecked()) {
					return subHeading;
				}
			}
		}
		return null;
	}

	private void executeParamtrasfTrasformazion(List<ParamtrasfTrasformazion> paramtrasfTrasformazions,
			List<ParamtrasfTrasformazion> checkedParamtrasfs) {
		List<ParamtrasfTrasformazion> paramsfToDelete = new ArrayList<>();
		if (paramtrasfTrasformazions.isEmpty()) {
			for (ParamtrasfTrasformazion checkedParam : checkedParamtrasfs) {
				paramtrasfTrasformazionDAO.insert(checkedParam);
			}
		} else {
			for (ParamtrasfTrasformazion checkedParam : checkedParamtrasfs) {
				if (!paramtrasfTrasformazions.contains(checkedParam)) {
					paramtrasfTrasformazionDAO.insert(checkedParam);
				}
			}
			for (ParamtrasfTrasformazion paramtrasfTrasformazion : paramtrasfTrasformazions) {
				if (!checkedParamtrasfs.contains(paramtrasfTrasformazion)) {
					paramsfToDelete.add(paramtrasfTrasformazion);
				}
			}
		}
		removeParamtrasfTrasformazionsFromDB(paramsfToDelete);
	}

	private int getLocationSubHeadingId(Integer idIntervento) {
		List<String[]> sezioniAndFogli = new ArrayList<>();
		List<PropCatasto> propCatastos = propCatastoDAO.getPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento));
		for (PropCatasto propCatasto : propCatastos) {
			String[] sezFog = new String[2];
			sezFog[0] = propCatasto.getSezione();
			sezFog[1] = propCatasto.getFoglio().toString();
			sezioniAndFogli.add(sezFog);
		}
		return zonaAltimetricaDAO.getMaxOccurencesOfFkParamtrasf(sezioniAndFogli);
	}

	private void setCategoryForestOption(Integer idIntervento, BoscoSubHeadings subHeading) {
		int intersection = supForestaleDAO.getTipoForestaleIntersectPropCatasto(idIntervento);
		if (intersection != -1) {
			ParametroTrasf parametroTrasf = parametroTrasfDAO
					.getParametroTrasfById(macroCatforDAO.getMacroCatforById(categoriaForestaleDAO
							.getCategoriaForestaleById(
									tipoForestaleDAO.getTipoForestaleById(intersection).getFkCategoriaForestale())
							.getFkParamMacroCatfor()).getIdParamMacroCatfor());

			if (subHeading.getId().equals(parametroTrasf.getIdParametroTrasf())) {
				subHeading.setChecked(true);
			}
		}
	}

	private void setDestinazioniOption(Integer idIntervento, BoscoHeadings heading) throws RecordNotUniqueException {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())) {
			// First item is always checked
			heading.getSubheadings().get(0).setChecked(true);
			IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
			if (intervTrasf != null && (intervTrasf.getFlgVincIdro() == 1)) {
				heading.getSubheadings().get(1).setChecked(true);
			}

			if (!interventoAappDAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoRn2000DAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoPopSemeDAO.getInterventosByIdIntervento(idIntervento).isEmpty()) {
				heading.getSubheadings().get(2).setChecked(true);
			}
		}
	}

	private boolean isSetCategoryForestOption(Integer idIntervento, BoscoSubHeadings subHeading) {
		int intersection = supForestaleDAO.getTipoForestaleIntersectPropCatasto(idIntervento);

		if (intersection != -1) {
			ParametroTrasf parametroTrasf = parametroTrasfDAO
					.getParametroTrasfById(macroCatforDAO.getMacroCatforById(categoriaForestaleDAO
							.getCategoriaForestaleById(
									tipoForestaleDAO.getTipoForestaleById(intersection).getFkCategoriaForestale())
							.getFkParamMacroCatfor()).getIdParamMacroCatfor());

			return !(subHeading.getId().equals(parametroTrasf.getIdParametroTrasf()) && !subHeading.isChecked());
		} else {
			return true;
		}
	}

	private boolean isSetDestinazioniOption(Integer idIntervento, BoscoHeadings heading)
			throws RecordNotUniqueException {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())) {
			// First item is always checked
			if (!heading.getSubheadings().get(0).isChecked()) {
				throw new IllegalArgumentException(
						"'Nessun vincolo oltre a quello paesaggistico' option must be selected");
			}
			IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
			if (intervTrasf != null
					&& (intervTrasf.getFlgVincIdro() == 1 && !heading.getSubheadings().get(1).isChecked())) {
				return false;
			}

			if ((!interventoAappDAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoRn2000DAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoPopSemeDAO.getInterventosByIdIntervento(idIntervento).isEmpty())
					&& !heading.getSubheadings().get(2).isChecked()) {
				return false;
			}
		}
		return true;
	}

	private void setFlgArts(Integer idIntervento, IntervTrasformazione intervTrasf,
			CompensationForesteDTO compensationForesteDTO) {
		if (intervTrasf.getFlgArt7A() == 1
				|| interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata() < 500) {
			compensationForesteDTO.setFlgA(true);
		}
		if (intervTrasf.getFlgArt7B() == 1) {
			compensationForesteDTO.setFlgB(true);
		}
		if (intervTrasf.getFlgArt7B() == 1) {
			compensationForesteDTO.setFlgB(true);
		}
		if (intervTrasf.getFlgArt7C() == 1) {
			compensationForesteDTO.setFlgC(true);
		}
		if (intervTrasf.getFlgArt7D() == 1) {
			compensationForesteDTO.setFlgD(true);
		}
	}

	private void removeParamtrasfTrasformazionsFromDB(List<ParamtrasfTrasformazion> paramsfToDelete) {
		if (!paramsfToDelete.isEmpty()) {
			for (ParamtrasfTrasformazion paramtrasfTrasformazion : paramsfToDelete) {
				paramtrasfTrasformazionDAO.deleteByIdInterventoAndIdParametroTrasf(
						paramtrasfTrasformazion.getIdIntervento(), paramtrasfTrasformazion.getIdParametroTrasf());
			}
		}
	}

	private ParamtrasfTrasformazion mapSubheadingToParatrasf(Integer idIntervento, Integer fkConfigUtente,
			int idSubheading) {
		ParamtrasfTrasformazion paramtrasfTrasformazion = new ParamtrasfTrasformazion();

		paramtrasfTrasformazion.setIdParameroTrasf(idSubheading);
		paramtrasfTrasformazion.setIdIntervento(idIntervento);
		paramtrasfTrasformazion.setFkConfigUtente(fkConfigUtente);
		paramtrasfTrasformazion.setDataInserimento(LocalDate.now());

		return paramtrasfTrasformazion;
	}

	private boolean areTwoSoggettosTheSame(TSoggetto soggetto, TSoggetto soggettoFromDB) {
		return Objects.equals(soggetto.getCodiceFiscale(), soggettoFromDB.getCodiceFiscale())
				&& Objects.equals(soggetto.getPartitaIva(), soggettoFromDB.getPartitaIva())
				&& Objects.equals(soggetto.getCognome(), soggettoFromDB.getCognome())
				&& Objects.equals(soggetto.getNome(), soggettoFromDB.getNome())
				&& Objects.equals(soggetto.getFkComune(), soggettoFromDB.getFkComune())
				&& Objects.equals(soggetto.getIndirizzo(), soggettoFromDB.getIndirizzo())
				&& Objects.equals(soggetto.getCivico(), soggettoFromDB.getCivico())
				&& Objects.equals(soggetto.getCap(), soggettoFromDB.getCap())
				&& Objects.equals(soggetto.getNrTelefonico(), soggettoFromDB.getNrTelefonico())
				&& Objects.equals(soggetto.geteMail(), soggettoFromDB.geteMail())
				&& Objects.equals(soggetto.getPec(), soggettoFromDB.getPec());
	}

	private List<SceltiParameter> filterSceltiParametri(List<SceltiParameter> sceltiParametri) {
		List<SceltiParameter> sceltiResponse = new ArrayList<>();
		for (int i = 0; i < sceltiParametri.size(); i++) {
			boolean moreThanOne = false;
			for (int j = i + 1; j < sceltiParametri.size(); j++) {
				if (sceltiParametri.get(i).getNome().equals(sceltiParametri.get(j).getNome())) {
					moreThanOne = true;
				}
			}
			if (!moreThanOne) {
				sceltiResponse.add(sceltiParametri.get(i));
			}
		}
		return sceltiResponse;
	}

	private TSoggetto getFkSoggettoProfess(Integer idIntervento) throws RecordNotFoundException {
		int fkSoggettoProfess = interventoDAO.findInterventoByIdIntervento(idIntervento).getFkSoggettoProfess();
		if (fkSoggettoProfess != 0) {
			return removeAdressInfo(soggettoDAO.findSoggettoById(fkSoggettoProfess));
		}
		return null;
	}

	private TSoggetto removeAdressInfo(TSoggetto soggetto) {
		TSoggetto response = new TSoggetto();
		response.setCodiceFiscale(soggetto.getCodiceFiscale());
		response.setPartitaIva(soggetto.getPartitaIva());
		response.setCognome(soggetto.getCognome());
		response.setNome(soggetto.getNome());
		response.setNrTelefonico(soggetto.getNrTelefonico());
		response.seteMail(soggetto.geteMail());
		response.setPec(soggetto.getPec());
		return response;
	}

	private void validateQuintaSezione(TSoggetto soggetto) {

		if (soggetto == null || soggetto.getCodiceFiscale() == null || soggetto.getPartitaIva() == null
				|| soggetto.getCognome() == null || soggetto.getNome() == null || soggetto.getFkComune() == null
				|| soggetto.getFkComune() < 1 || soggetto.getIndirizzo() == null || soggetto.getCivico() == null
				|| soggetto.getCap() == null || soggetto.getNrTelefonico() == null || soggetto.geteMail() == null
				|| soggetto.getPec() == null) {
			throw new IllegalArgumentException();
		}

		if (soggetto.getCodiceFiscale().length() > 16 || soggetto.getPartitaIva().length() > 11
				|| soggetto.getCognome().length() > 100 || soggetto.getNome().length() > 50
				|| soggetto.getIndirizzo().length() > 50 || soggetto.getCivico().length() > 10
				|| soggetto.getCap().length() != 5 || soggetto.getNrTelefonico().length() > 20
				|| soggetto.geteMail().length() > 50 || soggetto.getPec().length() > 50) {
			throw new IllegalArgumentException("Invalid length!");
		}

	}

	private DichProprieta retrieveDichProprieta(ParametroAppl parametroAppl1, ParametroAppl parametroAppl2,
			Byte flgProprieta) {
		DichProprieta dichProprieta = new DichProprieta();
		dichProprieta.setEtichetta1(parametroAppl1.getParametroApplChar());
		dichProprieta.setEtichetta2(parametroAppl2.getParametroApplChar());
		dichProprieta.setOwner(flgProprieta == 1);
		return dichProprieta;
	}

	private DichDissensi retrieveDichDissensi(ParametroAppl parametroAppl, Byte flgDissensi) {
		DichDissensi dichDissensi = new DichDissensi();
		dichDissensi.setEtichetta(parametroAppl.getParametroApplChar());
		dichDissensi.setHaDissenso(flgDissensi == 1);
		return dichDissensi;
	}

	private DichAutorizzazione retrieveDichPaesaggistica(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(true);
		dichAutorizzazione.setChecked(true);
		dichAutorizzazione.setRequired(true);
		if (intervTrasformazione.getFlgAutPaesaggistica() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutPaesaggistica());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutPaesaggistica());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutPaesaggistica());
		}
		return dichAutorizzazione;
	}

	private DichAutorizzazione retrieveDichVincIdrogeologico(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(intervTrasformazione.getFlgVincIdro() == 1);
		dichAutorizzazione.setChecked(intervTrasformazione.getFlgVincIdro() == 1);
		dichAutorizzazione.setRequired(false);
		if (intervTrasformazione.getFlgVincIdro() == 1 && intervTrasformazione.getFlgAutVidro() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutVidro());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutVidro());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutVidro());
		}
		return dichAutorizzazione;
	}

	private DichAutorizzazione retrieveDichValIncidenza(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione, List<InterventoRn2000> interventos) {
		boolean isInterventos2000Empty = interventos.isEmpty();
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(!isInterventos2000Empty);
		dichAutorizzazione.setChecked(!isInterventos2000Empty);
		dichAutorizzazione.setRequired(false);
		if (!isInterventos2000Empty && intervTrasformazione.getFlgAutIncidenza() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutIncidenza());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutIncidenza());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutIncidenza());
		}
		return dichAutorizzazione;
	}

	private DichAltriPareri retrieveDichAltriPareri(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		DichAltriPareri dichAltriPareri = new DichAltriPareri();
		dichAltriPareri.setEtichetta(parametroAppl.getParametroApplChar());
		dichAltriPareri.setChecked(intervTrasformazione.getAltriPareri() != null);
		if (intervTrasformazione.getAltriPareri() != null) {
			dichAltriPareri.setTesto(intervTrasformazione.getAltriPareri());
		}
		return dichAltriPareri;
	}

	private DichCompensazione retrieveDichCompFisica(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		boolean required = SuperficieCompensationEnum.F.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setEtichetta(parametroAppl.getParametroApplChar().replace("{$DEPOSIT$}",
				intervTrasformazione.getCompenzazioneEuro().toString()));
		dichCompensazione.setVisible(required);
		dichCompensazione.setChecked(required);
		dichCompensazione.setRequired(required);
		return dichCompensazione;
	}

	private DichCompensazione retrieveDichCompMonetaria(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		boolean isChecked = SuperficieCompensationEnum.M.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setEtichetta(parametroAppl.getParametroApplChar().replace("{$DEPOSIT$}",
				intervTrasformazione.getCompenzazioneEuro().toString()));
		dichCompensazione.setVisible(isChecked);
		dichCompensazione.setChecked(isChecked);
		dichCompensazione.setRequired(isChecked);
		return dichCompensazione;
	}

	private DichCompensazione retrieveDichAltroAllega() {
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setVisible(true);
		dichCompensazione.setChecked(false);
		dichCompensazione.setRequired(false);
		return dichCompensazione;
	}

	private DichIstanzaTaglio retrieveDichIstanzi(ParametroAppl parametroAppl, int idIntervento,
			IntervTrasformazione intervTrasformazione) {
		boolean required = SuperficieCompensationEnum.F.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichIstanzaTaglio dichIstanzaTaglio = new DichIstanzaTaglio();
		dichIstanzaTaglio.setEtichetta(parametroAppl.getParametroApplChar());
		dichIstanzaTaglio.setVisible(required);
		dichIstanzaTaglio.setRequired(required);
		dichIstanzaTaglio.setChecked(required);
		List<IstanzaCompensazione> istanzas = istanzaCompensazioneDAO.getAllByFkIntervento(idIntervento);
		if (required) {
			dichIstanzaTaglio.setIstanziList(mapToIstanziTaglioList(istanzas));
		} else {
			dichIstanzaTaglio.setIstanziList(null);
		}

		return dichIstanzaTaglio;
	}

	private List<IstanzaTaglio> mapToIstanziTaglioList(List<IstanzaCompensazione> istanzas) {
		List<IstanzaTaglio> istanziTaglio = new ArrayList<>();
		for (IstanzaCompensazione istanzaComp : istanzas) {
			IstanzaTaglio istTaglio = new IstanzaTaglio();
			istTaglio.setIdIstanza(istanzaComp.getNumIstanzaCompensazione());
			istTaglio.setNumIstanza(istanzaComp.getNumIstanzaCompensazione());
			// TODO set the rest properties

			istanziTaglio.add(istTaglio);
		}
		return istanziTaglio;
	}

	private DichNota retrieveDichNota(IntervTrasformazione intervTrasformazione) {
		DichNota dichNota = new DichNota();
		dichNota.setEtichetta(
				"Annotazioni/dichiarazioni/chiarimenti relativi alle dichiarazioni riportate, da sottoporre a chi valutera la domanda");
		dichNota.setTesto(intervTrasformazione.getNoteDichiarazione());
		return dichNota;
	}

	private void sestoSezioneValidation(PlainSestoSezione plainSestoSezione) throws ValidationException {

		validateAutPaesaggistica(plainSestoSezione);

		validateAutVidro(plainSestoSezione);

		validateVidroIncidenza(plainSestoSezione);

		if (plainSestoSezione.getAltriPareri().isChecked()
				&& plainSestoSezione.getAltriPareri().getTesto().length() > 1000) {
			throw new ValidationException();
		}

		validateCompensazione(plainSestoSezione);

		for (FatDocumentoAllegato documento : plainSestoSezione.getAllegati()) {
			if (documento.getIdDocumentoAllegato() == null || documento.getIdDocumentoAllegato().equals(0)) {
				throw new ValidationException();
			}
		}

		for (IstanzaTaglio istanzaTaglio : plainSestoSezione.getIstanzi().getIstanziList()) {
			if (istanzaTaglio.getIdIstanza() == null || istanzaTaglio.getIdIstanza().equals(0)) {
				throw new ValidationException();
			}
		}

		if (plainSestoSezione.getNota() == null || plainSestoSezione.getNota().getTesto().length() > 1000) {
			throw new ValidationException();
		}
	}

	private void validateAutPaesaggistica(PlainSestoSezione plainSestoSezione) throws ValidationException {
		boolean flgAutPaesaggistica = plainSestoSezione.getPaesaggistica().isChecked();
		if (flgAutPaesaggistica) {
			if (plainSestoSezione.getPaesaggistica().getDataAutorizzazione() == null
					|| plainSestoSezione.getPaesaggistica().getNumeroAutorizzazione() == null
					|| plainSestoSezione.getPaesaggistica().getNumeroAutorizzazione().length() > 50
					|| plainSestoSezione.getPaesaggistica().getRilasciataAutorizzazione() == null
					|| plainSestoSezione.getPaesaggistica().getRilasciataAutorizzazione().length() > 200) {
				throw new ValidationException();
			}
		} else {
			throw new ValidationException();
		}
	}

	private void validateAutVidro(PlainSestoSezione plainSestoSezione) throws ValidationException {
		boolean flgAutVidro = plainSestoSezione.getVincIdrogeologico().isChecked();
		if (flgAutVidro && (plainSestoSezione.getVincIdrogeologico().getDataAutorizzazione() == null
				|| plainSestoSezione.getVincIdrogeologico().getNumeroAutorizzazione() == null
				|| plainSestoSezione.getVincIdrogeologico().getNumeroAutorizzazione().length() > 50
				|| plainSestoSezione.getVincIdrogeologico().getRilasciataAutorizzazione() == null
				|| plainSestoSezione.getVincIdrogeologico().getRilasciataAutorizzazione().length() > 200)) {
			throw new ValidationException();
		}
	}

	private void validateVidroIncidenza(PlainSestoSezione plainSestoSezione) throws ValidationException {
		boolean flgAutVidroIncidenza = plainSestoSezione.getValIncidenza().isChecked();
		if (flgAutVidroIncidenza && (plainSestoSezione.getValIncidenza().getDataAutorizzazione() == null
				|| plainSestoSezione.getValIncidenza().getNumeroAutorizzazione() == null
				|| plainSestoSezione.getValIncidenza().getNumeroAutorizzazione().length() > 50
				|| plainSestoSezione.getValIncidenza().getRilasciataAutorizzazione() == null
				|| plainSestoSezione.getValIncidenza().getRilasciataAutorizzazione().length() > 200)) {
			throw new ValidationException();
		}
	}

	private void validateCompensazione(PlainSestoSezione plainSestoSezione) throws ValidationException {
		if ((plainSestoSezione.getCompFisica().isChecked() && plainSestoSezione.getCompMonetaria().isChecked())
				|| (plainSestoSezione.getCompFisica().isChecked() && !plainSestoSezione.getIstanzi().isChecked())
				|| (plainSestoSezione.getCompMonetaria().isChecked() && plainSestoSezione.getIstanzi().isChecked())) {
			throw new ValidationException();
		}
	}

	private IntervTrasformazione retrieveIntervTrasformazioneForDich(PlainSestoSezione plainSestoSezione) {
		IntervTrasformazione intervTrasformazione = new IntervTrasformazione();

		intervTrasformazione.setFlgProprieta(plainSestoSezione.getProprieta().isOwner() ? (byte) 1 : (byte) 0);
		intervTrasformazione.setFlgDissensi(plainSestoSezione.getDissensi().haDissenso() ? (byte) 1 : (byte) 0);

		byte flgAutPaesaggistica = plainSestoSezione.getPaesaggistica().isChecked() ? (byte) 1 : (byte) 0;
		if (flgAutPaesaggistica == 1) {
			intervTrasformazione.setFlgAutPaesaggistica(flgAutPaesaggistica);
			intervTrasformazione.setDataAutPaesaggistica(plainSestoSezione.getPaesaggistica().getDataAutorizzazione());
			intervTrasformazione.setNrAutPaesaggistica(plainSestoSezione.getPaesaggistica().getNumeroAutorizzazione());
			intervTrasformazione
					.setEnteAutPaesaggistica(plainSestoSezione.getPaesaggistica().getRilasciataAutorizzazione());
		} else {
			intervTrasformazione.setFlgAutPaesaggistica((byte) 0);
			intervTrasformazione.setDataAutPaesaggistica(null);
			intervTrasformazione.setNrAutPaesaggistica(null);
			intervTrasformazione.setEnteAutPaesaggistica(null);
		}

		byte flgAutVidro = plainSestoSezione.getVincIdrogeologico().isChecked() ? (byte) 1 : (byte) 0;
		if (flgAutVidro == 1) {
			intervTrasformazione.setFlgAutVidro(flgAutVidro);
			intervTrasformazione.setDataAutVidro(plainSestoSezione.getVincIdrogeologico().getDataAutorizzazione());
			intervTrasformazione.setNrAutVidro(plainSestoSezione.getVincIdrogeologico().getNumeroAutorizzazione());
			intervTrasformazione
					.setEnteAutVidro(plainSestoSezione.getVincIdrogeologico().getRilasciataAutorizzazione());
		} else {
			intervTrasformazione.setFlgAutVidro((byte) 0);
			intervTrasformazione.setDataAutVidro(null);
			intervTrasformazione.setNrAutVidro(null);
			intervTrasformazione.setEnteAutVidro(null);
		}

		byte flgAutVidroIncidenza = plainSestoSezione.getValIncidenza().isChecked() ? (byte) 1 : (byte) 0;
		if (flgAutVidroIncidenza == 1) {
			intervTrasformazione.setFlgAutIncidenza(flgAutVidroIncidenza);
			intervTrasformazione.setDataAutIncidenza(plainSestoSezione.getValIncidenza().getDataAutorizzazione());
			intervTrasformazione.setNrAutIncidenza(plainSestoSezione.getValIncidenza().getNumeroAutorizzazione());
			intervTrasformazione.setEnteAutIncidenza(plainSestoSezione.getValIncidenza().getRilasciataAutorizzazione());
		} else {
			intervTrasformazione.setFlgAutIncidenza((byte) 0);
			intervTrasformazione.setDataAutIncidenza(null);
			intervTrasformazione.setNrAutIncidenza(null);
			intervTrasformazione.setEnteAutIncidenza(null);
		}

		if (plainSestoSezione.getAltriPareri().isChecked()) {
			intervTrasformazione.setAltriPareri(plainSestoSezione.getAltriPareri().getTesto());
		} else {
			intervTrasformazione.setAltriPareri(null);
		}

		intervTrasformazione.setFlgCauzioneCf(plainSestoSezione.getCompFisica().isChecked() ? (byte) 1 : (byte) 0);
		intervTrasformazione.setFlgVersamentoCm(plainSestoSezione.getCompMonetaria().isChecked() ? (byte) 1 : (byte) 0);
		intervTrasformazione.setNoteDichiarazione(plainSestoSezione.getNota().getTesto());

		return intervTrasformazione;
	}

	private void documentoAllegatiManipulation(List<FatDocumentoAllegato> documenti, int fkConfigUtente,
			Integer idIntervento, List<TipoAllegatoEnum> tipoAllegati) {

		List<FatDocumentoAllegato> allDocumenti = documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento,
				tipoAllegati);

		for (FatDocumentoAllegato documento : documenti) {

			if (documento.isDeleted() && allDocumenti.contains(documento)) {
				documentoAllegatoDAO.deleteDocumentoAllegatoByID(documento.getIdDocumentoAllegato());

			} else if (!allDocumenti.contains(documento)) {

				documento.setIdGeoPlPfa(PlPfaEnum.FONTE_FINAZ.getValue());
				documento.setDataIniziValidita(LocalDate.now());
				documento.setFkConfigUtente(fkConfigUtente);
				int a = documentoAllegatoDAO.createDocumentoAllegato(documento);
				documento.setIdDocumentoAllegato(a);
			}
		}
	}

	private void istanzaComensazioneManipulation(DichIstanzaTaglio istanzi, int fkConfigUtente, int fkIntervento) {
		List<IstanzaTaglio> allIstanzi = mapToIstanziTaglioList(
				istanzaCompensazioneDAO.getAllByFkIntervento(fkIntervento));

		if (istanzi.isChecked()) {
			for (IstanzaTaglio istanzaTaglio : istanzi.getIstanziList()) {
				if (istanzaTaglio.isDeleted() && allIstanzi.contains(istanzaTaglio)) {
					istanzaCompensazioneDAO.deleteIstanza(istanzaTaglio.getIdIstanza());
				} else if (!allIstanzi.contains(istanzaTaglio)) {
					istanzaCompensazioneDAO.insertIstanzaCompensazione(
							mapToIstanzaCompensazione(istanzaTaglio, fkConfigUtente, fkIntervento));
				}
			}
		}
	}

	private IstanzaCompensazione mapToIstanzaCompensazione(IstanzaTaglio istanzaTaglio, int fkConfigUtente,
			int fkIntervento) {
		IstanzaCompensazione istanzaCompensazione = new IstanzaCompensazione();
		istanzaCompensazione.setNumIstanzaCompensazione(istanzaTaglio.getIdIstanza());
		istanzaCompensazione.setDataInserimento(LocalDate.now());
		istanzaCompensazione.setFkConfigUtente(fkConfigUtente);
		istanzaCompensazione.setFkIntervento(fkIntervento);
		istanzaCompensazione.setDataPresentazione(istanzaTaglio.getDataPresentazioneIstanza());
		istanzaCompensazione.setDataAutorizzazione(istanzaTaglio.getDataAutorizzazioneIstanza());
		return istanzaCompensazione;
	}

	private void validateSearchParameters(Map<String, String> queryParams, ConfigUtente configUtente,
			TSoggetto soggetto) throws ValidationException, RecordNotUniqueException {
		validateSearchNullParams(queryParams);

		String annoIstanza = queryParams.get("annoIstanza");
		if (annoIstanza != null && annoIstanza.length() != 4 && !ValidationUtil.isNumber(annoIstanza)) {
			throw new ValidationException();
		}
		validateSearchDates(queryParams);
		validateSearchComuneAndProvincia(queryParams.get("prov"), queryParams.get("comune"), configUtente, soggetto);
		validateCalcoloEuro(queryParams.get("calcEconDa"), queryParams.get("calcEconA"));

	}

	private void validateSearchNullParams(Map<String, String> queryParams) throws ValidationException {
		boolean allNull = true;
		for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			if (entry.getValue() == null) {
				allNull = false;
			}
		}
		if (allNull) {
			throw new ValidationException();
		}
	}

	private void validateSearchDates(Map<String, String> queryParams) throws ValidationException {
		String dataDaString = queryParams.get("dataPresDa");
		String dataAString = queryParams.get("dataPresA");
		if (dataDaString != null && dataAString != null) {
			LocalDate dataDa = checkAndReturnDate(dataDaString);
			LocalDate dataA = checkAndReturnDate(dataAString);
			if (dataDa.isAfter(dataA)) {
				throw new ValidationException();
			}
		} else if (dataDaString != null) {
			checkAndReturnDate(dataDaString);
		} else if (dataAString != null) {
			checkAndReturnDate(dataAString);
		}
	}

	private LocalDate checkAndReturnDate(String date) throws ValidationException {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		try {
			return LocalDate.parse(date);
		} catch (DateTimeParseException dtpe) {
			throw new ValidationException();
		}
	}

	private void validateSearchComuneAndProvincia(String provincia, String comune, ConfigUtente configUtente,
			TSoggetto soggetto) throws RecordNotUniqueException, ValidationException {
		int ufficioTerProfilo = ProfiloUtenteEnum.UFFICIO_TERRITORIALE.getValue();
		int comuneProfilo = ProfiloUtenteEnum.COMUNE.getValue();
		if (provincia != null && configUtente.getFkProfiloUtente() == ufficioTerProfilo
				&& !provincia.equals(comuneDAO.findComuneResourceByID(soggetto.getFkComune()).getIstatProv())) {
			throw new ValidationException();
		}

		if (comune != null && configUtente.getFkProfiloUtente() == comuneProfilo
				&& soggetto.getFkComune() != Integer.parseInt(comune)) {
			throw new ValidationException();
		}
	}

	private void validateCalcoloEuro(String calcDa, String calcA) throws ValidationException {
		if (calcDa != null && calcA != null) {
			if (!ValidationUtil.isBigDecimal(calcDa) || !ValidationUtil.isBigDecimal(calcA)
					|| BigDecimal.valueOf(Double.parseDouble(calcDa))
							.compareTo(BigDecimal.valueOf(Double.parseDouble(calcA))) > -1) {
				throw new ValidationException();
			}
		} else if (calcDa != null && !ValidationUtil.isBigDecimal(calcDa)) {
			throw new ValidationException();
		} else if (calcA != null && !ValidationUtil.isBigDecimal(calcA)) {
			throw new ValidationException();
		}
	}

	private IntervSelvicolturale retrieveIntervSelvicolturaleForInsert(Integer idGeoPlPfa, TSoggetto compilerSoggetto,
			int idIntervento) {
		IntervSelvicolturale intervSelvicolturale = new IntervSelvicolturale();
		intervSelvicolturale.setIdIntervento(idIntervento);
		intervSelvicolturale.setNrProgressivoInterv(intervSelvicoulturaleDAO.getProssimoNrProgInterv(1)); // mocked
																											// tipoIntervento
		intervSelvicolturale.setIdgeoPlPfa(idGeoPlPfa);
		intervSelvicolturale.setFkConfigUtente(compilerSoggetto.getFkConfigUtente());
		intervSelvicolturale.setFlgConformeDeroga(ConformeDerogaEnum.C.toString()); // predefined
		// SET NOT NULL CONSTRAINT FIELDS, ALL MOCKED
		intervSelvicolturale.setFkTipoIntervento(1); // composite
		intervSelvicolturale.setFkConfigIpla(0); // composite
		intervSelvicolturale.setFkStatoIntervento(1);
		intervSelvicolturale.setFkFinalitaTaglio(1);
		intervSelvicolturale.setFkDestLegname(1);
		intervSelvicolturale.setFkFasciaAltimetrica(1);
		intervSelvicolturale.setFlgIntervNonPrevisto((byte) 0);
		intervSelvicolturale.setFlgIstanzaTaglio((byte) 0);
		intervSelvicolturale.setFlgPiedilista((byte) 0);

		return intervSelvicolturale;
	}

	@Override
	public void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateIstanzaTo(idIntervento, InstanceStateEnum.VERIFICATA);
	}

	@Override
	public void updateIstanzaRifiutata(Integer idIntervento, TSoggetto soggetoFromUser)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateIstanzaTo(idIntervento, InstanceStateEnum.RIFIUTATA);

	}

	@Override
	public BoOwnerIstanze getUtenteForIstanza(Integer idIntervento) {
		ConfigUtente cnfUttente = istanzaForestDAO.getUtenteForIstanzaById(idIntervento);

		try {
			TSoggetto sog = soggettoDAO.findSoggettoById(cnfUttente.getFkSoggetto());
			return new BoOwnerIstanze(cnfUttente, sog);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GeneratedFileParameters getCeoIstanza(Integer idIntervento) {

		GeneratedFileParameters genFileParams = dichiarazioneSummaryDAO.getSummary(idIntervento);
		return genFileParams;
	}

	@Override
	public void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException {

		ConfigUtente confut = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());
		Integer profilutentea = confut.getFkProfiloUtente();
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		
		switch (profilutentea) {
		case 1:
			
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);
break;
		case 2:
			
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);
			break;
		case 3:
			
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);
			break;
		case 4:
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);
			break;
		case 5:
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);
			break;
		case 6:
			
			throw new IllegalArgumentException("Not allowed to proceed with this action");

		}
		

	}
}