package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class PlainParticelleCatastali {
	
	private Integer id;
	private ComuneResource comune;
	private String sezione;
	private Integer foglio;
	private String particella;
	private BigDecimal supCatastale;
	private boolean toDelete;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
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
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public BigDecimal getSupCatastale() {
		return supCatastale;
	}
	public void setSupCatastale(BigDecimal supCatastale) {
		this.supCatastale = supCatastale;
	}
	public boolean isToDelete() {
		return toDelete;
	}
	public void setToDelete(boolean toDelete) {
		this.toDelete = toDelete;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainParticelleCatastali [id=");
		builder.append(id);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", sezione=");
		builder.append(sezione);
		builder.append(", foglio=");
		builder.append(foglio);
		builder.append(", particella=");
		builder.append(particella);
		builder.append(", supCatastale=");
		builder.append(supCatastale);
		builder.append("]");
		return builder.toString();
	}
}
