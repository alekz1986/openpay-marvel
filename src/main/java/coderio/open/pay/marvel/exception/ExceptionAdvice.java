package coderio.open.pay.marvel.exception;

import coderio.open.pay.marvel.exception.authentication.AuthenticateException;
import coderio.open.pay.wrapper.api.marvel.exception.MarvelApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(MarvelApiException.class)
    public ResponseEntity<Map> marvelApiException(MarvelApiException ex) {
        log.error("An exception of type MarvelAPIException occurred with the following information" +
                ex.getResponse().getBody(), ex);
        return ex.getResponse();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> marvelApiException(Exception ex) {
        String message = "An unexpected error occurred, please contact your administrator";
        log.error(message, ex);
        Map<String, String> response = Map.of("message", message);
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Set<String>> validationException(ConstraintViolationException ex) {
        log.error("The validations were not passed", ex);
        return new ResponseEntity<>(ex.getConstraintViolations().stream()
                        .map(x -> x.getMessage())
                        .collect(Collectors.toSet()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Map> application(ResponseStatusException ex) {
        log.error("ResponseStatusException error", ex);
        return new ResponseEntity<>(Map.of("message", ex.getReason()), ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map> application(MethodArgumentNotValidException ex) {
        log.error("Validation error", ex);

        List<String> errors = ex.getAllErrors().stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(Map.of("errors", errors), HttpStatus.BAD_REQUEST);

    }

}
