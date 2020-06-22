package it.csi.idf.idfapi.util;

public enum RelascopicaCompletaEnum {
	
	SPECIE("Specie"),
	TIPO("Tipo"),
	DIAMETRO("Diametro"),
	ALTEZZA("Altezza"),
	INCREMENTO("Incremento");
	
	private String value;
	private RelascopicaCompletaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
