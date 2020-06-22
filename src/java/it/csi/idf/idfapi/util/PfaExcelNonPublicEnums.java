package it.csi.idf.idfapi.util;

public enum PfaExcelNonPublicEnums {

	DENOMINAZIONE("Denominazione"),
	PROVINCIE("Provincie"),
	COMUNI("Comuni"),
	DATA_INIZIO("Data inizio"),
	DATA_FINE("Data fine"),
	PROPRIETA_NON_FORESTALE("Proprieta NonForestaleHa"),
	SUPERFICIA_PIANIFICATA_NONFORESTALE("SupPianif NonForestaleHa"),
	PROPRIETA_SILVOPAST("Proprieta SilvopastHa"),
	PROPRIETA_FORESTALE("Proprieta ForestaleHa"),
	SUPERFICIA_BOCS_GESTATTIVA("SuperfB GEstAttivaHa"),
	SUPERFICIA_PIANIFICATA_FORESTALE("SupPianif ForestaleHa"),
	SUPERFICIA_GEST_NONATTIVA_MON("SuperfB Gest NonAttivaMonHa"),
	SUPERFICIA_GEST_NONATTIVA_TOT("SuperfB Gest NonAttivaTotHa"),
	SUPERFICIA_GEST_NONATTIVAEVL("Superf Gest NonAttivaEvlHa");

	private String value;

	private PfaExcelNonPublicEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
