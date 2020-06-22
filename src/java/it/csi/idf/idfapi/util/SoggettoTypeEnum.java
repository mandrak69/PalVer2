package it.csi.idf.idfapi.util;

public enum SoggettoTypeEnum {
	DATO_NON_PRESENTE(0),
	RICHIEDENTE(1),
	UTILIZZATORE(2),
	TECNICO_FORESTALE(3);
	
	private int value;
	
	private SoggettoTypeEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
