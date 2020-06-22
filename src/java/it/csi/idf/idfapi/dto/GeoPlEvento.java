package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoPlEvento {
	
	private Integer idgeoPlEvento;
	private LocalDate dataInserimento;
	private Object geometria;
	private Integer fkEvento;
	
	public Integer getIdgeoPlEvento() {
		return idgeoPlEvento;
	}
	public void setIdgeoPlEvento(Integer idgeoPlEvento) {
		this.idgeoPlEvento = idgeoPlEvento;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public Integer getFkEvento() {
		return fkEvento;
	}
	public void setFkEvento(Integer fkEvento) {
		this.fkEvento = fkEvento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPlEvento [idgeoPlEvento=");
		builder.append(idgeoPlEvento);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", fkEvento=");
		builder.append(fkEvento);
		builder.append("]");
		return builder.toString();
	}
}