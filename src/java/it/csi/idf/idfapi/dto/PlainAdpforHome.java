package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlainAdpforHome {
	private Integer tipoIstanzaId;
	private String tipoIstanzaDescr;
	private String fkTipoAccreditamento;
	private String partitaIva;
	private String pec;
	private String numeroIscrizione;
	private String provinciaOrdine;
	private String codiceFiscaleDelega;

	@JsonProperty("tipoIstanzaId")
	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}

	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}

	@JsonProperty("tipoIstanzaDescr")
	public String getTipoIstanzaDescr() {
		return tipoIstanzaDescr;
	}

	public void setTipoIstanzaDescr(String tipoIstanzaDescr) {
		this.tipoIstanzaDescr = tipoIstanzaDescr;
	}

	@JsonProperty("fkTipoAccreditamento")
	public String getFkTipoAccreditamento() {
		return fkTipoAccreditamento;
	}

	public void setFkTipoAccreditamento(String fkTipoAccreditamento) {
		this.fkTipoAccreditamento = fkTipoAccreditamento;
	}

	@JsonProperty("partitaIva")
	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	@JsonProperty("pec")
	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	@JsonProperty("numeroIscrizione")
	public String getNumeroIscrizione() {
		return numeroIscrizione;
	}

	public void setNumeroIscrizione(String numeroIscrizione) {
		this.numeroIscrizione = numeroIscrizione;
	}

	@JsonProperty("provinciaOrdine")
	public String getProvinciaOrdine() {
		return provinciaOrdine;
	}

	public void setProvinciaOrdine(String provinciaOrdine) {
		this.provinciaOrdine = provinciaOrdine;
	}

	@JsonProperty("codiceFiscaleDelega")
	public String getCodiceFiscaleDelega() {
		return codiceFiscaleDelega;
	}

	public void setCodiceFiscaleDelega(String codiceFiscaleDelega) {
		this.codiceFiscaleDelega = codiceFiscaleDelega;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlainAdpforHome plainHome = (PlainAdpforHome) o;
		return Objects.equals(tipoIstanzaId, plainHome.tipoIstanzaId)
				&& Objects.equals(tipoIstanzaDescr, plainHome.tipoIstanzaDescr)
				&& Objects.equals(fkTipoAccreditamento, plainHome.fkTipoAccreditamento)
				&& Objects.equals(partitaIva, plainHome.partitaIva) && Objects.equals(pec, plainHome.pec)
				&& Objects.equals(numeroIscrizione, plainHome.numeroIscrizione)
				&& Objects.equals(provinciaOrdine, plainHome.provinciaOrdine)
				&& Objects.equals(codiceFiscaleDelega, plainHome.codiceFiscaleDelega);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipoIstanzaId, tipoIstanzaDescr, fkTipoAccreditamento,
				partitaIva, pec, numeroIscrizione, provinciaOrdine, codiceFiscaleDelega);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlainHome {\n");
		sb.append("    tipoIstanzaId: ").append(tipoIstanzaId).append("\n");
		sb.append("    tipoIstanzaDescr: ").append(tipoIstanzaDescr).append("\n");
		sb.append("    fkTipoAccreditamento: ").append(fkTipoAccreditamento).append("\n");
		sb.append("    partitaIva: ").append(partitaIva).append("\n");
		sb.append("    pec: ").append(pec).append("\n");
		sb.append("    numeroIscrizione: ").append(numeroIscrizione).append("\n");
		sb.append("    provinciaOrdine: ").append(provinciaOrdine).append("\n");
		sb.append("    codiceFiscaleDelega: ").append(codiceFiscaleDelega).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
