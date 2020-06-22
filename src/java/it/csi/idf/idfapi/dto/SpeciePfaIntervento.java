package it.csi.idf.idfapi.dto;

public class SpeciePfaIntervento {

	private Integer idSpecie;
	private Integer idIntervento;
	private String flgSpeciePriorita;
	private Integer volumeSpecia;
	private String dataInserimento;
	private String dataAggiornamento;
	private Integer fkUdm;
	
	public Integer getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public String getFlgSpeciePriorita() {
		return flgSpeciePriorita;
	}
	public void setFlgSpeciePriorita(String flgSpeciePriorita) {
		this.flgSpeciePriorita = flgSpeciePriorita;
	}
	public Integer getVolumeSpecia() {
		return volumeSpecia;
	}
	public void setVolumeSpecia(Integer volumeSpecia) {
		this.volumeSpecia = volumeSpecia;
	}
	public Integer getFkUdm() {
		return fkUdm;
	}
	public void setFkUdm(Integer fkUdm) {
		this.fkUdm = fkUdm;
	}
	public String getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public String getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
}
