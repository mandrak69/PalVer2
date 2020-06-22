package it.csi.idf.idfapi.business.be.impl.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.CompensationForesteDTO;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.GeneratedFileParameters;
import it.csi.idf.idfapi.dto.InvioIstanzaDTO;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainPrimaSezione;
import it.csi.idf.idfapi.dto.PlainQuintaSezione;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.PlainSestoSezione;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.PlainTerzaSezione;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TerzaSezioneSaveResult;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.PaginatedList;

@Transactional(rollbackFor=Exception.class)
public interface WrapperIstanzaDAO {

	StepNumber getNumberOfNextStep(Integer idIntervento);

	PlainPrimaSezione getPrimaSezione(int idIntervento) throws RecordNotFoundException, RecordNotUniqueException;

	PlainSezioneId savePrimaSezione(PlainPrimaSezione body, UserInfo user)
			throws RecordNotUniqueException, ValidationException;

	void updatePrimaSezione(PlainPrimaSezione body, UserInfo user, int idIntervento)
			throws ValidationException, RecordNotUniqueException;

	PlainSecondoSezione getSecondoSezione(Integer idIntervento) throws RecordNotUniqueException, ValidationException;

	void saveSecondoSezione(PlainSecondoSezione body, UserInfo user, int idIntervento) throws RecordNotUniqueException, ValidationException;

	void updateSecondoSezione(PlainSecondoSezione body, UserInfo user, Integer idIntervento)
			throws RecordNotUniqueException, ValidationException;

	PlainTerzaSezione getTerzaSezione(int idIntervento) throws RecordNotUniqueException, ValidationException;

	TerzaSezioneSaveResult saveTerzaSezione(int idIntervento, UserInfo user, PlainTerzaSezione body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;

	CompensationForesteDTO getQuartaSezione(Integer idIntervento) throws RecordNotUniqueException, ValidationException;

	void saveQuartaSezione(Integer idIntervento, CompensationForesteDTO compensationForesteDTO)
			throws ValidationException, RecordNotUniqueException;

	PlainQuintaSezione getQuintaSezione(Integer idIntervento)
			throws RecordNotUniqueException, DuplicateRecordException, RecordNotFoundException, ValidationException;

	void saveQuintaSezione(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotUniqueException, ValidationException;

	PlainSestoSezione getSestoSezione(Integer idIntervento) throws RecordNotUniqueException;

	void saveSestoSezione(Integer idIntervento, String codFiscale, PlainSestoSezione plainSestoSezione)
			throws RecordNotUniqueException, ValidationException;

	PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, String codiceFiscale)
			throws ParseException, RecordNotUniqueException, ValidationException;

	List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento);

	InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento);

	void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto tSoggetto) throws RecordNotFoundException, RecordNotUniqueException;
	
	PlainSezioneId saveLocalizzaLotto(PfaLottoLocalizza body, UserInfo user, Integer idGeoPlPfa) throws RecordNotUniqueException;

	void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException;

	void updateIstanzaRifiutata(Integer idIntervento, TSoggetto soggetoFromUser) throws RecordNotFoundException, RecordNotUniqueException;

	BoOwnerIstanze getUtenteForIstanza(Integer idIntervento);

	GeneratedFileParameters getCeoIstanza(Integer idIntervento);
	
	void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto soggetoFromUser) throws RecordNotFoundException, RecordNotUniqueException;
}
