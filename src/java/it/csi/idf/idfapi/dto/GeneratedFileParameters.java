package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;

public class GeneratedFileParameters {
	
	private TipoAllegatoEnum tipoAllegato;
	private int idIntervento; 
	private int fkConfigUtente;
	private TipoTitolaritaEnum tipoTitolarita;
	private String richCognome;
	private String richNome;
	private String richRagSociale;
	private String richCodiceFiscale;
	private String richPartitaIva;
	private String richIndirizzo;
	private String richCivico;
	private String richProvincia;
	private String richCap;
	private String richComune;
	private String richTelefonico;
	private String richEmail;
	private String richPec;
	private SuperficieCompensationEnum flgCompensazione;
	private boolean flgArt7a;
	private boolean flgArt7b;
	private boolean flgArt7c;
	private boolean flgArt7d;
	private boolean flgArt7dBis;
	private boolean formaGovernoFlg1;
	private boolean formaGovernoFlg2;
	private boolean categForestFlg1;
	private boolean categForestFlg2;
	private boolean categForestFlg3;
	private boolean ubicazioneFlg1;
	private boolean ubicazioneFlg2;
	private boolean ubicazioneFlg3;
	private boolean destVincFlg1;
	private boolean destVincFlg2;
	private boolean destVincFlg3;
	private boolean tipologiaFlg1;
	private boolean tipologiaFlg2;
	private boolean tipologiaFlg3;
	private String profCognome;
	private String profNome;
	private String profCodiceFiscale;
	private String profProvinciaOrdine;
	private String profNIscrizione;
	private String profTelefonico;
	private String profPec;
	private String compenzazioneEuro;
	private boolean dichProprietario;
	private boolean dichDissenso;
	private boolean dichAutPaesaggistica;
	private LocalDate dichDataPaesaggistica;
	private String dichNrPaesaggistica;
	private String dichEntePaesaggistica;
	private boolean dichAutVidro;
	private LocalDate dichDataVidro;
	private String dichNrVidro;
	private String dichEnteVidro;
	private boolean dichAutIncidenza;
	private LocalDate dichDataIncidenza;
	private String dichNrIncidenza;
	private String dichEnteIncidenza;
	private String dichAltriPareri;
	private List<DichPropCatasto> propCatasti;
	private BigDecimal superficieInteressata;
	private String regionaleSoggetto;
	private List<IstanzaCompensazione> istanzeCompensazione;
	
