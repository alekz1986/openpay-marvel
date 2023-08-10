package coderio.open.pay.marvel.exception.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthenticateException extends ResponseStatusException {

    private static final String DEFAULT_MESSAGE = "Usuario y/o clave inválida.";

    public AuthenticateException() {
        super(HttpStatus.UNAUTHORIZED, DEFAULT_MESSAGE);
    }

    public AuthenticateException(String reason, Throwable throwable) {
        super(HttpStatus.UNAUTHORIZED, reason, throwable);
    }
}
