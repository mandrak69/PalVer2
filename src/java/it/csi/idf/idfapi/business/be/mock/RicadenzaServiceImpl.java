package it.csi.idf.idfapi.business.be.mock;

import java.util.ArrayList;
import java.util.List;

import org.opengis.geometry.Geometry;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

@Component
public class RicadenzaServiceImpl implements RicadenzaService {
	
	
	public RicadenzaServiceImpl() {
		RicadenzaInformazioni ricadenzaInformazioni1 = new RicadenzaInformazioni();
		ricadenzaInformazioni1.setCodiceAmministrativo("0057");
		ricadenzaInformazioni1.setNome("Pianna di Usseglio");
		ricadenzaInformazioni1.setPercentualeRicadenza(10);
		
		RicadenzaInformazioni ricadenzaInformazioni2 = new RicadenzaInformazioni();
		ricadenzaInformazioni2.setCodiceAmministrativo("0074");
		ricadenzaInformazioni2.setNome("Altoggio");
		ricadenzaInformazioni2.setPercentualeRicadenza(10);
		
		allRicadenzas.add(ricadenzaInformazioni1);
		allRicadenzas.add(ricadenzaInformazioni2);
	}
	
	private final List<RicadenzaInformazioni> allRicadenzas = new ArrayList<>();

	@Override
	public List<RicadenzaInformazioni> getPopolamentiDaSemes(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzaInformations = new ArrayList<>();
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("0057");
		ricadenzaInformazioni.setNome("Pianna di Usseglio");
		ricadenzaInformazioni.setPercentualeRicadenza(10);
		
		ricadenzaInformations.add(ricadenzaInformazioni);
		
		return ricadenzaInformations;
	}

	@Override
	public List<RicadenzaInformazioni> getCategoriesForestali(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzaInformations = new ArrayList<>();
		
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("0074");
		ricadenzaInformazioni.setNome("Altoggio");
		ricadenzaInformazioni.setPercentualeRicadenza(10);
		
		ricadenzaInformations.add(ricadenzaInformazioni);
		
		return ricadenzaInformations;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTuttiPopolamentiDaSeme() {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		return allRicadenzas;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTutteCategorieForestali() {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		return allRicadenzas;
	}
	
	
	
}
