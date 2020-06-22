package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoLnEvento {

	private Integer idgeoLnEvento;
	private LocalDate dataInserimento;
	private Object geometria;
	private Integer fkEvento;
	
	public Integer getIdgeoLnEvento() {
		return idgeoLnEvento;
	}
	public void setIdgeoLnEvento(Integer idgeoLnEvento) {
		this.idgeoLnEvento = idgeoLnEvento;
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
		builder.append("GeoLnEvento [idgeoLnEvento=");
		builder.append(idgeoLnEvento);
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