package it.csi.idf.idfapi.util;

public enum EventoExcelEnum {
		
		PFA_DENOMINAZIONE("pfa_denominazione"),
		N_EVENTO("n_evento"),
		NOME_BREVE("nome_breve"),
	    DATA_EVENTO("data_evento"),
	    PARTICELA_FORESTALE("particela_forestale"),
	    TIPO_EVENTO("tipo_evento"),
	    DESCRIZIONE("descrizione"),
	    LOCALITA("localita"),
	    SUPERFICIE_INTERESSATA("superficie_interessata");
	
	private String value;

	private EventoExcelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}