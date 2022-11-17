package com.ao.webshop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

@Getter
public class WebshopException extends RuntimeException {
   private final HttpStatus status;
   private final URI path;

    public WebshopException(HttpStatus status, URI path, String message) {
        super(message);
        this.status = status;
        this.path = path;
    }
}
