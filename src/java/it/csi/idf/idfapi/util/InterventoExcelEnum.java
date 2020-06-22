package it.csi.idf.idfapi.util;

public enum InterventoExcelEnum {
	
	PFA_DENOMINAZIONE("pfa_denominazione"),
	N_INTERVENTO("n_intervento"),
	ANNATA_SILVANA("annata_silvana"),
	PARTICELA_FORESTALE("particela_forestale"),
	DATA_INIZIO("data_inizio"),
	DATA_FINE("data_fine"),
	DESCRIZIONE("descrizione"),
	LOCALITA("localita"),
	SUPERCFICIE_INTERESSATA("superficie_interessata"),
	M3_PRELEVATI("M3_prelevati"),
	STATO_INTERVENTO("stato_intervento"),
	COMUNICAZIONE_DI_TAGLIO("comunicazione_di_taglio");
	
	private String value;

	private InterventoExcelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
