package com.roxy.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常类
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AddException extends RuntimeException {
    public AddException() {
    }

    public AddException(String message) {
        super(message);
    }

    public AddException(String message, Throwable cause) {
        super(message, cause);
    }
}
