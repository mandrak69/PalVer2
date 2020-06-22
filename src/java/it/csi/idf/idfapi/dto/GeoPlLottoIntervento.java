package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoPlLottoIntervento {
	
	private Integer idgeoPlLottoIntervento;
	private LocalDate datainserimento;
	private Object geometria;
	private Integer fkIntervento;
	
	public Integer getIdgeoPlLottoIntervento() {
		return idgeoPlLottoIntervento;
	}
	public void setIdgeoPlLottoIntervento(Integer idgeoPlLottoIntervento) {
		this.idgeoPlLottoIntervento = idgeoPlLottoIntervento;
	}
	public LocalDate getDatainserimento() {
		return datainserimento;
	}
	public void setDatainserimento(LocalDate datainserimento) {
		this.datainserimento = datainserimento;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public Integer getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(Integer fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPlLottoIntervento [idgeoPlLottoIntervento=");
		builder.append(idgeoPlLottoIntervento);
		builder.append(", datainserimento=");
		builder.append(datainserimento);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", fkIntervento=");
		builder.append(fkIntervento);
		builder.append("]");
		return builder.toString();
	}
}