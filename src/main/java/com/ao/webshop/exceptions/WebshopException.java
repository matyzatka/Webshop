package com.ao.webshop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebshopException extends RuntimeException {
   private final HttpStatus status;
   private final String path;

    public WebshopException(HttpStatus status, String path, String message) {
        super(message);
        this.status = status;
        this.path = path;
    }
}
