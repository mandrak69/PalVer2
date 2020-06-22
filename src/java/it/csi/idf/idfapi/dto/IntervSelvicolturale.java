package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IntervSelvicolturale {

	private Integer idIntervento;
	private Integer fkTipoIntervento;
	private Integer fkStatoIntervento;
	private Integer idgeoPlPfa;
	private Integer fkTipoForestalePrevalente;
	private Integer fkFinalitaTaglio;
	private Integer fkDestLegname;
	private Integer fkFasciaAltimetrica;
	private Byte flgIntervNonPrevisto;
	private Integer fkConfigIpla;
	private Integer nrPiante;
	private Integer stimaMassaRetraibileM3;
	private Integer m3Prelevati;
	private Integer volumeRamagliaM3;
	private LocalDate dataPresaInCarico;
	private String annataSilvana;
	private Integer nrProgressivoInterv;
	private Byte flgIstanzaTaglio;
	private Byte flgPiedilista;
	private String flgConformeDeroga;
	private String noteEsbosco;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private BigDecimal ripresaPrevistaMc;
	private BigDecimal ripresaRealeFineIntervMc;
	private Integer fkGoverno;
	private String codEsbosco;
	private Integer idUsoViabilita;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}
	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}
	public Integer getFkStatoIntervento() {
		return fkStatoIntervento;
	}
	public void setFkStatoIntervento(Integer fkStatoIntervento) {
		this.fkStatoIntervento = fkStatoIntervento;
	}
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public Integer getFkTipoForestalePrevalente() {
		return fkTipoForestalePrevalente;
	}
	public void setFkTipoForestalePrevalente(Integer fkTipoForestalePrevalente) {
		this.fkTipoForestalePrevalente = fkTipoForestalePrevalente;
	}
	public Integer getFkFinalitaTaglio() {
		return fkFinalitaTaglio;
	}
	public void setFkFinalitaTaglio(Integer fkFinalitaTaglio) {
		this.fkFinalitaTaglio = fkFinalitaTaglio;
	}
	public Integer getFkDestLegname() {
		return fkDestLegname;
	}
	public void setFkDestLegname(Integer fkDestLegname) {
		this.fkDestLegname = fkDestLegname;
	}
	public Integer getFkFasciaAltimetrica() {
		return fkFasciaAltimetrica;
	}
	public void setFkFasciaAltimetrica(Integer fkFasciaAltimetrica) {
		this.fkFasciaAltimetrica = fkFasciaAltimetrica;
	}
	public Byte getFlgIntervNonPrevisto() {
		return flgIntervNonPrevisto;
	}
	public void setFlgIntervNonPrevisto(Byte flgIntervNonPrevisto) {
		this.flgIntervNonPrevisto = flgIntervNonPrevisto;
	}
	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}
	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}
	public Integer getNrPiante() {
		return nrPiante;
	}
	public void setNrPiante(Integer nrPiante) {
		this.nrPiante = nrPiante;
	}
	public Integer getStimaMassaRetraibileM3() {
		return stimaMassaRetraibileM3;
	}
	public void setStimaMassaRetraibileM3(Integer stimaMassaRetraibileM3) {
		this.stimaMassaRetraibileM3 = stimaMassaRetraibileM3;
	}
	public Integer getM3Prelevati() {
		return m3Prelevati;
	}
	public void setM3Prelevati(Integer m3Prelevati) {
		this.m3Prelevati = m3Prelevati;
	}
	public Integer getVolumeRamagliaM3() {
		return volumeRamagliaM3;
	}
	public void setVolumeRamagliaM3(Integer volumeRamagliaM3) {
		this.volumeRamagliaM3 = volumeRamagliaM3;
	}
	public LocalDate getDataPresaInCarico() {
		return dataPresaInCarico;
	}
	public void setDataPresaInCarico(LocalDate dataPresaInCarico) {
		this.dataPresaInCarico = dataPresaInCarico;
	}
	public String getAnnataSilvana() {
		return annataSilvana;
	}
	public void setAnnataSilvana(String annataSilvana) {
		this.annataSilvana = annataSilvana;
	}
	public Integer getNrProgressivoInterv() {
		return nrProgressivoInterv;
	}
	public void setNrProgressivoInterv(Integer nrProgressivoInterv) {
		this.nrProgressivoInterv = nrProgressivoInterv;
	}
	public Byte getFlgIstanzaTaglio() {
		return flgIstanzaTaglio;
	}
	public void setFlgIstanzaTaglio(Byte flgIstanzaTaglio) {
		this.flgIstanzaTaglio = flgIstanzaTaglio;
	}
	public Byte getFlgPiedilista() {
		return flgPiedilista;
	}
	public void setFlgPiedilista(Byte flgPiedilista) {
		this.flgPiedilista = flgPiedilista;
	}
	public String getFlgConformeDeroga() {
		return flgConformeDeroga;
	}
	public void setFlgConformeDeroga(String flgConformeDeroga) {
		this.flgConformeDeroga = flgConformeDeroga;
	}
	public String getNoteEsbosco() {
		return noteEsbosco;
	}
	public void setNoteEsbosco(String noteEsbosco) {
		this.noteEsbosco = noteEsbosco;
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
	public BigDecimal getRipresaPrevistaMc() {
		return ripresaPrevistaMc;
	}
	public void setRipresaPrevistaMc(BigDecimal ripresaPrevistaMc) {
		this.ripresaPrevistaMc = ripresaPrevistaMc;
	}
	public BigDecimal getRipresaRealeFineIntervMc() {
		return ripresaRealeFineIntervMc;
	}
	public void setRipresaRealeFineIntervMc(BigDecimal ripresaRealeFineIntervMc) {
		this.ripresaRealeFineIntervMc = ripresaRealeFineIntervMc;
	}
	public Integer getFkGoverno() {
		return fkGoverno;
	}
	public void setFkGoverno(Integer fkGoverno) {
		this.fkGoverno = fkGoverno;
	}
	public String getCodEsbosco() {
		return codEsbosco;
	}
	public void setCodEsbosco(String codEsbosco) {
		this.codEsbosco = codEsbosco;
	}
	public Integer getIdUsoViabilita() {
		return idUsoViabilita;
	}
	public void setIdUsoViabilita(Integer idUsoViabilita) {
		this.idUsoViabilita = idUsoViabilita;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntervSelvicolturale [idIntervento=");
		builder.append(idIntervento);
		builder.append(", fkTipoIntervento=");
		builder.append(fkTipoIntervento);
		builder.append(", fkStatoIntervento=");
		builder.append(fkStatoIntervento);
		builder.append(", idgeoPlPfa=");
		builder.append(idgeoPlPfa);
		builder.append(", fkTipoForestalePrevalente=");
		builder.append(fkTipoForestalePrevalente);
		builder.append(", fkFinalitaTaglio=");
		builder.append(fkFinalitaTaglio);
		builder.append(", fkDestLegname=");
		builder.append(fkDestLegname);
		builder.append(", fkFasciaAltimetrica=");
		builder.append(fkFasciaAltimetrica);
		builder.append(", flgIntervNonPrevisto=");
		builder.append(flgIntervNonPrevisto);
		builder.append(", fkConfigIpla=");
		builder.append(fkConfigIpla);
		builder.append(", nrPiante=");
		builder.append(nrPiante);
		builder.append(", stimaMassaRetraibileM3=");
		builder.append(stimaMassaRetraibileM3);
		builder.append(", m3Prelevati=");
		builder.append(m3Prelevati);
		builder.append(", volumeRamagliaM3=");
		builder.append(volumeRamagliaM3);
		builder.append(", dataPresaInCarico=");
		builder.append(dataPresaInCarico);
		builder.append(", annataSilvana=");
		builder.append(annataSilvana);
		builder.append(", nrProgressivoInterv=");
		builder.append(nrProgressivoInterv);
		builder.append(", flgIstanzaTaglio=");
		builder.append(flgIstanzaTaglio);
		builder.append(", flgPiedilista=");
		builder.append(flgPiedilista);
		builder.append(", flgConformeDeroga=");
		builder.append(flgConformeDeroga);
		builder.append(", noteEsbosco=");
		builder.append(noteEsbosco);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", ripresaPrevistaMc=");
		builder.append(ripresaPrevistaMc);
		builder.append(", ripresaRealeFineIntervMc=");
		builder.append(ripresaRealeFineIntervMc);
		builder.append(", fkGoverno=");
		builder.append(fkGoverno);
		builder.append(", codEsbosco=");
		builder.append(codEsbosco);
		builder.append(", idUsoViabilita=");
		builder.append(idUsoViabilita);
		builder.append("]");
		return builder.toString();
	}
	
	
}
