package it.csi.idf.idfapi.util;

public enum VincIdroEnum {
	YES((byte) 1),
	NO((byte) 0);
	
	private byte value;
	
	private VincIdroEnum(byte value) {
		this.value = value;
	}
	
	public byte getValue() {
		return value;
	}
}
