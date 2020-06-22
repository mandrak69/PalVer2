package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.ParametroAppl;
import it.csi.idf.idfapi.util.TipoParametroApplEnum;

public interface ParametroApplDAO {
	ParametroAppl getParamertroByTipo(TipoParametroApplEnum tipoParametroAppl);
	List<ParametroAppl> getParamertriByTipo(TipoParametroApplEnum tipoParametroAppl);
}
