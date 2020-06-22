package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.EsboscoIntervselvDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervselEventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PartforInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UsoviabInterventoselvDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.mapper.TipoInterventoDatiTecniciMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class WrapperInterventoDAOImpl implements WrapperInterventoDAO {

	private static final String SEZIONE_1_VALID_MSG = "Sezione 1 deve essere completata";
	private static final String SEZIONE_2_VALID_MSG = "Sezione 2 deve essere completata";
	private static final String SEZIONE_3_VALID_MSG = "Sezione 3 deve essere completata";
	private static final String SEZIONE_4_VALID_MSG = "Sezione 4 deve essere completata";

	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;
	@Autowired
	private PartforInterventoDAO partforInterventoDAO;
	@Autowired
	private IntervselEventoDAO intervselEventoDAO;
	@Autowired
	private InterventoDAO interventoDao;
	@Autowired
	private UsoviabInterventoselvDAO usoviabInterventoselvDAO;
	@Autowired
	private EsboscoIntervselvDAO esboscoIntervselvDAO;
	@Autowired
	private SkOkSelvDAOImpl skOkSelvDAO;
	@Autowired
	private SoggettoDAOImpl soggettoDAO;
	@Autowired
	private TipoInterventoDatiTecniciDAOImpl tipoInterventoDatiTecniciDAOImpl;

	@Override
	public void saveSecondStep(InterventoDatiTehnici interventoDatiTehnici, UserInfo user, Integer idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkSelvDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

		interventoDao.updateIntervento(interventoDatiTehnici.getTipoIntervento(), idIntervento,
				soggetto.getFkConfigUtente());

		intervSelvicoulturaleDAO.updateIntervSelvicolturale(interventoDatiTehnici.getIntervSelvicolturale(),
				idIntervento);

		intervselEventoDAO.createIntervselEvento(interventoDatiTehnici.getTipoIntervento().getIdEventoCorelato(),
				idIntervento);

		usoviabInterventoselvDAO.createIntervselUsovib(
				interventoDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita(), idIntervento);
		esboscoIntervselvDAO.createIntervselvEsbosco(interventoDatiTehnici.getIntervSelvicolturale().getCodEsbosco(),
				idIntervento);

		skOkSelvDAO.updateFlgSez2(idIntervento);

	}

	@Override
	public void deleteIntervento(Integer idIntervento) throws RecordNotFoundException {

		partforInterventoDAO.deletePartforIntervento(idIntervento);
		intervselEventoDAO.deleteIntervselEvento(idIntervento);
		usoviabInterventoselvDAO.deleteIntervselUsovib(idIntervento);
		esboscoIntervselvDAO.deleteIntervselvEsbosco(idIntervento);
		intervSelvicoulturaleDAO.deleteIntervSelvicolturale(idIntervento);
		interventoDao.deleteIntervento(idIntervento);

	}

	@Override
	public StepNumber getNumberOfNextStep(Integer idIntervento) {
		return new StepNumber(skOkSelvDAO.sumFlgSeziones(idIntervento));
	}

}
