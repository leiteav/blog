package com.blog.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BlogException {
    private int status;
    private String message;
    public BlogException(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }
}
