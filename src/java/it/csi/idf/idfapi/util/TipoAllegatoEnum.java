package it.csi.idf.idfapi.util;

public enum TipoAllegatoEnum {
	DICHIARAZIONE(1),
	DICHIARAZIONE_DIGITALE(2),
	DICHIARAZIONE_AUTOGRAFA(3),
	DOCUMENTO_IDENTITA(4),
	COMPENSAZIONE_FISICA(5),
	COMPENSAZIONE_MONETARIA(6),
	ALTRO(7),
	COMUNICAZIONE_TAGLIO(8),
	AUTORIZZAZIONE_TAGLIO(9),
	RELAZIONE_TECHINCA(10),
	PROGETTO_INTERVENTO(11),
	VERBALE_PREVENTIVO(12),
	PIEDILISTA(13);
	
	private int value;
	
	private TipoAllegatoEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
