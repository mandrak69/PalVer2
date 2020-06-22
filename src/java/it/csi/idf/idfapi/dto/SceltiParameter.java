package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class SceltiParameter {
	
	private String nome;
	private BigDecimal valore;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValore() {
		return valore;
	}
	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SceltiParameter [nome=");
		builder.append(nome);
		builder.append(", valore=");
		builder.append(valore);
		builder.append("]");
		return builder.toString();
	}
}
