package it.csi.idf.idfapi.dto;

import java.sql.Timestamp;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConfigUtente {

	private Integer idConfigUtente;
	private Integer fkProfiloUtente;
	private Integer fkTipoAccreditamento;
	private Integer nrAccessi;
	private Timestamp dataPrimoAccesso;
	private Timestamp dataUltimoAccesso;
	private Byte flgPrivacy;
	private Integer fkSoggetto;

	@JsonProperty("idConfigUtente")
	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}

	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}

	@JsonProperty("fkProfiloUtente")
	public Integer getFkProfiloUtente() {
		return fkProfiloUtente;
	}

	public void setFkProfiloUtente(Integer fkProfiloUtente) {
		this.fkProfiloUtente = fkProfiloUtente;
	}

	@JsonProperty("fkTipoAccreditamento")
	public Integer getFkTipoAccreditamento() {
		return fkTipoAccreditamento;
	}

	public void setFkTipoAccreditamento(Integer fkTipoAccreditamento) {
		this.fkTipoAccreditamento = fkTipoAccreditamento;
	}

	@JsonProperty("nrAccessi")
	public Integer getNrAccessi() {
		return nrAccessi;
	}

	public void setNrAccessi(Integer nrAccessi) {
		this.nrAccessi = nrAccessi;
	}

	@JsonProperty("dataPrimoAccesso")
	public Timestamp getDataPrimoAccesso() {
		return dataPrimoAccesso;
	}

	public void setDataPrimoAccesso(Timestamp dataPrimoAccesso) {
		this.dataPrimoAccesso = dataPrimoAccesso;
	}

	@JsonProperty("dataUltimoAccesso")
	public Timestamp getDataUltimoAccesso() {
		return dataUltimoAccesso;
	}

	public void setDataUltimoAccesso(Timestamp dataUltimoAccesso) {
		this.dataUltimoAccesso = dataUltimoAccesso;
	}

	@JsonProperty("flgPrivacy")
	public Byte getFlgPrivacy() {
		return flgPrivacy;
	}

	public void setFlgPrivacy(Byte flgPrivacy) {
		this.flgPrivacy = flgPrivacy;
	}

	@JsonProperty("fkSoggetto")
	public Integer getFkSoggetto() {
		return fkSoggetto;
	}

	public void setFkSoggetto(Integer fkSoggetto) {
		this.fkSoggetto = fkSoggetto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ConfigUtente configUtente = (ConfigUtente) o;
		return Objects.equals(idConfigUtente, configUtente.idConfigUtente)
				&& Objects.equals(fkProfiloUtente, configUtente.fkProfiloUtente)
				&& Objects.equals(fkTipoAccreditamento, configUtente.fkTipoAccreditamento)
				&& Objects.equals(nrAccessi, configUtente.nrAccessi)
				&& Objects.equals(dataPrimoAccesso, configUtente.dataPrimoAccesso)
				&& Objects.equals(dataUltimoAccesso, configUtente.dataUltimoAccesso)
				&& Objects.equals(flgPrivacy, configUtente.flgPrivacy)
				&& Objects.equals(fkSoggetto, configUtente.fkSoggetto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConfigUtente, fkProfiloUtente, fkTipoAccreditamento, nrAccessi, dataPrimoAccesso,
				dataUltimoAccesso, flgPrivacy, fkSoggetto);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ConfigUtente {\n");

		sb.append("    idConfigUtente: ").append(idConfigUtente).append("\n");
		sb.append("    fkProfiloUtente: ").append(fkProfiloUtente).append("\n");
		sb.append("    fkTipoAccreditamento: ").append(fkTipoAccreditamento).append("\n");
		sb.append("    nrAccessi: ").append(nrAccessi).append("\n");
		sb.append("    dataPrimoAccesso: ").append(dataPrimoAccesso).append("\n");
		sb.append("    dataUltimoAccesso: ").append(dataUltimoAccesso).append("\n");
		sb.append("    flgPrivacy: ").append(flgPrivacy).append("\n");
		sb.append("    fkSoggetto: ").append(fkSoggetto).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
