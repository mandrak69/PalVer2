package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class TSpecie {
	
	private Integer idSpecie;
	private String codice;
	private String codGruppo;
	private String codicePignatti;
	private String latino;
	private String volgare;
	private String linkFoto;
	private String flg386;
	private String linkScheda;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	

	@JsonProperty("idSpecie")
	public Integer getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}

	@JsonProperty("codice")
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@JsonProperty("codGruppo")
	public String getCodGruppo() {
		return codGruppo;
	}

	public void setCodGruppo(String codGruppo) {
		this.codGruppo = codGruppo;
	}

	@JsonProperty("codicePignatti")
	public String getCodicePignatti() {
		return codicePignatti;
	}

	public void setCodicePignatti(String codicePignatti) {
		this.codicePignatti = codicePignatti;
	}

	@JsonProperty("latino")
	public String getLatino() {
		return latino;
	}

	public void setLatino(String latino) {
		this.latino = latino;
	}

	@JsonProperty("volgare")
	public String getVolgare() {
		return volgare;
	}

	public void setVolgare(String volgare) {
		this.volgare = volgare;
	}

	@JsonProperty("linkFoto")
	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}

	@JsonProperty("flg386")
	public String getFlg386() {
		return flg386;
	}

	public void setFlg386(String flg386) {
		this.flg386 = flg386;
	}

	@JsonProperty("linkScheda")
	public String getLinkScheda() {
		return linkScheda;
	}

	public void setLinkScheda(String linkScheda) {
		this.linkScheda = linkScheda;
	}

	
	@JsonProperty("mtdOrdinamento")
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}

	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	
	@JsonProperty("flgVisibile")
	public Byte getFlgVisibile() {
		return flgVisibile;
	}

	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TSpecie specie = (TSpecie) o;
		return Objects.equals(idSpecie, specie.idSpecie) && Objects.equals(codice, specie.codice)
				&& Objects.equals(codGruppo, specie.codGruppo) && Objects.equals(codicePignatti, specie.codicePignatti)
				&& Objects.equals(latino, specie.latino) && Objects.equals(volgare, specie.volgare)
				&& Objects.equals(linkScheda, specie.linkScheda) && Objects.equals(linkFoto, specie.linkFoto)
				&& Objects.equals(flg386, specie.flg386) && Objects.equals(mtdOrdinamento, specie.mtdOrdinamento) && Objects.equals(flgVisibile, specie.flgVisibile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSpecie, codice, codGruppo, codicePignatti, latino, volgare, linkFoto, linkScheda, flg386,
				mtdOrdinamento,flgVisibile);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TSpecie {\n");

		sb.append("    idSpecie: ").append(idSpecie).append("\n");
		sb.append("    codice: ").append(codice).append("\n");
		sb.append("    codGruppo: ").append(codGruppo).append("\n");
		sb.append("    codicePignatti: ").append(codicePignatti).append("\n");
		sb.append("    latino: ").append(latino).append("\n");
		sb.append("    volgare: ").append(volgare).append("\n");
		sb.append("    linkScheda: ").append(linkScheda).append("\n");
		sb.append("    linkFoto: ").append(linkFoto).append("\n");
		sb.append("    flg386: ").append(flg386).append("\n");
		sb.append("    mtdOrdinamento: ").append(mtdOrdinamento).append("\n");
		sb.append("    flgVisibile: ").append(flgVisibile).append("\n");
		sb.append("}");
		return sb.toString();
	}

}
