package com.roxy.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常类
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFountException extends RuntimeException {
    public NotFountException() {
    }

    public NotFountException(String message) {
        super(message);
    }

    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}
