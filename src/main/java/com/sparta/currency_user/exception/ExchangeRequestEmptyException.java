package com.sparta.currency_user.exception;

public class ExchangeRequestEmptyException extends RuntimeException {
    public ExchangeRequestEmptyException(String message) {
        super(message);
    }
}
