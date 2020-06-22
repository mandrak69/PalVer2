package it.csi.idf.idfapi.util;

public enum ConformeDerogaEnum {
	
	C("Confomre"),
	D("Deroga");
	
	private String value;
	
	private ConformeDerogaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}