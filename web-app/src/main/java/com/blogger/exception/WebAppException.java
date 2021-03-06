package com.blogger.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bad request")
public class WebAppException extends RuntimeException {

    private static final long serialVersionUID = -4286020057705515354L;

    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(Throwable t) {
        super(t);
    }

    public WebAppException(String message, Throwable t) {
        super(message, t);
    }
}
