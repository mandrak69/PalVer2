package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class FatSoggetto {
	
	private Integer idSoggetto;
	private ComuneResource comune;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String partitaIva;
	private String denominazione;
	private String indirizzo;
	private String nrTelefonico;
	private String eMail;
	private String pec;
	private String nIscrizioneOrdine;
	private String istatProvIscrizioneOrdine;
	private String istatProvCompetenzaTerr;
	private Byte flgSettoreRegionale;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private String civico;
	private String cap;
	
	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getNrTelefonico() {
		return nrTelefonico;
	}
	public void setNrTelefonico(String nrTelefonico) {
		this.nrTelefonico = nrTelefonico;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPec() {
		return pec;
	}
	public void setPec(String pec) {
		this.pec = pec;
	}
	public String getnIscrizioneOrdine() {
		return nIscrizioneOrdine;
	}
	public void setnIscrizioneOrdine(String nIscrizioneOrdine) {
		this.nIscrizioneOrdine = nIscrizioneOrdine;
	}
	public String getIstatProvIscrizioneOrdine() {
		return istatProvIscrizioneOrdine;
	}
	public void setIstatProvIscrizioneOrdine(String istatProvIscrizioneOrdine) {
		this.istatProvIscrizioneOrdine = istatProvIscrizioneOrdine;
	}
	public String getIstatProvCompetenzaTerr() {
		return istatProvCompetenzaTerr;
	}
	public void setIstatProvCompetenzaTerr(String istatProvCompetenzaTerr) {
		this.istatProvCompetenzaTerr = istatProvCompetenzaTerr;
	}
	public Byte getFlgSettoreRegionale() {
		return flgSettoreRegionale;
	}
	public void setFlgSettoreRegionale(Byte flgSettoreRegionale) {
		this.flgSettoreRegionale = flgSettoreRegionale;
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
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FatSoggetto [idSoggetto=");
		builder.append(idSoggetto);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cognome=");
		builder.append(cognome);
		builder.append(", codiceFiscale=");
		builder.append(codiceFiscale);
		builder.append(", partitaIva=");
		builder.append(partitaIva);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", indirizzo=");
		builder.append(indirizzo);
		builder.append(", nrTelefonico=");
		builder.append(nrTelefonico);
		builder.append(", eMail=");
		builder.append(eMail);
		builder.append(", pec=");
		builder.append(pec);
		builder.append(", nIscrizioneOrdine=");
		builder.append(nIscrizioneOrdine);
		builder.append(", istatProvIscrizioneOrdine=");
		builder.append(istatProvIscrizioneOrdine);
		builder.append(", istatProvCompetenzaTerr=");
		builder.append(istatProvCompetenzaTerr);
		builder.append(", flgSettoreRegionale=");
		builder.append(flgSettoreRegionale);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", civico=");
		builder.append(civico);
		builder.append(", cap=");
		builder.append(cap);
		builder.append("]");
		return builder.toString();
	}
}
