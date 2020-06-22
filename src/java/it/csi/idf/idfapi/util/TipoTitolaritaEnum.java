package it.csi.idf.idfapi.util;

public enum TipoTitolaritaEnum {
	RF(1),
	RG(2),
	PF(3),
	PG(4);
	
	private Integer option;
	
	private TipoTitolaritaEnum(Integer option) {
		this.option = option;
	}
	
	public Integer getOption() {
		return option;
	}
	
	public static TipoTitolaritaEnum getTitolarita(int titolaritaIstanza) {
		for (TipoTitolaritaEnum titolo : TipoTitolaritaEnum.values()) { 
		    if(titolo.getOption() == titolaritaIstanza) {
		    	return titolo;
		    } 
		}
		throw new IllegalArgumentException("TitolaritaIstanzaEnum does not have provided parameter");
	}
	
	public static int getTitolaritaOption(TipoTitolaritaEnum titolarita) {
		for (TipoTitolaritaEnum titolo : TipoTitolaritaEnum.values()) { 
		    if(titolo == titolarita) {
		    	return titolo.getOption();
		    } 
		}
		throw new IllegalArgumentException("TitolaritaIstanzaEnum does not have provided enum");
	}
}
