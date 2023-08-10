package coderio.open.pay.marvel.exception;

public class TechnicalException extends Exception {

    private static final String DEFAULT_CLIENT_MESSAGE = "Ocurrió un error procesando su solicitud.";

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

}
