package it.csi.idf.idfapi.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Delega {
	
	public Delega() {}
	
	public Delega(String cfDelegante, Integer idConfigUtente) {
		this.cfDelegante = cfDelegante;
		this.idConfigUtente = idConfigUtente;
	}
	
	private String cfDelegante;
	private Integer idConfigUtente;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private Timestamp dataUtilizzo;
	
	public String getCfDelegante() {
		return cfDelegante;
	}
	public void setCfDelegante(String cfDelegante) {
		this.cfDelegante = cfDelegante;
	}
	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}
	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	public Timestamp getDataUtilizzo() {
		return dataUtilizzo;
	}
	public void setDataUtilizzo(Timestamp dataUtilizzo) {
		this.dataUtilizzo = dataUtilizzo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfDelegante == null) ? 0 : cfDelegante.hashCode());
		result = prime * result + ((dataFine == null) ? 0 : dataFine.hashCode());
		result = prime * result + ((dataInizio == null) ? 0 : dataInizio.hashCode());
		result = prime * result + ((idConfigUtente == null) ? 0 : idConfigUtente.hashCode());
		result = prime * result + ((dataUtilizzo == null) ? 0 : dataUtilizzo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Delega delega = (Delega) o;
		return Objects.equals(cfDelegante, delega.cfDelegante)
			&& Objects.equals(idConfigUtente, delega.idConfigUtente);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Delega {\n");

		sb.append("    cfDelegante: ").append(cfDelegante).append("\n");
		sb.append("    fkConfigUtente: ").append(idConfigUtente).append("\n");
		sb.append("    dataInizio: ").append(dataInizio).append("\n");
		sb.append("    dataFine: ").append(dataFine).append("\n");
		sb.append("    dataUtilizzo: ").append(dataUtilizzo).append("\n");

		sb.append("}");
		return sb.toString();
	}
}
