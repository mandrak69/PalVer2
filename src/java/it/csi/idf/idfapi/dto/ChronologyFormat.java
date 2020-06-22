package it.csi.idf.idfapi.dto;

public class ChronologyFormat {
	
	private String id;
	private String calendarType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCalendarType() {
		return calendarType;
	}
	public void setCalendarType(String calendarType) {
		this.calendarType = calendarType;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChronologyFormat [id=");
		builder.append(id);
		builder.append(", calendarType=");
		builder.append(calendarType);
		builder.append("]");
		return builder.toString();
	}
}
