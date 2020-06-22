package it.csi.idf.idfapi.dto;

public class RicadenzaInformazioni {
	
	public RicadenzaInformazioni() {}
	
	public RicadenzaInformazioni(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}
	
	private String codiceAmministrativo;
	private String nome;
	private Integer percentualeRicadenza;
	
	public String getCodiceAmministrativo() {
		return codiceAmministrativo;
	}
	public void setCodiceAmministrativo(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPercentualeRicadenza() {
		return percentualeRicadenza;
	}
	public void setPercentualeRicadenza(Integer percentualeRicadenza) {
		this.percentualeRicadenza = percentualeRicadenza;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceAmministrativo == null) ? 0 : codiceAmministrativo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RicadenzaInformazioni other = (RicadenzaInformazioni) obj;
		if (codiceAmministrativo == null) {
			if (other.codiceAmministrativo != null)
				return false;
		} else if (!codiceAmministrativo.equals(other.codiceAmministrativo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RicadenzaInformazioni [codiceAmministrativo=");
		builder.append(codiceAmministrativo);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", percentualeRicadenza=");
		builder.append(percentualeRicadenza);
		builder.append("]");
		return builder.toString();
	}
}
