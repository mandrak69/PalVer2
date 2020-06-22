package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class IntervTrasformazione {
	
	private Integer idIntervento;
	private String flgCompensazione;
	private Byte flgArt7A;
	private Byte flgArt7B;
	private Byte flgArt7C;
	private Byte flgArt7D;
	private Byte flgArt7Dbis;
	private Byte flgProprieta;
	private Byte flgDissensi;
	private Byte flgAutPaesaggistica;
	private LocalDate dataAutPaesaggistica;
	private String nrAutPaesaggistica;
	private String enteAutPaesaggistica;
	private Byte flgVincIdro;
	private Byte flgAutVidro;
	private LocalDate dataAutVidro;
	private String nrAutVidro;
	private Byte flgAutIncidenza;
	private LocalDate dataAutIncidenza;
	private String nrAutIncidenza;
	private String enteAutIncidenza;
	private String enteAutVidro;
	private Byte flgCauzioneCf;
	private Byte flgVersamentoCm;
	private String altriPareri;
	private String noteDichiarazione;
	private BigDecimal compenzazioneEuro;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public String getFlgCompensazione() {
		return flgCompensazione;
	}
	public void setFlgCompensazione(String flgCompensazione) {
		this.flgCompensazione = flgCompensazione;
	}
	public Byte getFlgArt7A() {
		return flgArt7A;
	}
	public void setFlgArt7A(Byte flgArt7A) {
		this.flgArt7A = flgArt7A;
	}
	public Byte getFlgArt7B() {
		return flgArt7B;
	}
	public void setFlgArt7B(Byte flgArt7B) {
		this.flgArt7B = flgArt7B;
	}
	public Byte getFlgArt7C() {
		return flgArt7C;
	}
	public void setFlgArt7C(Byte flgArt7C) {
		this.flgArt7C = flgArt7C;
	}
	public Byte getFlgArt7D() {
		return flgArt7D;
	}
	public void setFlgArt7D(Byte flgArt7D) {
		this.flgArt7D = flgArt7D;
	}
	public Byte getFlgArt7Dbis() {
		return flgArt7Dbis;
	}
	public void setFlgArt7Dbis(Byte flgArt7Dbis) {
		this.flgArt7Dbis = flgArt7Dbis;
	}
	public Byte getFlgProprieta() {
		return flgProprieta;
	}
	public void setFlgProprieta(Byte flgProprieta) {
		this.flgProprieta = flgProprieta;
	}
	public Byte getFlgDissensi() {
		return flgDissensi;
	}
	public void setFlgDissensi(Byte flgDissensi) {
		this.flgDissensi = flgDissensi;
	}
	public Byte getFlgAutPaesaggistica() {
		return flgAutPaesaggistica;
	}
	public void setFlgAutPaesaggistica(Byte flgAutPaesaggistica) {
		this.flgAutPaesaggistica = flgAutPaesaggistica;
	}
	public LocalDate getDataAutPaesaggistica() {
		return dataAutPaesaggistica;
	}
	public void setDataAutPaesaggistica(LocalDate dataAutPaesaggistica) {
		this.dataAutPaesaggistica = dataAutPaesaggistica;
	}
	public String getNrAutPaesaggistica() {
		return nrAutPaesaggistica;
	}
	public void setNrAutPaesaggistica(String nrAutPaesaggistica) {
		this.nrAutPaesaggistica = nrAutPaesaggistica;
	}
	public String getEnteAutPaesaggistica() {
		return enteAutPaesaggistica;
	}
	public void setEnteAutPaesaggistica(String enteAutPaesaggistica) {
		this.enteAutPaesaggistica = enteAutPaesaggistica;
	}
	public Byte getFlgVincIdro() {
		return flgVincIdro;
	}
	public void setFlgVincIdro(Byte flgVincIdro) {
		this.flgVincIdro = flgVincIdro;
	}
	public Byte getFlgAutVidro() {
		return flgAutVidro;
	}
	public void setFlgAutVidro(Byte flgAutVidro) {
		this.flgAutVidro = flgAutVidro;
	}
	public LocalDate getDataAutVidro() {
		return dataAutVidro;
	}
	public void setDataAutVidro(LocalDate dataAutVidro) {
		this.dataAutVidro = dataAutVidro;
	}
	public String getNrAutVidro() {
		return nrAutVidro;
	}
	public void setNrAutVidro(String nrAutVidro) {
		this.nrAutVidro = nrAutVidro;
	}
	public Byte getFlgAutIncidenza() {
		return flgAutIncidenza;
	}
	public void setFlgAutIncidenza(Byte flgAutIncidenza) {
		this.flgAutIncidenza = flgAutIncidenza;
	}
	public LocalDate getDataAutIncidenza() {
		return dataAutIncidenza;
	}
	public void setDataAutIncidenza(LocalDate dataAutIncidenza) {
		this.dataAutIncidenza = dataAutIncidenza;
	}
	public String getNrAutIncidenza() {
		return nrAutIncidenza;
	}
	public void setNrAutIncidenza(String nrAutIncidenza) {
		this.nrAutIncidenza = nrAutIncidenza;
	}
	public String getEnteAutIncidenza() {
		return enteAutIncidenza;
	}
	public void setEnteAutIncidenza(String enteAutIncidenza) {
		this.enteAutIncidenza = enteAutIncidenza;
	}
	public String getEnteAutVidro() {
		return enteAutVidro;
	}
	public void setEnteAutVidro(String enteAutVidro) {
		this.enteAutVidro = enteAutVidro;
	}
	public Byte getFlgCauzioneCf() {
		return flgCauzioneCf;
	}
	public void setFlgCauzioneCf(Byte flgCauzioneCf) {
		this.flgCauzioneCf = flgCauzioneCf;
	}
	public Byte getFlgVersamentoCm() {
		return flgVersamentoCm;
	}
	public void setFlgVersamentoCm(Byte flgVersamentoCm) {
		this.flgVersamentoCm = flgVersamentoCm;
	}
	public String getAltriPareri() {
		return altriPareri;
	}
	public void setAltriPareri(String altriPareri) {
		this.altriPareri = altriPareri;
	}
	public String getNoteDichiarazione() {
		return noteDichiarazione;
	}
	public void setNoteDichiarazione(String noteDichiarazione) {
		this.noteDichiarazione = noteDichiarazione;
	}
	public BigDecimal getCompenzazioneEuro() {
		return compenzazioneEuro;
	}
	public void setCompenzazioneEuro(BigDecimal compenzazioneEuro) {
		this.compenzazioneEuro = compenzazioneEuro;
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
	public int hashCode() {
		return Objects.hash(idIntervento, flgCompensazione, flgArt7A, flgArt7B, flgArt7C, flgArt7D, flgArt7Dbis,
				flgProprieta, flgDissensi, flgAutPaesaggistica, dataAutPaesaggistica, nrAutPaesaggistica,
				enteAutPaesaggistica, flgVincIdro, flgAutVidro, dataAutVidro, nrAutVidro, flgAutIncidenza,
				dataAutIncidenza, nrAutIncidenza, enteAutIncidenza, enteAutVidro, flgCauzioneCf, flgVersamentoCm,
				altriPareri, noteDichiarazione, compenzazioneEuro, dataInserimento, dataAggiornamento, fkConfigUtente);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
		      return true;
		    }
		    if (obj == null || getClass() != obj.getClass()) {
		      return false;
		    }
		    IntervTrasformazione intervTrasformazione = (IntervTrasformazione) obj;
		    
			return Objects.equals(idIntervento, intervTrasformazione.idIntervento) 
					&& Objects.equals(flgCompensazione, intervTrasformazione.flgCompensazione)
					&& Objects.equals(flgArt7A, intervTrasformazione.flgArt7A)
					&& Objects.equals(flgArt7B, intervTrasformazione.flgArt7B)
					&& Objects.equals(flgArt7C, intervTrasformazione.flgArt7C)
					&& Objects.equals(flgArt7D, intervTrasformazione.flgArt7D)
					&& Objects.equals(flgArt7Dbis, intervTrasformazione.flgArt7Dbis)
					&& Objects.equals(flgProprieta, intervTrasformazione.flgProprieta)
					&& Objects.equals(flgDissensi, intervTrasformazione.flgDissensi)
					&& Objects.equals(flgAutPaesaggistica, intervTrasformazione.flgAutPaesaggistica)
					&& Objects.equals(dataAutPaesaggistica, intervTrasformazione.dataAutPaesaggistica)
					&& Objects.equals(nrAutPaesaggistica, intervTrasformazione.nrAutPaesaggistica)
					&& Objects.equals(enteAutPaesaggistica, intervTrasformazione.enteAutPaesaggistica)
					&& Objects.equals(flgVincIdro, intervTrasformazione.flgVincIdro)
					&& Objects.equals(flgAutVidro, intervTrasformazione.flgAutVidro)
					&& Objects.equals(dataAutVidro, intervTrasformazione.dataAutVidro)
					&& Objects.equals(nrAutVidro, intervTrasformazione.nrAutVidro)
					&& Objects.equals(flgAutIncidenza, intervTrasformazione.flgAutIncidenza)
					&& Objects.equals(dataAutIncidenza, intervTrasformazione.dataAutIncidenza)
					&& Objects.equals(nrAutIncidenza, intervTrasformazione.nrAutIncidenza)
					&& Objects.equals(enteAutIncidenza, intervTrasformazione.enteAutIncidenza)
					&& Objects.equals(enteAutVidro, intervTrasformazione.enteAutVidro)
					&& Objects.equals(flgCauzioneCf, intervTrasformazione.flgCauzioneCf)
					&& Objects.equals(flgVersamentoCm, intervTrasformazione.flgVersamentoCm)
					&& Objects.equals(altriPareri, intervTrasformazione.altriPareri)
					&& Objects.equals(noteDichiarazione, intervTrasformazione.noteDichiarazione)
					&& Objects.equals(compenzazioneEuro, intervTrasformazione.compenzazioneEuro)
					&& Objects.equals(dataInserimento, intervTrasformazione.dataInserimento)
					&& Objects.equals(dataAggiornamento, intervTrasformazione.dataAggiornamento)
					&& Objects.equals(fkConfigUtente, intervTrasformazione.fkConfigUtente);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class IntervTrasformazione {\n");

		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    flgCompensazione: ").append(flgCompensazione).append("\n");
		sb.append("    flgArt7A: ").append(flgArt7A).append("\n");
		sb.append("    flgArt7B: ").append(flgArt7B).append("\n");
		sb.append("    flgArt7C: ").append(flgArt7C).append("\n");
		sb.append("    flgArt7D: ").append(flgArt7D).append("\n");
		sb.append("    flgArt7Dbis: ").append(flgArt7Dbis).append("\n");
		sb.append("    flgProprieta: ").append(flgProprieta).append("\n");
		sb.append("    flgDissensi: ").append(flgDissensi).append("\n");
		sb.append("    flgAutPaesaggistica: ").append(flgAutPaesaggistica).append("\n");
		sb.append("    dataAutPaesaggistica: ").append(dataAutPaesaggistica).append("\n");
		sb.append("    nrAutPaesaggistica: ").append(nrAutPaesaggistica).append("\n");
		sb.append("    enteAutPaesaggistica: ").append(enteAutPaesaggistica).append("\n");
		sb.append("    flgVincIdro: ").append(flgVincIdro).append("\n");
		sb.append("    flgAutVidro: ").append(flgAutVidro).append("\n");
		sb.append("    dataAutVidro: ").append(dataAutVidro).append("\n");
		sb.append("    nrAutVidro: ").append(nrAutVidro).append("\n");
		sb.append("    flgAutIncidenza: ").append(flgAutIncidenza).append("\n");
		sb.append("    dataAutIncidenza: ").append(dataAutIncidenza).append("\n");
		sb.append("    nrAutIncidenza: ").append(nrAutIncidenza).append("\n");
		sb.append("    enteAutIncidenza: ").append(enteAutIncidenza).append("\n");
		sb.append("    enteAutVidro: ").append(enteAutVidro).append("\n");
		sb.append("    flgCauzioneCf: ").append(flgCauzioneCf).append("\n");
		sb.append("    flgVersamentoCm: ").append(flgVersamentoCm).append("\n");
		sb.append("    altriPareri: ").append(altriPareri).append("\n");
		sb.append("    noteDichiarazione: ").append(noteDichiarazione).append("\n");
		sb.append("    compenzazioneEuro: ").append(compenzazioneEuro).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
