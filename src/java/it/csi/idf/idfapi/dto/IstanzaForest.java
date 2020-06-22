package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class IstanzaForest {
	@JsonProperty("id_istanza_intervento")
	private Integer idIstanzaIntervento;
	@JsonProperty("fk_sogg_settore_regionale")
	private Integer fkSoggSettoreRegionale;
	@JsonProperty("fk_stato_istanza")
	private Integer fkStatoIstanza;
	@JsonProperty("nr_istanza_forestale")
	private Integer nrIstanzaForestale;
	@JsonProperty("data_invio")
	private LocalDate dataInvio;
	@JsonProperty("data_verifica")
	private LocalDate dataVerifica;
	@JsonProperty("data_protocollo")
	private LocalDate dataProtocollo;
	@JsonProperty("nr_protocollo")
	private String nrProtocollo;
	@JsonProperty("data_ult_agg")
	private LocalDate dataUltAgg;
	@JsonProperty("data_inserimento")
	private LocalDate dataInserimento;
	@JsonProperty("data_aggiornamento")
	private LocalDate dataAggiornamento;
	@JsonProperty("fk_config_utente")
	private Integer fkConfigUtente;
	@JsonProperty("fk_tipo_istanza")
	private Integer fkTipoIstanza;

	public Integer getIdIstanzaIntervento() {
		return idIstanzaIntervento;
	}

	public void setIdIstanzaIntervento(Integer idIstanzaIntervento) {
		this.idIstanzaIntervento = idIstanzaIntervento;
	}

	public Integer getFkSoggSettoreRegionale() {
		return fkSoggSettoreRegionale;
	}

	public void setFkSoggSettoreRegionale(Integer fkSoggSettoreRegionale) {
		this.fkSoggSettoreRegionale = fkSoggSettoreRegionale;
	}

	public Integer getFkStatoIstanza() {
		return fkStatoIstanza;
	}

	public void setFkStatoIstanza(Integer fkStatoIstanza) {
		this.fkStatoIstanza = fkStatoIstanza;
	}

	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}

	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}

	public LocalDate getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(LocalDate dataInvio) {
		this.dataInvio = dataInvio;
	}

	public LocalDate getDataVerifica() {
		return dataVerifica;
	}

	public void setDataVerifica(LocalDate dataVerifica) {
		this.dataVerifica = dataVerifica;
	}

	public LocalDate getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(LocalDate dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public String getNrProtocollo() {
		return nrProtocollo;
	}

	public void setNrProtocollo(String nrProtocollo) {
		this.nrProtocollo = nrProtocollo;
	}

	public LocalDate getDataUltAgg() {
		return dataUltAgg;
	}

	public void setDataUltAgg(LocalDate dataUltAgg) {
		this.dataUltAgg = dataUltAgg;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}

	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}

	public Integer getFkTipoIstanza() {
		return fkTipoIstanza;
	}

	public void setFkTipoIstanza(Integer fkTipoIstanza) {
		this.fkTipoIstanza = fkTipoIstanza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((dataInvio == null) ? 0 : dataInvio.hashCode());
		result = prime * result + ((dataProtocollo == null) ? 0 : dataProtocollo.hashCode());
		result = prime * result + ((dataUltAgg == null) ? 0 : dataUltAgg.hashCode());
		result = prime * result + ((dataVerifica == null) ? 0 : dataVerifica.hashCode());
		result = prime * result + ((fkConfigUtente == null) ? 0 : fkConfigUtente.hashCode());
		result = prime * result + ((fkSoggSettoreRegionale == null) ? 0 : fkSoggSettoreRegionale.hashCode());
		result = prime * result + ((fkStatoIstanza == null) ? 0 : fkStatoIstanza.hashCode());
		result = prime * result + ((fkTipoIstanza == null) ? 0 : fkTipoIstanza.hashCode());
		result = prime * result + ((idIstanzaIntervento == null) ? 0 : idIstanzaIntervento.hashCode());
		result = prime * result + ((nrIstanzaForestale == null) ? 0 : nrIstanzaForestale.hashCode());
		result = prime * result + ((nrProtocollo == null) ? 0 : nrProtocollo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		IstanzaForest istanzaForest = (IstanzaForest) obj;

		return Objects.equals(idIstanzaIntervento, istanzaForest.idIstanzaIntervento)
				&& Objects.equals(fkSoggSettoreRegionale, istanzaForest.fkSoggSettoreRegionale)
				&& Objects.equals(fkStatoIstanza, istanzaForest.fkStatoIstanza)
				&& Objects.equals(nrIstanzaForestale, istanzaForest.nrIstanzaForestale)
				&& Objects.equals(dataInvio, istanzaForest.dataInvio)
				&& Objects.equals(dataVerifica, istanzaForest.dataVerifica)
				&& Objects.equals(dataProtocollo, istanzaForest.dataProtocollo)
				&& Objects.equals(nrProtocollo, istanzaForest.nrProtocollo)
				&& Objects.equals(dataUltAgg, istanzaForest.dataUltAgg)
				&& Objects.equals(dataInserimento, istanzaForest.dataInserimento)
				&& Objects.equals(dataAggiornamento, istanzaForest.dataAggiornamento)
				&& Objects.equals(fkConfigUtente, istanzaForest.fkConfigUtente)
				&& Objects.equals(fkTipoIstanza, istanzaForest.fkTipoIstanza);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class IstanzaForest {\n");

		sb.append("    idIstanzaIntervento: ").append(idIstanzaIntervento).append("\n");
		sb.append("    fkSoggSettoreRegionale: ").append(fkSoggSettoreRegionale).append("\n");
		sb.append("    fkStatoIstanza: ").append(fkStatoIstanza).append("\n");
		sb.append("    nrIstanzaForestale: ").append(nrIstanzaForestale).append("\n");
		sb.append("    dataInvio: ").append(dataInvio).append("\n");
		sb.append("    dataVerifica: ").append(dataVerifica).append("\n");
		sb.append("    dataProtocollo: ").append(dataProtocollo).append("\n");
		sb.append("    nrProtocollo: ").append(nrProtocollo).append("\n");
		sb.append("    dataUltAgg: ").append(dataUltAgg).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("    fkTipoIstanza: ").append(fkTipoIstanza).append("\n");

		sb.append("}");
		return sb.toString();
	}
}
