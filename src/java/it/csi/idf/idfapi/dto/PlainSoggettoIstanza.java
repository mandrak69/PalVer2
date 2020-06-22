package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class PlainSoggettoIstanza {
	
	private Integer idIntervento;
	private Integer nrIstanzaForestale;
	private LocalDate dataInvio;
	private SoggettoResource richiedente;
	private ComuneResource comune;
	private String stato;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
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
	public SoggettoResource getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(SoggettoResource richiedente) {
		this.richiedente = richiedente;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainSoggettoIstanza [idIntervento=");
		builder.append(idIntervento);
		builder.append(", nrIstanzaForestale=");
		builder.append(nrIstanzaForestale);
		builder.append(", dataInvio=");
		builder.append(dataInvio);
		builder.append(", richiedente=");
		builder.append(richiedente);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", stato=");
		builder.append(stato);
		builder.append("]");
		return builder.toString();
	}
}
