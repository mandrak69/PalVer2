package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.util.List;

public class PlainQuintaSezione {
	
	private Long baseValue;
	private BigDecimal superficie;
	List<SceltiParameter> sceltiParametri;
	private BigDecimal totale;
	private TSoggetto soggettoProfess;
	
	public Long getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Long baseValue) {
		this.baseValue = baseValue;
	}
	public BigDecimal getSuperficie() {
		return superficie;
	}
	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}
	public List<SceltiParameter> getSceltiParametri() {
		return sceltiParametri;
	}
	public void setSceltiParametri(List<SceltiParameter> sceltiParametri) {
		this.sceltiParametri = sceltiParametri;
	}
	public BigDecimal getTotale() {
		return totale;
	}
	public void setTotale(BigDecimal totale) {
		this.totale = totale;
	}
	public TSoggetto getSoggettoProfess() {
		return soggettoProfess;
	}
	public void setSoggettoProfess(TSoggetto soggettoProfess) {
		this.soggettoProfess = soggettoProfess;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainQuintaSezione [baseValue=");
		builder.append(baseValue);
		builder.append(", superficie=");
		builder.append(superficie);
		builder.append(", sceltiParametri=");
		builder.append(sceltiParametri);
		builder.append(", totale=");
		builder.append(totale);
		builder.append(", soggettoProfess=");
		builder.append(soggettoProfess);
		builder.append("]");
		return builder.toString();
	}
}
