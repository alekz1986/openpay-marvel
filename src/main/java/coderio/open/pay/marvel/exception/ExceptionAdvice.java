package coderio.open.pay.marvel.exception;

import coderio.open.pay.wrapper.api.marvel.exception.MarvelApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(MarvelApiException.class)
    public Mono<ResponseEntity<Map>> marvelApiException(MarvelApiException ex) {
        log.error("An exception of type MarvelAPIException occurred with the following information" +
                ex.getResponse().getBody(), ex);
        return Mono.just(ex.getResponse());
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map>> marvelApiException(Exception ex) {
        String message = "An unexpected error occurred, please contact your administrator";
        log.error(message, ex);
        Map<String, String> response = Map.of("message", message);
        return Mono.just(new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY));
    }

}
