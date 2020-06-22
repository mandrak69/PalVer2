package it.csi.idf.idfapi.util;

public enum TipoIstanzaEnum {
	DICHIARAZIONE_SOSTITUTIVA(1),
	COMUNICAZIONE(2),
	AUTORIZZAZIONE(3);
	
	private int value;
	
	private TipoIstanzaEnum(int value) {
		this.value = value;
	}
	
	public int getTypeValue() {
		return value;
	}
	
	public static TipoIstanzaEnum getEnum(int instanceTypeValue) {
		for (TipoIstanzaEnum instance : TipoIstanzaEnum.values()) { 
		    if(instance.getTypeValue() == instanceTypeValue) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("InstanceTypeEnum does not have enum with provided parameter");
	}
}
