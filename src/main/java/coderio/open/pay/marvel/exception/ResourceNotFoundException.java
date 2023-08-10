package coderio.open.pay.marvel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    private static final String DEFAULT = "Resource not found";
    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND, DEFAULT);
    }

}
