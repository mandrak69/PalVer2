package it.csi.idf.idfapi.business.be.exceptions;


/**
 * Classe di eccezioni del <code>Servizio</code>.
 *
 * @author  1346 (Enrico Fusaro)
 */
public final class ServiceException extends Exception{

    private static final long serialVersionUID = 7583376730326946211L;

    public ServiceException(String message) {
		super(message);
	}

}
