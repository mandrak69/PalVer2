package it.csi.idf.idfapi.dto;

public class GeoPlPfaExcelDTO {

	private Integer idGeoPfa;
	
	public Integer getIdGeoPfa() {
		return idGeoPfa;
	}
	public void setIdGeoPfa(Integer idGeoPfa) {
		this.idGeoPfa = idGeoPfa;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPlPfaExcelDTO [idGeoPfa=");
		builder.append(idGeoPfa);
		builder.append("]");
		return builder.toString();
	}
}