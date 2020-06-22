package it.csi.idf.idfapi.dto;

public class PlainBackOfficeInfo {
	
	private boolean isAllowed;
	private String message;
	
	public boolean isAllowed() {
		return isAllowed;
	}
	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainBackOfficeInfo [isAllowed=");
		builder.append(isAllowed);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}
