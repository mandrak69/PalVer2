package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.AreaDiSaggio;

public interface SuperficieNotaDAO {
	
	void saveSuperficie(AreaDiSaggio areaDiSaggio);
	
	int insertSuperficieDatiStazionaliOne(AreaDiSaggio areaDiSaggio); 
	
	int updateSuperficieDatiStazionaliTwo(AreaDiSaggio areaDiSaggio);
	
	void updateSuperficieDatiStazionaliOne(AreaDiSaggio areaDiSaggio);
	
	public boolean superficiaNotaExists(AreaDiSaggio areaDiSaggio);
	
}
