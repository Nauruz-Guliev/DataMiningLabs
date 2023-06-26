package ru.kpfu.itis.gnt.hwpebble.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends DatabaseException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
