package it.csi.idf.idfapi.util;

public enum TipologiaEnum {
//	A_SUPERFICIE_NOTA("A SUPERFICIE NOTA"), 
//	RELASCOPICA_SEMPLICE("RELASCOPICA SEMPLICE"),
//	RELASCOPICA_COMPLETA("RELASCOPICA COMPLETA");
	
	TEMPORANEA("Temporanea"),
	PERMANENTE("Permanente"),
	SATELLITE("Satellite");
	
	private String value;
	private TipologiaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
