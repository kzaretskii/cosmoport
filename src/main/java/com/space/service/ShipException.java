package com.space.service;

import org.springframework.http.HttpStatus;

public class ShipException extends RuntimeException{
    private HttpStatus httpStatus;

    public ShipException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
