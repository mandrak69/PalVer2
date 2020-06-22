package it.csi.idf.idfapi.dto;

public class Combustibile {
	private Integer idgeoPtAds;
	private String codCombustibile;	
	private Integer percCoperturaLettiera;
	private Integer percCoperturaErbacea;
	private Integer percCoperturaCespugli;
	private Byte flgPrincipale;
	public Integer getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Integer idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	public String getCodCombustibile() {
		return codCombustibile;
	}
	public void setCodCombustibile(String codCombustibile) {
		this.codCombustibile = codCombustibile;
	}
	public Integer getPercCoperturaLettiera() {
		return percCoperturaLettiera;
	}
	public void setPercCoperturaLettiera(Integer percCoperturaLettiera) {
		this.percCoperturaLettiera = percCoperturaLettiera;
	}
	public Integer getPercCoperturaErbacea() {
		return percCoperturaErbacea;
	}
	public void setPercCoperturaErbacea(Integer percCoperturaErbacea) {
		this.percCoperturaErbacea = percCoperturaErbacea;
	}
	public Integer getPercCoperturaCespugli() {
		return percCoperturaCespugli;
	}
	public void setPercCoperturaCespugli(Integer percCoperturaCespugli) {
		this.percCoperturaCespugli = percCoperturaCespugli;
	}
	public Byte getFlgPrincipale() {
		return flgPrincipale;
	}
	public void setFlgPrincipale(Byte flgPrincipale) {
		this.flgPrincipale = flgPrincipale;
	}
}
