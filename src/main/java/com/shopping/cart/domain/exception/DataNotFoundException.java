package com.shopping.cart.domain.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable tw) {
        super(message, tw);
    }
}