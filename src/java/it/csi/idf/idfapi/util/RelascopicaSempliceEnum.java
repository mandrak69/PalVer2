package it.csi.idf.idfapi.util;

public enum RelascopicaSempliceEnum {
	
	COMUNE("Comune"), 
	PARTICELLA("Particella"),
	UTM_EST("UTM EST"),
	UTM_NORD("UTM NORD"),
	QUOTA("Quota"),
	ESPOSIZIONE("Esposizione"),
	INCLINAZIONE("Inclinazione"),
	FATTORE_DI_NUMERAZIONE_RELASCOPICA("Fatore di numerazione relascopica"),
	CATEGORIA_FORESTALE("Categoria forestale"),
	TIPO_FORESTALE("Tipo forestale"),
	PROPRIETA("Proprieta"),
	TIPO_STRUTTURALE("Tipo strutturale"),
	SPECIE("Specie"),
	N_ALBERI_CONTATI_SEME("N. Alberi contati seme"),
	N_ALBERI_CONTATI_POLLONI("N. Alberi contati polloni"),
	NOTE("Note");
	
	private String value;
	private RelascopicaSempliceEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
