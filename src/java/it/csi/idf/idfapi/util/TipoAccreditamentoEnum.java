package it.csi.idf.idfapi.util;

public enum TipoAccreditamentoEnum {
	RICHIEDENTE("RICHIEDENTE", 1), 
	PROFESSIONISTA("PROFESSIONISTA", 2),
	BACKOFFICE("BACKOFFICE",3);
	
	private String text;
	private int value;
	
	private TipoAccreditamentoEnum(String text, int value) {
		this.text = text;
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	
	public int getValue() {
		return value;
	}
}
