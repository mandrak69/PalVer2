package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import it.csi.idf.idfapi.config.LocalDateDefaultDeserializer;

public class IstanzaTaglio {

	private Integer idIstanza;
	private LocalDate anno;
	private Integer numIstanza;
	private LocalDate dataPresentazioneIstanza;
	private LocalDate dataAutorizzazioneIstanza;
	private String descIntervento;
	private Integer stimaMassaRetraibile;
	private String unita;
	private String tipoComunicazione;
	private String stato;
	private String governo;
	private Integer tipoIntervento;
	private Integer categoriaIntervento;
	private boolean isDeleted;
	
	public Integer getIdIstanza() {
		return idIstanza;
	}
	public void setIdIstanza(Integer idIstanza) {
		this.idIstanza = idIstanza;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getAnno() {
		return anno;
	}
	public void setAnno(LocalDate anno) {
		this.anno = anno;
	}
	public Integer getNumIstanza() {
		return numIstanza;
	}
	public void setNumIstanza(Integer numIstanza) {
		this.numIstanza = numIstanza;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataPresentazioneIstanza() {
		return dataPresentazioneIstanza;
	}
	public void setDataPresentazioneIstanza(LocalDate dataPresentazioneIstanza) {
		this.dataPresentazioneIstanza = dataPresentazioneIstanza;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataAutorizzazioneIstanza() {
		return dataAutorizzazioneIstanza;
	}
	public void setDataAutorizzazioneIstanza(LocalDate dataAutorizzazioneIstanza) {
		this.dataAutorizzazioneIstanza = dataAutorizzazioneIstanza;
	}
	public String getDescIntervento() {
		return descIntervento;
	}
	public void setDescIntervento(String descIntervento) {
		this.descIntervento = descIntervento;
	}
	public Integer getStimaMassaRetraibile() {
		return stimaMassaRetraibile;
	}
	public void setStimaMassaRetraibile(Integer stimaMassaRetraibile) {
		this.stimaMassaRetraibile = stimaMassaRetraibile;
	}
	public String getUnita() {
		return unita;
	}
	public void setUnita(String unita) {
		this.unita = unita;
	}
	public String getTipoComunicazione() {
		return tipoComunicazione;
	}
	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getGoverno() {
		return governo;
	}
	public void setGoverno(String governo) {
		this.governo = governo;
	}
	public Integer getTipoIntervento() {
		return tipoIntervento;
	}
	public void setTipoIntervento(Integer tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}
	public Integer getCategoriaIntervento() {
		return categoriaIntervento;
	}
	public void setCategoriaIntervento(Integer categoriaIntervento) {
		this.categoriaIntervento = categoriaIntervento;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numIstanza == null) ? 0 : numIstanza.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IstanzaTaglio other = (IstanzaTaglio) obj;
		if (numIstanza == null) {
			if (other.numIstanza != null)
				return false;
		} else if (!numIstanza.equals(other.numIstanza)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IstanzaTaglio [idIstanza=");
		builder.append(idIstanza);
		builder.append(", anno=");
		builder.append(anno);
		builder.append(", numIstanza=");
		builder.append(numIstanza);
		builder.append(", dataPresentazioneIstanza=");
		builder.append(dataPresentazioneIstanza);
		builder.append(", dataAutorizzazioneIstanza=");
		builder.append(dataAutorizzazioneIstanza);
		builder.append(", descIntervento=");
		builder.append(descIntervento);
		builder.append(", stimaMassaRetraibile=");
		builder.append(stimaMassaRetraibile);
		builder.append(", unita=");
		builder.append(unita);
		builder.append(", tipoComunicazione=");
		builder.append(tipoComunicazione);
		builder.append(", stato=");
		builder.append(stato);
		builder.append(", governo=");
		builder.append(governo);
		builder.append(", tipoIntervento=");
		builder.append(tipoIntervento);
		builder.append(", categoriaIntervento=");
		builder.append(categoriaIntervento);
		builder.append(", isDeleted=");
		builder.append(isDeleted);
		builder.append("]");
		return builder.toString();
	}
}
