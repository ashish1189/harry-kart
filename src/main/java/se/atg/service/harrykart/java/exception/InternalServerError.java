package se.atg.service.harrykart.java.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException {

    public InternalServerError(String message) {
        super(message);
    }
}
