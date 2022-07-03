package se.atg.service.harrykart.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoRaceParticipantsException extends RuntimeException {
    public NoRaceParticipantsException(String message) {
        super(message);
    }
}
