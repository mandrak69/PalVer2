package it.csi.idf.idfapi.business.be.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {   /// extends Exception{

	private static final long serialVersionUID = 8702644029945350717L;
	
	public ValidationException() {}

	public ValidationException(String string) {
		super(string);
	}
}
