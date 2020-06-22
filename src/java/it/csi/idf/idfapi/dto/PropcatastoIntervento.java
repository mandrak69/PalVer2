package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class PropcatastoIntervento {
	
	private Integer idgeoPlPropCatasto;
	private Integer idIntervento;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	
	public Integer getIdgeoPlPropCatasto() {
		return idgeoPlPropCatasto;
	}
	public void setIdgeoPlPropCatasto(Integer idgeoPlPropCatasto) {
		this.idgeoPlPropCatasto = idgeoPlPropCatasto;
	}
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropcatastoIntervento [idgeoPlPropCatasto=");
		builder.append(idgeoPlPropCatasto);
		builder.append(", idIntervento=");
		builder.append(idIntervento);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append("]");
		return builder.toString();
	}
}
