package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.PersonaFisica;

public interface PersonaFisicaDAO {
	PersonaFisica getPersonaFisica();
	
	int createPersonaFisica(PersonaFisica newPersonaFisica) throws ValidationException;
	
	public void savePersona(String codiceFiscale, PersonaFisica newPersonaFisica) throws RecordNotFoundException;
}
