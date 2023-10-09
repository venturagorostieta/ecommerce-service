package com.tech.trove.infrastructure.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Forbidden exception.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Forbidden exception.
     *
     * @param message the message
     */
    public ForbiddenException(String message) {
        super(message);
    }

}
