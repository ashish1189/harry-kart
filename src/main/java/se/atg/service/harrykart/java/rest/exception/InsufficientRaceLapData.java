package se.atg.service.harrykart.java.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientRaceLapData extends RuntimeException {
    public InsufficientRaceLapData(String message) {
        super(message);
    }
}