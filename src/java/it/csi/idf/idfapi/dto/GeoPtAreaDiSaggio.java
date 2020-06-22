package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoPtAreaDiSaggio {
	
	private Integer idgeoPtAds;
	private Integer fkComune;
	private Integer fkSoggettoPg;
	private LocalDate dataRil;
	private String codiceAds;
	private Integer fkSettore;
	private Integer fkProprieta;
	private Integer fkTipoAds;
	private Integer fkAssetto;
	private Integer fkEsposizione;
	private Integer fkComunitaMontana;
	private Integer fkGeoTipoForestale;
	private Integer fkDestinazione;
	private Integer fkPriorita;
	private String flgDia;
	private Integer fkSoggetto;
	private Integer quota;
	private Integer inclinazione;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private String note;
	private Integer fkAmbito;
	private Integer idgeoPlParticellaForest;
	private Integer fkDanno;
	private Integer fkTipoIntervento;
	
	@JsonProperty("idgeoPtAds")
	public Integer getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Integer idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	@JsonProperty("fkComune")
	public Integer getFkComune() {
		return fkComune;
	}
	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}
	@JsonProperty("fkSoggettoPg")
	public Integer getFkSoggettoPg() {
		return fkSoggettoPg;
	}
	public void setFkSoggettoPg(Integer fkSoggettoPg) {
		this.fkSoggettoPg = fkSoggettoPg;
	}
	@JsonProperty("dataRil")
	public LocalDate getDataRil() {
		return dataRil;
	}
	public void setDataRil(LocalDate dataRil) {
		this.dataRil = dataRil;
	}
	@JsonProperty("codiceAds")
	public String getCodiceAds() {
		return codiceAds;
	}
	public void setCodiceAds(String codiceAds) {
		this.codiceAds = codiceAds;
	}
	@JsonProperty("fkSettore")
	public Integer getFkSettore() {
		return fkSettore;
	}
	public void setFkSettore(Integer fkSettore) {
		this.fkSettore = fkSettore;
	}
	@JsonProperty("fkProprieta")
	public Integer getFkProprieta() {
		return fkProprieta;
	}
	public void setFkProprieta(Integer fkProprieta) {
		this.fkProprieta = fkProprieta;
	}
	@JsonProperty("fkTipoAds")
	public Integer getFkTipoAds() {
		return fkTipoAds;
	}
	public void setFkTipoAds(Integer fkTipoAds) {
		this.fkTipoAds = fkTipoAds;
	}
	@JsonProperty("fkAssetto")
	public Integer getFkAssetto() {
		return fkAssetto;
	}
	public void setFkAssetto(Integer fkAssetto) {
		this.fkAssetto = fkAssetto;
	}
	@JsonProperty("fkEsposizione")
	public Integer getFkEsposizione() {
		return fkEsposizione;
	}
	public void setFkEsposizione(Integer fkEsposizione) {
		this.fkEsposizione = fkEsposizione;
	}
	@JsonProperty("fkComunitaMontana")
	public Integer getFkComunitaMontana() {
		return fkComunitaMontana;
	}
	public void setFkComunitaMontana(Integer fkComunitaMontana) {
		this.fkComunitaMontana = fkComunitaMontana;
	}
	@JsonProperty("fkGeoTipoForestale")
	public Integer getFkGeoTipoForestale() {
		return fkGeoTipoForestale;
	}
	public void setFkGeoTipoForestale(Integer fkGeoTipoForestale) {
		this.fkGeoTipoForestale = fkGeoTipoForestale;
	}
	@JsonProperty("fkDestinazione")
	public Integer getFkDestinazione() {
		return fkDestinazione;
	}
	public void setFkDestinazione(Integer fkDestinazione) {
		this.fkDestinazione = fkDestinazione;
	}
	@JsonProperty("fkPriorita")
	public Integer getFkPriorita() {
		return fkPriorita;
	}
	public void setFkPriorita(Integer fkPriorita) {
		this.fkPriorita = fkPriorita;
	}
	@JsonProperty("flgDia")
	public String getFlgDia() {
		return flgDia;
	}
	public void setFlgDia(String flgDia) {
		this.flgDia = flgDia;
	}
	@JsonProperty("fkSoggetto")
	public Integer getFkSoggetto() {
		return fkSoggetto;
	}
	public void setFkSoggetto(Integer fkSoggetto) {
		this.fkSoggetto = fkSoggetto;
	}
	@JsonProperty("quota")
	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}
	@JsonProperty("inclinazione")
	public Integer getInclinazione() {
		return inclinazione;
	}
	public void setInclinazione(Integer inclinazione) {
		this.inclinazione = inclinazione;
	}
	@JsonProperty("dataInizioValidita")
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	@JsonProperty("dataFineValidita")
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	@JsonProperty("note")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@JsonProperty("fkAmbito")
	public Integer getFkAmbito() {
		return fkAmbito;
	}
	public void setFkAmbito(Integer fkAmbito) {
		this.fkAmbito = fkAmbito;
	}
	@JsonProperty("idgeoPlParticellaForest")
	public Integer getIdgeoPlParticellaForest() {
		return idgeoPlParticellaForest;
	}
	public void setIdgeoPlParticellaForest(Integer idgeoPlParticellaForest) {
		this.idgeoPlParticellaForest = idgeoPlParticellaForest;
	}
	@JsonProperty("fkDanno")
	public Integer getFkDanno() {
		return fkDanno;
	}
	public void setFkDanno(Integer fkDanno) {
		this.fkDanno = fkDanno;
	}
	@JsonProperty("fkTipoIntervento")
	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}
	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}
	
	

}
