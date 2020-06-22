package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

/**
 * Object needed for creating dynamic content in report pdf
 * Oggetto necessario per la creazione di contenuti dinamici nel report pdf
 */
public class DichPropCatasto {
	private String denominazioneComune;
	private String denominazioneProvincia;
	private String sezione;
	private Integer foglio;
	private String particella;
	private BigDecimal supCatastaleMq;
	private BigDecimal supCartograficaHa;
	
	public String getDenominazioneComune() {
		return denominazioneComune;
	}
	public void setDenominazioneComune(String denominazioneComune) {
		this.denominazioneComune = denominazioneComune;
	}
	public String getDenominazioneProvincia() {
		return denominazioneProvincia;
	}
	public void setDenominazioneProvincia(String denominazioneProvincia) {
		this.denominazioneProvincia = denominazioneProvincia;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public Integer getFoglio() {
		return foglio;
	}
	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public BigDecimal getSupCatastaleMq() {
		return supCatastaleMq;
	}
	public void setSupCatastaleMq(BigDecimal supCatastaleMq) {
		this.supCatastaleMq = supCatastaleMq;
	}
	public BigDecimal getSupCartograficaHa() {
		return supCartograficaHa;
	}
	public void setSupCartograficaHa(BigDecimal supCartograficaHa) {
		this.supCartograficaHa = supCartograficaHa;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DichPropCatasto [denominazioneComune=");
		builder.append(denominazioneComune);
		builder.append(", denominazioneProvincia=");
		builder.append(denominazioneProvincia);
		builder.append(", sezione=");
		builder.append(sezione);
		builder.append(", foglio=");
		builder.append(foglio);
		builder.append(", particella=");
		builder.append(particella);
		builder.append(", supCatastaleMq=");
		builder.append(supCatastaleMq);
		builder.append(", supCartograficaHa=");
		builder.append(supCartograficaHa);
		builder.append("]");
		return builder.toString();
	}
}