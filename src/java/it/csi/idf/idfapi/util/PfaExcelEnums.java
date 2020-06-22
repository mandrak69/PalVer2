package it.csi.idf.idfapi.util;

public enum PfaExcelEnums {

	DENOMINAZIONE("Denominazione"),
	PROVINCIE("Provincie"),
	COMUNI("Comuni"),
	DATA_INIZIO("Data inizio"),
	DATA_FINE("Data fine");
	
	private String value;
	
	private PfaExcelEnums(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
