package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

public class SoggettoIntervento {
	
	private Integer idIntervento;
	private Integer idSoggetto;
	private Integer idTipoSoggetto;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private Integer fkTipoTitolarita;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public Integer getIdTipoSoggetto() {
		return idTipoSoggetto;
	}
	public void setIdTipoSoggetto(Integer idTipoSoggetto) {
		this.idTipoSoggetto = idTipoSoggetto;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
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
	public Integer getFkTipoTitolarita() {
		return fkTipoTitolarita;
	}
	public void setFkTipoTitolarita(Integer fkTipoTitolarita) {
		this.fkTipoTitolarita = fkTipoTitolarita;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
		result = prime * result + ((dataFineValidita == null) ? 0 : dataFineValidita.hashCode());
		result = prime * result + ((dataInizioValidita == null) ? 0 : dataInizioValidita.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((fkConfigUtente == null) ? 0 : fkConfigUtente.hashCode());
		result = prime * result + ((idIntervento == null) ? 0 : idIntervento.hashCode());
		result = prime * result + ((idSoggetto == null) ? 0 : idSoggetto.hashCode());
		result = prime * result + ((idTipoSoggetto == null) ? 0 : idTipoSoggetto.hashCode());
		result = prime * result + ((fkTipoTitolarita == null) ? 0 : fkTipoTitolarita.hashCode());
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
		SoggettoIntervento soggettoIntervento = (SoggettoIntervento) obj;

		return Objects.equals(idIntervento, soggettoIntervento.idIntervento)
				&& Objects.equals(idSoggetto, soggettoIntervento.idSoggetto)
				&& Objects.equals(idTipoSoggetto, soggettoIntervento.idTipoSoggetto)
				&& Objects.equals(dataInizioValidita, soggettoIntervento.dataInizioValidita)
				&& Objects.equals(dataFineValidita, soggettoIntervento.dataFineValidita)
				&& Objects.equals(dataInserimento, soggettoIntervento.dataInserimento)
				&& Objects.equals(dataAggiornamento, soggettoIntervento.dataAggiornamento)
				&& Objects.equals(fkConfigUtente, soggettoIntervento.fkConfigUtente)
				&& Objects.equals(fkTipoTitolarita, soggettoIntervento.fkTipoTitolarita);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SoggettoIntervento {\n");

		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    idSoggetto: ").append(idSoggetto).append("\n");
		sb.append("    idTipoSoggetto: ").append(idTipoSoggetto).append("\n");
		sb.append("    dataInizioValidita: ").append(dataInizioValidita).append("\n");
		sb.append("    dataFineValidita: ").append(dataFineValidita).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("    fkTipoTitolarita: ").append(fkTipoTitolarita).append("\n");

		sb.append("}");
		return sb.toString();
	}
}
