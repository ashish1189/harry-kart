package se.atg.service.harrykart.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotEnoughParticipants extends RuntimeException {
    public NotEnoughParticipants(String message) {
        super(message);
    }
}
