package com.ao.webshop.exceptions;

import org.springframework.http.HttpStatus;

public class WebshopServiceException extends Throwable {
    private final HttpStatus status;
    private final String message;

    public WebshopServiceException(HttpStatus status, String message) {
       this.status = status;
       this.message = message;
    }
}
