package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.GenericException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAccreditamentoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.dto.PlainBackOfficeInfo;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoAccreditamento;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.Q;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

@Component
public class WrapperAdpforHomeDAOImpl implements WrapperAdpforHomeDAO {

	private static final String BACK_OFFICE_MESSAGE = "Attenzione! Non esiste nessun profilo di tipo Gestore associato alle tue credenziali. Contattare il referente regionale del Servizio.";

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Autowired
	private TipoAccreditamentoDAO tipoAccreditamentoDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private DelegaDAO delegaDAO;

	@Override
	public PlainAdpforHome getAdpforHome(UserInfo user) throws RecordNotUniqueException {

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		PlainAdpforHome plainHome = new PlainAdpforHome();
		// DM sada je ovo verovatno visak.  sogeto ne sme biti null zbog poziva na home
		if (soggetto == null) {

			plainHome.setTipoIstanzaId(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA.getTypeValue());
			plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA.toString());
			plainHome.setCodiceFiscaleDelega(user.getCodFisc());

		} else {
			ConfigUtente configUtente;
			TipoAccreditamento tipoAccreditamento;

			if (soggetto.getFkConfigUtente() != null) {
				configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());

				if (configUtente != null) {
					tipoAccreditamento = tipoAccreditamentoDAO
							.getTipoAccreditamentoById(configUtente.getFkTipoAccreditamento());
					plainHome.setFkTipoAccreditamento(tipoAccreditamento.getDescrTipoAccreditamento());
				}

				Delega delega = delegaDAO.getByConfigUtente(soggetto.getFkConfigUtente());
				if (delega != null) {
					plainHome.setCodiceFiscaleDelega(delega.getCfDelegante());
				}
			}

			plainHome.setTipoIstanzaId(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA.getTypeValue());
			plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA.toString());

			plainHome.setPartitaIva(soggetto.getPartitaIva());
			plainHome.setPec(soggetto.getPec());
			plainHome.setNumeroIscrizione(soggetto.getnIscrizioneOrdine());
			plainHome.setProvinciaOrdine(soggetto.getIstatProvIscrizioneOrdine());
		}
		return plainHome;
	}

	@Override
	public void updateAdpforHome(UserInfo user, PlainAdpforHome body)
			throws RecordNotUniqueException, ValidationException, GenericException {

		plainHomeValidation(body);
		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

		if (TipoAccreditamentoEnum.PROFESSIONISTA.getText().equalsIgnoreCase(body.getFkTipoAccreditamento())) {
			soggetto.setPartitaIva(body.getPartitaIva());
			soggetto.setPec(body.getPec());
			soggetto.setIstatProvIscrizioneOrdine(body.getProvinciaOrdine());
			soggetto.setnIscrizioneOrdine(body.getNumeroIscrizione());

			soggettoDAO.updateSoggetto(soggetto);

			ConfigUtente configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());
			configUtente.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
			configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.PROFESSIONISTA.getValue());

			configUtenteDAO.updateConfigUtente(configUtente);

			TSoggetto soggettoForDelega = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscaleDelega());

			if (soggettoForDelega == null) {
				throw new GenericException("Indicated codice fiscale for delega is not present in the sistem!");
			}

			if (body.getCodiceFiscaleDelega().equals(soggetto.getCodiceFiscale())) {
				throw new GenericException("You can not be delegate of yourself");
			}

			Delega dbDelega = delegaDAO.getByCfDeleganteAndConfigUtente(body.getCodiceFiscaleDelega(),
					soggetto.getFkConfigUtente());
			if (dbDelega != null) {
				delegaDAO.updateDataUtilizzoDelega(body.getCodiceFiscaleDelega(), soggetto.getFkConfigUtente());
			} else {
				Delega delega = new Delega();
				delega.setCfDelegante(soggettoForDelega.getCodiceFiscale());
				delega.setIdConfigUtente(soggetto.getFkConfigUtente());
				delega.setDataInizio(LocalDate.now());
				delegaDAO.createDelega(delega);
			}
		} else {
			ConfigUtente configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());
			configUtente.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
			configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.RICHIEDENTE.getValue());

			configUtenteDAO.updateConfigUtente(configUtente);
		}

	}

	private void plainHomeValidation(PlainAdpforHome plainHome) throws ValidationException {
		if (TipoAccreditamentoEnum.PROFESSIONISTA.getText().equalsIgnoreCase(plainHome.getFkTipoAccreditamento())
				&& (plainHome.getPartitaIva().length() > 11 || plainHome.getPec().length() > 50
						|| plainHome.getProvinciaOrdine().length() > 3 || plainHome.getNumeroIscrizione().length() > 20
						|| plainHome.getCodiceFiscaleDelega().length() > 16)) {
			throw new ValidationException();
		}
	}

	@Override
	public PlainBackOfficeInfo getAdpforBackOfficeHome(UserInfo user) throws RecordNotUniqueException {
		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		ConfigUtente configUtente = null;
		PlainBackOfficeInfo plainBackOfficeInfo = new PlainBackOfficeInfo();
		if (soggetto.getFkConfigUtente() != null) {
			configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());
			if (configUtente == null || configUtente.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
				plainBackOfficeInfo.setAllowed(false);
				plainBackOfficeInfo.setMessage(BACK_OFFICE_MESSAGE);
			} else {
				plainBackOfficeInfo.setAllowed(true);
			}
		} else {
			plainBackOfficeInfo.setAllowed(false);
			plainBackOfficeInfo.setMessage(BACK_OFFICE_MESSAGE);
		}
		return plainBackOfficeInfo;
	}

// DM
	@Override
	public UserInfo getHomeData(UserInfo user) throws RecordNotUniqueException {
//		try {
//			StringBuilder qqq = Q.insertObject("idf_t_soggetto",user);
//		} catch (IllegalArgumentException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		TSoggetto soggetto;

		soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		if (soggetto == null) {
			
			user.setCodRuolo(null);
			user.setEnte(null);
			user.setLivAuth(null);
			user.setRuolo(null);

		}
		return user;

	}
	
	
	
}
