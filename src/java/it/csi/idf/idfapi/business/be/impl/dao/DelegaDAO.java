package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.Delega;

public interface DelegaDAO {
	Delega getByConfigUtente(Integer idConfigUtente);
	Delega getByCfDeleganteAndConfigUtente(String cfDelegante, Integer fkConfigUtente) throws RecordNotUniqueException;
	int createDelega(Delega delega);
	void updateDataUtilizzoDelega(String cfDelegante, Integer idConfigUtente);
	List<Delega> getListByConfigUtente(Integer fkConfigUtente);
	List<Delega> getMieiDelegati(Integer fkConfigUtente);
}
