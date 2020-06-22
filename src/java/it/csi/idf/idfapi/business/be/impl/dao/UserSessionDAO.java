package it.csi.idf.idfapi.business.be.impl.dao;

import javax.servlet.http.HttpServletRequest;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;

public interface UserSessionDAO {
	UserInfo getUser(HttpServletRequest httpRequest);
	
	TSoggetto getSoggetoFromUser(UserInfo userInfo) throws RecordNotUniqueException;
	
	ConfigUtente getUtenteFromUserSoggetto(TSoggetto soggetto);
}
