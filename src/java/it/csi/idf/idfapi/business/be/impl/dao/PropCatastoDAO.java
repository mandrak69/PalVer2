package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.DichPropCatasto;
import it.csi.idf.idfapi.dto.PlainIntDTO;
import it.csi.idf.idfapi.dto.PlainStringDTO;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;

public interface PropCatastoDAO {
	
	List<PropCatasto> findAllCatasti();

	PropCatasto findCatastoByID(Integer idGeoPlPropCatasto) throws RecordNotFoundException;
	
	int insertPropCatasto(PropCatasto propCatasto);
	
	List<PropCatasto> getPropCatastosByPropcatastoIntervento(List<PropcatastoIntervento> propcatastoIntervento);
	List<DichPropCatasto> getDichPropCatastosByPropcatastoIntervento(List<PropcatastoIntervento> propcatastoIntervento);
	
	void deletePropCatasto(int idGeoPlPropCatasto);
	
	List<PlainStringDTO> findSezioniByComune(int fkComune);
	List<PlainIntDTO> findFogliByComune(int fkComune);
	List<PlainStringDTO> findParticelleByComune(int fkComune);

	void updateAllNoteOfOneIntervento(String annotazioni, int idIntervento);
}
