package com.roxy.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常类
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DeleteException extends RuntimeException {
    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
