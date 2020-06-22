package it.csi.idf.idfapi.business.be.service;

import java.io.IOException;

import it.csi.idf.idfapi.dto.GeneratedFileBean;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import net.sf.jasperreports.engine.JRException;

public interface FileGenerator {
	
	GeneratedFileBean generateDichiarazione(TipoAllegatoEnum tipoAllegato, int idIntervento) throws JRException, IOException;
}
