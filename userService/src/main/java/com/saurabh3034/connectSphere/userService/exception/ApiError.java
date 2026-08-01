package com.saurabh3034.connectSphere.userService.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String error;
    private LocalDateTime timestamp;
    private HttpStatus statusCode;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus statusCode) {
        this();
        this.error = message;
        this.statusCode = statusCode;
    }
}
