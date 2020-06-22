package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PropCatasto {

	private Integer idGeoPlPropCatasto;
	private Integer idGeoPlPfa;
	private Integer fkComune;
	private String sezione;
	private Integer foglio;
	private String allegatoCatasto;
	private String sviluppoCatasto;
	private String particella;
	private BigDecimal supCatastaleMq;
	private BigDecimal supCartograficaHa;
	private Integer fkProprieta;
	private String intestato;
	private String qualitaColtura;
	private Byte flgUsiCivici;
	private Byte flgPossessiContest;
	private Byte flgLivellari;
	private LocalDate dataInizioValidita;//not null
	private LocalDate dataFineValidita;
	private String note;
	private LocalDate dataAggiornamentoDatocatast;
	private LocalDate dataInserimento;
	//private Object geometria;
	private Integer fkConfigUtente;
	
	public Integer getIdGeoPlPropCatasto() {
		return idGeoPlPropCatasto;
	}
	public void setIdGeoPlPropCatasto(Integer idGeoPlPropCatasto) {
		this.idGeoPlPropCatasto = idGeoPlPropCatasto;
	}
	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}
	public void setIdGeoPlPfa(Integer idGeoPlPFA) {
		this.idGeoPlPfa = idGeoPlPFA;
	}
	public Integer getFkComune() {
		return fkComune;
	}
	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public Integer getFoglio() {
		return foglio;
	}
	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}
	public String getAllegatoCatasto() {
		return allegatoCatasto;
	}
	public void setAllegatoCatasto(String allegatoCatasto) {
		this.allegatoCatasto = allegatoCatasto;
	}
	public String getSviluppoCatasto() {
		return sviluppoCatasto;
	}
	public void setSviluppoCatasto(String sviluppoCatasto) {
		this.sviluppoCatasto = sviluppoCatasto;
	}
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public BigDecimal getSupCatastaleMq() {
		return supCatastaleMq;
	}
	public void setSupCatastaleMq(BigDecimal supCatastaleMq) {
		this.supCatastaleMq = supCatastaleMq;
	}
	public BigDecimal getSupCartograficaHa() {
		return supCartograficaHa;
	}
	public void setSupCartograficaHa(BigDecimal supCartograficaHa) {
		this.supCartograficaHa = supCartograficaHa;
	}
	public Integer getFkProprieta() {
		return fkProprieta;
	}
	public void setFkProprieta(Integer fk_proprieta) {
		this.fkProprieta = fk_proprieta;
	}
	public String getIntestato() {
		return intestato;
	}
	public void setIntestato(String intestato) {
		this.intestato = intestato;
	}
	public String getQualitaColtura() {
		return qualitaColtura;
	}
	public void setQualitaColtura(String qualitaColtura) {
		this.qualitaColtura = qualitaColtura;
	}
	public Byte getFlgUsiCivici() {
		return flgUsiCivici;
	}
	public void setFlgUsiCivici(Byte flgUsiCivici) {
		this.flgUsiCivici = flgUsiCivici;
	}
	public Byte getFlgPossessiContest() {
		return flgPossessiContest;
	}
	public void setFlgPossessiContest(Byte flgPossessiContest) {
		this.flgPossessiContest = flgPossessiContest;
	}
	public Byte getFlgLivellari() {
		return flgLivellari;
	}
	public void setFlgLivellari(Byte flgLivellari) {
		this.flgLivellari = flgLivellari;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public LocalDate getDataAggiornamentoDatocatast() {
		return dataAggiornamentoDatocatast;
	}
	public void setDataAggiornamentoDatocatast(LocalDate dataAggiornamentoDatocatast) {
		this.dataAggiornamentoDatocatast = dataAggiornamentoDatocatast;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	/*public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}*/
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allegatoCatasto == null) ? 0 : allegatoCatasto.hashCode());
		result = prime * result + ((dataAggiornamentoDatocatast == null) ? 0 : dataAggiornamentoDatocatast.hashCode());
		result = prime * result + ((dataFineValidita == null) ? 0 : dataFineValidita.hashCode());
		result = prime * result + ((dataInizioValidita == null) ? 0 : dataInizioValidita.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((fkConfigUtente == null) ? 0 : fkConfigUtente.hashCode());
		result = prime * result + ((fkComune == null) ? 0 : fkComune.hashCode());
		result = prime * result + ((fkProprieta == null) ? 0 : fkProprieta.hashCode());
		result = prime * result + ((flgLivellari == null) ? 0 : flgLivellari.hashCode());
		result = prime * result + ((flgPossessiContest == null) ? 0 : flgPossessiContest.hashCode());
		result = prime * result + ((flgUsiCivici == null) ? 0 : flgUsiCivici.hashCode());
		result = prime * result + ((foglio == null) ? 0 : foglio.hashCode());
		//result = prime * result + ((geometria == null) ? 0 : geometria.hashCode());
		result = prime * result + ((idGeoPlPfa == null) ? 0 : idGeoPlPfa.hashCode());
		result = prime * result + ((idGeoPlPropCatasto == null) ? 0 : idGeoPlPropCatasto.hashCode());
		result = prime * result + ((intestato == null) ? 0 : intestato.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((particella == null) ? 0 : particella.hashCode());
		result = prime * result + ((qualitaColtura == null) ? 0 : qualitaColtura.hashCode());
		result = prime * result + ((sezione == null) ? 0 : sezione.hashCode());
		result = prime * result + ((supCartograficaHa == null) ? 0 : supCartograficaHa.hashCode());
		result = prime * result + ((supCatastaleMq == null) ? 0 : supCatastaleMq.hashCode());
		result = prime * result + ((sviluppoCatasto == null) ? 0 : sviluppoCatasto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropCatasto other = (PropCatasto) obj;
		if (allegatoCatasto == null) {
			if (other.allegatoCatasto != null)
				return false;
		} else if (!allegatoCatasto.equals(other.allegatoCatasto))
			return false;
		if (dataAggiornamentoDatocatast == null) {
			if (other.dataAggiornamentoDatocatast != null)
				return false;
		} else if (!dataAggiornamentoDatocatast.equals(other.dataAggiornamentoDatocatast))
			return false;
		if (dataFineValidita == null) {
			if (other.dataFineValidita != null)
				return false;
		} else if (!dataFineValidita.equals(other.dataFineValidita))
			return false;
		if (dataInizioValidita == null) {
			if (other.dataInizioValidita != null)
				return false;
		} else if (!dataInizioValidita.equals(other.dataInizioValidita))
			return false;
		if (dataInserimento == null) {
			if (other.dataInserimento != null)
				return false;
		} else if (!dataInserimento.equals(other.dataInserimento))
			return false;
		if (fkConfigUtente == null) {
			if (other.fkConfigUtente != null)
				return false;
		} else if (!fkConfigUtente.equals(other.fkConfigUtente))
			return false;
		if (fkComune == null) {
			if (other.fkComune != null)
				return false;
		} else if (!fkComune.equals(other.fkComune))
			return false;
		if (fkProprieta == null) {
			if (other.fkProprieta != null)
				return false;
		} else if (!fkProprieta.equals(other.fkProprieta))
			return false;
		if (flgLivellari == null) {
			if (other.flgLivellari != null)
				return false;
		} else if (!flgLivellari.equals(other.flgLivellari))
			return false;
		if (flgPossessiContest == null) {
			if (other.flgPossessiContest != null)
				return false;
		} else if (!flgPossessiContest.equals(other.flgPossessiContest))
			return false;
		if (flgUsiCivici == null) {
			if (other.flgUsiCivici != null)
				return false;
		} else if (!flgUsiCivici.equals(other.flgUsiCivici))
			return false;
		if (foglio == null) {
			if (other.foglio != null)
				return false;
		} else if (!foglio.equals(other.foglio))
			return false;
		/*if (geometria == null) {
			if (other.geometria != null)
				return false;
		} else if (!geometria.equals(other.geometria))
			return false;*/
		if (idGeoPlPfa == null) {
			if (other.idGeoPlPfa != null)
				return false;
		} else if (!idGeoPlPfa.equals(other.idGeoPlPfa))
			return false;
		if (idGeoPlPropCatasto == null) {
			if (other.idGeoPlPropCatasto != null)
				return false;
		} else if (!idGeoPlPropCatasto.equals(other.idGeoPlPropCatasto))
			return false;
		if (intestato == null) {
			if (other.intestato != null)
				return false;
		} else if (!intestato.equals(other.intestato))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (particella == null) {
			if (other.particella != null)
				return false;
		} else if (!particella.equals(other.particella))
			return false;
		if (qualitaColtura == null) {
			if (other.qualitaColtura != null)
				return false;
		} else if (!qualitaColtura.equals(other.qualitaColtura))
			return false;
		if (sezione == null) {
			if (other.sezione != null)
				return false;
		} else if (!sezione.equals(other.sezione))
			return false;
		if (supCartograficaHa == null) {
			if (other.supCartograficaHa != null)
				return false;
		} else if (!supCartograficaHa.equals(other.supCartograficaHa))
			return false;
		if (supCatastaleMq == null) {
			if (other.supCatastaleMq != null)
				return false;
		} else if (!supCatastaleMq.equals(other.supCatastaleMq))
			return false;
		if (sviluppoCatasto == null) {
			if (other.sviluppoCatasto != null)
				return false;
		} else if (!sviluppoCatasto.equals(other.sviluppoCatasto))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropCatasto [idGeoPlPropCatasto=");
		builder.append(idGeoPlPropCatasto);
		builder.append(", idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", fkComune=");
		builder.append(fkComune);
		builder.append(", sezione=");
		builder.append(sezione);
		builder.append(", foglio=");
		builder.append(foglio);
		builder.append(", allegatoCatasto=");
		builder.append(allegatoCatasto);
		builder.append(", sviluppoCatasto=");
		builder.append(sviluppoCatasto);
		builder.append(", particella=");
		builder.append(particella);
		builder.append(", supCatastaleMq=");
		builder.append(supCatastaleMq);
		builder.append(", supCartograficaHa=");
		builder.append(supCartograficaHa);
		builder.append(", fkProprieta=");
		builder.append(fkProprieta);
		builder.append(", intestato=");
		builder.append(intestato);
		builder.append(", qualitaColtura=");
		builder.append(qualitaColtura);
		builder.append(", flgUsiCivici=");
		builder.append(flgUsiCivici);
		builder.append(", flgPossessiContest=");
		builder.append(flgPossessiContest);
		builder.append(", flgLivellari=");
		builder.append(flgLivellari);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", note=");
		builder.append(note);
		builder.append(", dataAggiornamentoDatocatast=");
		builder.append(dataAggiornamentoDatocatast);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append("]");
		return builder.toString();
	}
}
