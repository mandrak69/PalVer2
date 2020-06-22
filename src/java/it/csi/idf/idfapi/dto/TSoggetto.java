package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class TSoggetto {
	
	private final String TABLE = "idf_t_soggetto";
	
	@JsonProperty("id_soggetto")
	private Integer idSoggetto;
	@JsonProperty("fk_comune")

	private Integer fkComune;
	@JsonProperty("nome")
	private String nome;
	@JsonProperty("cognome")
	private String cognome;
	@JsonProperty("codice_fiscale")
	private String codiceFiscale;
	@JsonProperty("partita_iva")
	private String partitaIva;
	@JsonProperty("denominazione")
	private String denominazione;
	@JsonProperty("indirizzo")
	private String indirizzo;
	@JsonProperty("nr_telefonico")
	private String nrTelefonico;
	@JsonProperty("e_mail")
	private String eMail;
	@JsonProperty("pec")
	private String pec;
	@JsonProperty("n_iscrizione_ordine")
	private String nIscrizioneOrdine;
	@JsonProperty("istat_prov_iscrizione_ordine")
	private String istatProvIscrizioneOrdine;
	@JsonProperty("istat_prov_competenza_terr")
	private String istatProvCompetenzaTerr;
	@JsonProperty("flg_settore_regionale")
	private Byte flgSettoreRegionale;
	@JsonProperty("data_inserimento")
	private LocalDate dataInserimento = null;
	@JsonProperty("data_aggiornamento")
	private LocalDate dataAggiornamento = null;
	@JsonProperty("fk_config_utente")
	private Integer fkConfigUtente;
	@JsonProperty("civico")
	private String civico;
	@JsonProperty("cap")
	private String cap;

	public Integer getIdSoggetto() {
		return idSoggetto;
	}

	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}

	@JsonProperty("nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonProperty("cognome")
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@JsonProperty("codiceFiscale")
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@JsonProperty("partitaIva")
	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	@JsonProperty("fkComune")
	public Integer getFkComune() {
		return fkComune;
	}

	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}

	@JsonProperty("denominazione")
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	@JsonProperty("nIscrizioneOrdine")
	public String getnIscrizioneOrdine() {
		return nIscrizioneOrdine;
	}

	public void setnIscrizioneOrdine(String nIscrizioneOrdine) {
		this.nIscrizioneOrdine = nIscrizioneOrdine;
	}

	@JsonProperty("istatProvIscrizioneOrdine")
	public String getIstatProvIscrizioneOrdine() {
		return istatProvIscrizioneOrdine;
	}

	public void setIstatProvIscrizioneOrdine(String istatProvIscrizioneOrdine) {
		this.istatProvIscrizioneOrdine = istatProvIscrizioneOrdine;
	}

	@JsonProperty("istatProvCompetenzaTerr")
	public String getIstatProvCompetenzaTerr() {
		return istatProvCompetenzaTerr;
	}

	public void setIstatProvCompetenzaTerr(String istatProvCompetenzaTerr) {
		this.istatProvCompetenzaTerr = istatProvCompetenzaTerr;
	}

	@JsonProperty("flgSettoreRegionale")
	public Byte getFlgSettoreRegionale() {
		return flgSettoreRegionale;
	}

	public void setFlgSettoreRegionale(Byte flgSettoreRegionale) {
		this.flgSettoreRegionale = flgSettoreRegionale;
	}

	@JsonProperty("dataInserimento")
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	@JsonProperty("dataAggiornamento")
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	@JsonProperty("fkConfigUtente")
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}

	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}

	@JsonProperty("indirizzo")
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@JsonProperty("nrTelefonico")
	public String getNrTelefonico() {
		return nrTelefonico;
	}

	public void setNrTelefonico(String nrTelefonico) {
		this.nrTelefonico = nrTelefonico;
	}

	@JsonProperty("eMail")
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@JsonProperty("pec")
	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	@JsonProperty("civico")
	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	@JsonProperty("cap")
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TSoggetto soggetto = (TSoggetto) o;
		return Objects.equals(idSoggetto, soggetto.idSoggetto) && Objects.equals(nome, soggetto.nome)
				&& Objects.equals(cognome, soggetto.cognome) && Objects.equals(eMail, soggetto.eMail)
				&& Objects.equals(codiceFiscale, soggetto.codiceFiscale) && Objects.equals(pec, soggetto.pec)
				&& Objects.equals(partitaIva, soggetto.partitaIva) && Objects.equals(fkComune, soggetto.fkComune)
				&& Objects.equals(denominazione, soggetto.denominazione)
				&& Objects.equals(indirizzo, soggetto.indirizzo)
				&& Objects.equals(nIscrizioneOrdine, soggetto.nIscrizioneOrdine)
				&& Objects.equals(istatProvIscrizioneOrdine, soggetto.istatProvIscrizioneOrdine)
				&& Objects.equals(istatProvCompetenzaTerr, soggetto.istatProvCompetenzaTerr)
				&& Objects.equals(flgSettoreRegionale, soggetto.flgSettoreRegionale)
				&& Objects.equals(dataInserimento, soggetto.dataInserimento)
				&& Objects.equals(dataAggiornamento, soggetto.dataAggiornamento)
				&& Objects.equals(fkConfigUtente, soggetto.fkConfigUtente)
				&& Objects.equals(nrTelefonico, soggetto.nrTelefonico) && Objects.equals(civico, soggetto.civico)
				&& Objects.equals(cap, soggetto.cap);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSoggetto, nome, cognome, codiceFiscale, partitaIva, fkComune, denominazione, indirizzo,
				nIscrizioneOrdine, istatProvIscrizioneOrdine, istatProvCompetenzaTerr, flgSettoreRegionale,
				dataInserimento, nrTelefonico, dataAggiornamento, fkConfigUtente, eMail, pec, civico, cap);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TSoggetto {\n");

		sb.append("    idSoggetto: ").append(idSoggetto).append("\n");
		sb.append("    fkComune: ").append(fkComune).append("\n");
		sb.append("    nome: ").append(nome).append("\n");
		sb.append("    cognome: ").append(cognome).append("\n");
		sb.append("    codiceFiscale: ").append(codiceFiscale).append("\n");
		sb.append("    partitaIva: ").append(partitaIva).append("\n");
		sb.append("    denominazione: ").append(denominazione).append("\n");
		sb.append("    indirizzo: ").append(indirizzo).append("\n");
		sb.append("    nrTelefonico: ").append(nrTelefonico).append("\n");
		sb.append("    eMail: ").append(eMail).append("\n");
		sb.append("    pec: ").append(pec).append("\n");
		sb.append("    nIscrizioneOrdine: ").append(nIscrizioneOrdine).append("\n");
		sb.append("    istatProvIscrizioneOrdine: ").append(istatProvIscrizioneOrdine).append("\n");
		sb.append("    istatProvCompetenzaTerr: ").append(istatProvCompetenzaTerr).append("\n");
		sb.append("    flgSettoreRegionale: ").append(flgSettoreRegionale).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("    civico: ").append(civico).append("\n");
		sb.append("    cap: ").append(cap).append("\n");

		sb.append("}");
		return sb.toString();
	}

}
