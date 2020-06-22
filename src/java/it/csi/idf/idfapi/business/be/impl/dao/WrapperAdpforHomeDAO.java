package it.csi.idf.idfapi.business.be.impl.dao;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.GenericException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.dto.PlainBackOfficeInfo;
import it.csi.idf.idfapi.dto.UserInfo;

@Transactional(rollbackFor=Exception.class)
public interface WrapperAdpforHomeDAO {
	PlainAdpforHome getAdpforHome(UserInfo user) throws RecordNotUniqueException;

	void updateAdpforHome(UserInfo user, PlainAdpforHome body)
			throws RecordNotUniqueException, ValidationException, GenericException;

	PlainBackOfficeInfo getAdpforBackOfficeHome(UserInfo user) throws RecordNotUniqueException;
// DM
	UserInfo getHomeData(UserInfo user) throws RecordNotUniqueException;
}
