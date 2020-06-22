package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoLnLottoIntervento {

	private Integer idgeoLnLottoIntervento;
	private LocalDate datainserimento;
	private Object geometria;
	private Integer idIntervento;
	
	public Integer getIdgeoLnLottoIntervento() {
		return idgeoLnLottoIntervento;
	}
	public void setIdgeoLnLottoIntervento(Integer idgeoLnLottoIntervento) {
		this.idgeoLnLottoIntervento = idgeoLnLottoIntervento;
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
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoLnLottoIntervento [idgeoLnLottoIntervento=");
		builder.append(idgeoLnLottoIntervento);
		builder.append(", datainserimento=");
		builder.append(datainserimento);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", idIntervento=");
		builder.append(idIntervento);
		builder.append("]");
		return builder.toString();
	}
}