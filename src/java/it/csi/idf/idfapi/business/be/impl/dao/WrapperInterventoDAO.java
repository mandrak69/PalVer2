package it.csi.idf.idfapi.business.be.impl.dao;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.UserInfo;

@Transactional(rollbackFor = Exception.class)
public interface WrapperInterventoDAO {

	void saveSecondStep(InterventoDatiTehnici interventoDatiTehnici, UserInfo user, Integer idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;

	void deleteIntervento(Integer idIntervento) throws RecordNotFoundException;

	StepNumber getNumberOfNextStep(Integer idIntervento);

}
