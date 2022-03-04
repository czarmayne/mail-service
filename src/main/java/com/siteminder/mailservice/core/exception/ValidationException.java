package com.siteminder.mailservice.core.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

@Slf4j
@Getter
public class ValidationException extends RuntimeException {
    public final Errors errors;

    ValidationException(Errors errors) {
        this(messageFor(errors), errors);
    }

    ValidationException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Creates a message out of the default error codes for each field
     */
    private static String messageFor(Errors errors) {
        var message = new StringBuffer("Validation exception with error codes: ");
        log.info("validation exception {}", message);
        message.append(errors.getAllErrors().stream().map(Object::toString)
                .collect(Collectors.joining(",")));
        return message.toString();

    }

    @Override
    public String toString() {
        return messageFor(errors);
    }

}