	public TipoAllegatoEnum getTipoAllegato() {
		return tipoAllegato;
	}
	public void setTipoAllegato(TipoAllegatoEnum tipoAllegato) {
		this.tipoAllegato = tipoAllegato;
	}
	public int getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(int idIntervento) {
		this.idIntervento = idIntervento;
	}
	public int getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(int fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public TipoTitolaritaEnum getTipoTitolarita() {
		return tipoTitolarita;
	}
	public void setTipoTitolarita(TipoTitolaritaEnum tipoTitolarita) {
		this.tipoTitolarita = tipoTitolarita;
	}
	public String getRichCognome() {
		return richCognome;
	}
	public void setRichCognome(String richCognome) {
		this.richCognome = richCognome;
	}
	public String getRichNome() {
		return richNome;
	}
	public void setRichNome(String richNome) {
		this.richNome = richNome;
	}
	public String getRichRagSociale() {
		return richRagSociale;
	}
	public void setRichRagSociale(String richRagSociale) {
		this.richRagSociale = richRagSociale;
	}
	public String getRichCodiceFiscale() {
		return richCodiceFiscale;
	}
	public void setRichCodiceFiscale(String richCodiceFiscale) {
		this.richCodiceFiscale = richCodiceFiscale;
	}
	public String getRichPartitaIva() {
		return richPartitaIva;
	}
	public void setRichPartitaIva(String richPartitaIva) {
		this.richPartitaIva = richPartitaIva;
	}
	public String getRichIndirizzo() {
		return richIndirizzo;
	}
	public void setRichIndirizzo(String richIndirizzo) {
		this.richIndirizzo = richIndirizzo;
	}
	public String getRichCivico() {
		return richCivico;
	}
	public void setRichCivico(String richCivico) {
		this.richCivico = richCivico;
	}
	public String getRichProvincia() {
		return richProvincia;
	}
	public void setRichProvincia(String richProvincia) {
		this.richProvincia = richProvincia;
	}
	public String getRichCap() {
		return richCap;
	}
	public void setRichCap(String richCap) {
		this.richCap = richCap;
	}
	public String getRichComune() {
		return richComune;
	}
	public void setRichComune(String richComune) {
		this.richComune = richComune;
	}
	public String getRichTelefonico() {
		return richTelefonico;
	}
	public void setRichTelefonico(String richTelefonico) {
		this.richTelefonico = richTelefonico;
	}
	public String getRichEmail() {
		return richEmail;
	}
	public void setRichEmail(String richEmail) {
		this.richEmail = richEmail;
	}
	public String getRichPec() {
		return richPec;
	}
	public void setRichPec(String richPec) {
		this.richPec = richPec;
	}
	public SuperficieCompensationEnum getFlgCompensazione() {
		return flgCompensazione;
	}
	public void setFlgCompensazione(SuperficieCompensationEnum flgCompensazione) {
		this.flgCompensazione = flgCompensazione;
	}
	public boolean isFlgArt7a() {
		return flgArt7a;
	}
	public void setFlgArt7a(boolean flgArt7a) {
		this.flgArt7a = flgArt7a;
	}
	public boolean isFlgArt7b() {
		return flgArt7b;
	}
	public void setFlgArt7b(boolean flgArt7b) {
		this.flgArt7b = flgArt7b;
	}
	public boolean isFlgArt7c() {
		return flgArt7c;
	}
	public void setFlgArt7c(boolean flgArt7c) {
		this.flgArt7c = flgArt7c;
	}
	public boolean isFlgArt7d() {
		return flgArt7d;
	}
	public void setFlgArt7d(boolean flgArt7d) {
		this.flgArt7d = flgArt7d;
	}
	public boolean isFlgArt7dBis() {
		return flgArt7dBis;
	}
	public void setFlgArt7dBis(boolean flgArt7dBis) {
		this.flgArt7dBis = flgArt7dBis;
	}
	public boolean isFormaGovernoFlg1() {
		return formaGovernoFlg1;
	}
	public void setFormaGovernoFlg1(boolean formaGovernoFlg1) {
		this.formaGovernoFlg1 = formaGovernoFlg1;
	}
	public boolean isFormaGovernoFlg2() {
		return formaGovernoFlg2;
	}
	public void setFormaGovernoFlg2(boolean formaGovernoFlg2) {
		this.formaGovernoFlg2 = formaGovernoFlg2;
	}
	public boolean isCategForestFlg1() {
		return categForestFlg1;
	}
	public void setCategForestFlg1(boolean categForestFlg1) {
		this.categForestFlg1 = categForestFlg1;
	}
	public boolean isCategForestFlg2() {
		return categForestFlg2;
	}
	public void setCategForestFlg2(boolean categForestFlg2) {
		this.categForestFlg2 = categForestFlg2;
	}
	public boolean isCategForestFlg3() {
		return categForestFlg3;
	}
	public void setCategForestFlg3(boolean categForestFlg3) {
		this.categForestFlg3 = categForestFlg3;
	}
	public boolean isUbicazioneFlg1() {
		return ubicazioneFlg1;
	}
	public void setUbicazioneFlg1(boolean ubicazioneFlg1) {
		this.ubicazioneFlg1 = ubicazioneFlg1;
	}
	public boolean isUbicazioneFlg2() {
		return ubicazioneFlg2;
	}
	public void setUbicazioneFlg2(boolean ubicazioneFlg2) {
		this.ubicazioneFlg2 = ubicazioneFlg2;
	}
	public boolean isUbicazioneFlg3() {
		return ubicazioneFlg3;
	}
	public void setUbicazioneFlg3(boolean ubicazioneFlg3) {
		this.ubicazioneFlg3 = ubicazioneFlg3;
	}
	public boolean isDestVincFlg1() {
		return destVincFlg1;
	}
	public void setDestVincFlg1(boolean destVincFlg1) {
		this.destVincFlg1 = destVincFlg1;
	}
	public boolean isDestVincFlg2() {
		return destVincFlg2;
	}
	public void setDestVincFlg2(boolean destVincFlg2) {
		this.destVincFlg2 = destVincFlg2;
	}
	public boolean isDestVincFlg3() {
		return destVincFlg3;
	}
	public void setDestVincFlg3(boolean destVincFlg3) {
		this.destVincFlg3 = destVincFlg3;
	}
	public boolean isTipologiaFlg1() {
		return tipologiaFlg1;
	}
	public void setTipologiaFlg1(boolean tipologiaFlg1) {
		this.tipologiaFlg1 = tipologiaFlg1;
	}
	public boolean isTipologiaFlg2() {
		return tipologiaFlg2;
	}
	public void setTipologiaFlg2(boolean tipologiaFlg2) {
		this.tipologiaFlg2 = tipologiaFlg2;
	}
	public boolean isTipologiaFlg3() {
		return tipologiaFlg3;
	}
	public void setTipologiaFlg3(boolean tipologiaFlg3) {
		this.tipologiaFlg3 = tipologiaFlg3;
	}
	public String getProfCognome() {
		return profCognome;
	}
	public void setProfCognome(String profCognome) {
		this.profCognome = profCognome;
	}
	public String getProfNome() {
		return profNome;
	}
	public void setProfNome(String profNome) {
		this.profNome = profNome;
	}
	public String getProfCodiceFiscale() {
		return profCodiceFiscale;
	}
	public void setProfCodiceFiscale(String profCodiceFiscale) {
		this.profCodiceFiscale = profCodiceFiscale;
	}
	public String getProfProvinciaOrdine() {
		return profProvinciaOrdine;
	}
	public void setProfProvinciaOrdine(String profProvinciaOrdine) {
		this.profProvinciaOrdine = profProvinciaOrdine;
	}
	public String getProfNIscrizione() {
		return profNIscrizione;
	}
	public void setProfNIscrizione(String profNIscrizione) {
		this.profNIscrizione = profNIscrizione;
	}
	public String getProfTelefonico() {
		return profTelefonico;
	}
	public void setProfTelefonico(String profTelefonico) {
		this.profTelefonico = profTelefonico;
	}
	public String getProfPec() {
		return profPec;
	}
	public void setProfPec(String profPec) {
		this.profPec = profPec;
	}
	public String getCompenzazioneEuro() {
		return compenzazioneEuro;
	}
	public void setCompenzazioneEuro(String compenzazioneEuro) {
		this.compenzazioneEuro = compenzazioneEuro;
	}
	public boolean isDichProprietario() {
		return dichProprietario;
	}
	public void setDichProprietario(boolean dichProprietario) {
		this.dichProprietario = dichProprietario;
	}
	public boolean isDichDissenso() {
		return dichDissenso;
	}
	public void setDichDissenso(boolean dichDissenso) {
		this.dichDissenso = dichDissenso;
	}
	public boolean isDichAutPaesaggistica() {
		return dichAutPaesaggistica;
	}
	public void setDichAutPaesaggistica(boolean dichAutPaesaggistica) {
		this.dichAutPaesaggistica = dichAutPaesaggistica;
	}
	public LocalDate getDichDataPaesaggistica() {
		return dichDataPaesaggistica;
	}
	public void setDichDataPaesaggistica(LocalDate dichDataPaesaggistica) {
		this.dichDataPaesaggistica = dichDataPaesaggistica;
	}
	public String getDichNrPaesaggistica() {
		return dichNrPaesaggistica;
	}
	public void setDichNrPaesaggistica(String dichNrPaesaggistica) {
		this.dichNrPaesaggistica = dichNrPaesaggistica;
	}
	public String getDichEntePaesaggistica() {
		return dichEntePaesaggistica;
	}
	public void setDichEntePaesaggistica(String dichEntePaesaggistica) {
		this.dichEntePaesaggistica = dichEntePaesaggistica;
	}
	public boolean isDichAutVidro() {
		return dichAutVidro;
	}
	public void setDichAutVidro(boolean dichAutVidro) {
		this.dichAutVidro = dichAutVidro;
	}
	public LocalDate getDichDataVidro() {
		return dichDataVidro;
	}
	public void setDichDataVidro(LocalDate dichDataVidro) {
		this.dichDataVidro = dichDataVidro;
	}
	public String getDichNrVidro() {
		return dichNrVidro;
	}
	public void setDichNrVidro(String dichNrVidro) {
		this.dichNrVidro = dichNrVidro;
	}
	public String getDichEnteVidro() {
		return dichEnteVidro;
	}
	public void setDichEnteVidro(String dichEnteVidro) {
		this.dichEnteVidro = dichEnteVidro;
	}
	public boolean isDichAutIncidenza() {
		return dichAutIncidenza;
	}
	public void setDichAutIncidenza(boolean dichAutIncidenza) {
		this.dichAutIncidenza = dichAutIncidenza;
	}
	public LocalDate getDichDataIncidenza() {
		return dichDataIncidenza;
	}
	public void setDichDataIncidenza(LocalDate dichDataIncidenza) {
		this.dichDataIncidenza = dichDataIncidenza;
	}
	public String getDichNrIncidenza() {
		return dichNrIncidenza;
	}
	public void setDichNrIncidenza(String dichNrIncidenza) {
		this.dichNrIncidenza = dichNrIncidenza;
	}
	public String getDichEnteIncidenza() {
		return dichEnteIncidenza;
	}
	public void setDichEnteIncidenza(String dichEnteIncidenza) {
		this.dichEnteIncidenza = dichEnteIncidenza;
	}
	public String getDichAltriPareri() {
		return dichAltriPareri;
	}
	public void setDichAltriPareri(String dichAltriPareri) {
		this.dichAltriPareri = dichAltriPareri;
	}
	public List<DichPropCatasto> getPropCatasti() {
		return propCatasti;
	}
	public void setPropCatasti(List<DichPropCatasto> propCatasti) {
		this.propCatasti = propCatasti;
	}
	public BigDecimal getSuperficieInteressata() {
		return superficieInteressata;
	}
	public void setSuperficieInteressata(BigDecimal superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}
	public String getRegionaleSoggetto() {
		return regionaleSoggetto;
	}
	public void setRegionaleSoggetto(String regionaleSoggetto) {
		this.regionaleSoggetto = regionaleSoggetto;
	}
	public List<IstanzaCompensazione> getIstanzeCompensazione() {
		return istanzeCompensazione;
	}
	public void setIstanzeCompensazione(List<IstanzaCompensazione> istanzaCompensazione) {
		this.istanzeCompensazione = istanzaCompensazione;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneratedFileParameters [tipoAllegato=");
		builder.append(tipoAllegato);
		builder.append(", idIntervento=");
		builder.append(idIntervento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", tipoTitolarita=");
		builder.append(tipoTitolarita);
		builder.append(", richCognome=");
		builder.append(richCognome);
		builder.append(", richNome=");
		builder.append(richNome);
		builder.append(", richRagSociale=");
		builder.append(richRagSociale);
		builder.append(", richCodiceFiscale=");
		builder.append(richCodiceFiscale);
		builder.append(", richPartitaIva=");
		builder.append(richPartitaIva);
		builder.append(", richIndirizzo=");
		builder.append(richIndirizzo);
		builder.append(", richCivico=");
		builder.append(richCivico);
		builder.append(", richProvincia=");
		builder.append(richProvincia);
		builder.append(", richCap=");
		builder.append(richCap);
		builder.append(", richComune=");
		builder.append(richComune);
		builder.append(", richTelefonico=");
		builder.append(richTelefonico);
		builder.append(", richEmail=");
		builder.append(richEmail);
		builder.append(", richPec=");
		builder.append(richPec);
		builder.append(", flgCompensazione=");
		builder.append(flgCompensazione);
		builder.append(", flgArt7a=");
		builder.append(flgArt7a);
		builder.append(", flgArt7b=");
		builder.append(flgArt7b);
		builder.append(", flgArt7c=");
		builder.append(flgArt7c);
		builder.append(", flgArt7d=");
		builder.append(flgArt7d);
		builder.append(", flgArt7dBis=");
		builder.append(flgArt7dBis);
		builder.append(", formaGovernoFlg1=");
		builder.append(formaGovernoFlg1);
		builder.append(", formaGovernoFlg2=");
		builder.append(formaGovernoFlg2);
		builder.append(", categForestFlg1=");
		builder.append(categForestFlg1);
		builder.append(", categForestFlg2=");
		builder.append(categForestFlg2);
		builder.append(", categForestFlg3=");
		builder.append(categForestFlg3);
		builder.append(", ubicazioneFlg1=");
		builder.append(ubicazioneFlg1);
		builder.append(", ubicazioneFlg2=");
		builder.append(ubicazioneFlg2);
		builder.append(", ubicazioneFlg3=");
		builder.append(ubicazioneFlg3);
		builder.append(", destVincFlg1=");
		builder.append(destVincFlg1);
		builder.append(", destVincFlg2=");
		builder.append(destVincFlg2);
		builder.append(", destVincFlg3=");
		builder.append(destVincFlg3);
		builder.append(", tipologiaFlg1=");
		builder.append(tipologiaFlg1);
		builder.append(", tipologiaFlg2=");
		builder.append(tipologiaFlg2);
		builder.append(", tipologiaFlg3=");
		builder.append(tipologiaFlg3);
		builder.append(", profCognome=");
		builder.append(profCognome);
		builder.append(", profNome=");
		builder.append(profNome);
		builder.append(", profCodiceFiscale=");
		builder.append(profCodiceFiscale);
		builder.append(", profProvinciaOrdine=");
		builder.append(profProvinciaOrdine);
		builder.append(", profNIscrizione=");
		builder.append(profNIscrizione);
		builder.append(", profTelefonico=");
		builder.append(profTelefonico);
		builder.append(", profPec=");
		builder.append(profPec);
		builder.append(", compenzazioneEuro=");
		builder.append(compenzazioneEuro);
		builder.append(", dichProprietario=");
		builder.append(dichProprietario);
		builder.append(", dichDissenso=");
		builder.append(dichDissenso);
		builder.append(", dichAutPaesaggistica=");
		builder.append(dichAutPaesaggistica);
		builder.append(", dichDataPaesaggistica=");
		builder.append(dichDataPaesaggistica);
		builder.append(", dichNrPaesaggistica=");
		builder.append(dichNrPaesaggistica);
		builder.append(", dichEntePaesaggistica=");
		builder.append(dichEntePaesaggistica);
		builder.append(", dichAutVidro=");
		builder.append(dichAutVidro);
		builder.append(", dichDataVidro=");
		builder.append(dichDataVidro);
		builder.append(", dichNrVidro=");
		builder.append(dichNrVidro);
		builder.append(", dichEnteVidro=");
		builder.append(dichEnteVidro);
		builder.append(", dichAutIncidenza=");
		builder.append(dichAutIncidenza);
		builder.append(", dichDataIncidenza=");
		builder.append(dichDataIncidenza);
		builder.append(", dichNrIncidenza=");
		builder.append(dichNrIncidenza);
		builder.append(", dichEnteIncidenza=");
		builder.append(dichEnteIncidenza);
		builder.append(", dichAltriPareri=");
		builder.append(dichAltriPareri);
		builder.append(", propCatasti=");
		builder.append(propCatasti);
		builder.append(", superficieInteressata=");
		builder.append(superficieInteressata);
		builder.append(", regionaleSoggetto=");
		builder.append(regionaleSoggetto);
		builder.append(", istanzeCompensazione=");
		builder.append(istanzeCompensazione);
		builder.append("]");
		return builder.toString();
	}
}