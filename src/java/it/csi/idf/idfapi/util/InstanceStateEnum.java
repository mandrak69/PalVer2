package it.csi.idf.idfapi.util;

public enum InstanceStateEnum {
	BOZZA(1),
	INVIATA(2),
	VERIFICATA(3),
	RIFIUTATA(4);
	
	private int value;
	
	private InstanceStateEnum(int value) {
		this.value = value;
	}
	
	public int getStateValue() {
		return value;
	}
}
