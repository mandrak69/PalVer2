package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonSetter;

public class ComuneResource {
	
	private Integer idComune;
	private String istatComune;
    private String istatProv;
	private String denominazioneComune;
	
	public Integer getIdComune() {
		return idComune;
	}
	
	public void setIdComune(Integer idComune) {
		this.idComune = idComune;
	}
	
	public String getIstatComune() {
		return istatComune;
	}
	
	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}
	public String getIstatProv() {
		return istatProv;
	}
	
	public void setIstatProv(String istatProv) {
		this.istatProv = istatProv;
	}
	public String getDenominazioneComune() {
		return denominazioneComune;
	}
	
	public void setDenominazioneComune(String denominazioneComune) {
		this.denominazioneComune = denominazioneComune;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComuneResource [idComune=");
		builder.append(idComune);
		builder.append(", istatComune=");
		builder.append(istatComune);
		builder.append(", istatProv=");
		builder.append(istatProv);
		builder.append(", denominazioneComune=");
		builder.append(denominazioneComune);
		builder.append("]");
		return builder.toString();
	}
	@JsonSetter("id_comune")
	public void setId_comune(Integer idComune) {
		this.idComune = idComune;
	}
	

	@JsonSetter("istat_comune")
	public void setIstat_comune(String istatComune) {
		this.istatComune = istatComune;
	}

	@JsonSetter("istat_prov")
	public void setIstat_prov(String istatProv) {
		this.istatProv = istatProv;
	}
	

	@JsonSetter("denominazione_comune")
	public void setDenominazione_comune(String denominazioneComune) {
		this.denominazioneComune = denominazioneComune;
	}
	
}
