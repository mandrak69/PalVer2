package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class AmbitoRilievo {
	
	private Integer idAmbito;
	private String descrAmbito;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	@JsonProperty("idAmbito")
	public Integer getIdAmbito() {
		return idAmbito;
	}
	public void setIdAmbito(Integer idAmbito) {
		this.idAmbito = idAmbito;
	}
	@JsonProperty("descrAmbito")
	public String getDescrAmbito() {
		return descrAmbito;
	}
	public void setDescrAmbito(String descrAmbito) {
		this.descrAmbito = descrAmbito;
	}
	@JsonProperty("mtdOrdinamento")
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	@JsonProperty("flgVisibile")
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
	
}
