package com.roxy.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常类
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UploadException extends RuntimeException {
    public UploadException() {
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
