package it.csi.idf.idfapi.dto;

public class PlainStringDTO {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainTextDTO [value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
