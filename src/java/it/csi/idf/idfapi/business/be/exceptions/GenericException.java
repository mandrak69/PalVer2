package it.csi.idf.idfapi.business.be.exceptions;

public class GenericException extends Exception {

	private static final long serialVersionUID = 3938065718787786482L;

	public GenericException() {}

	public GenericException(String message) {
		super(message);
	}
}
