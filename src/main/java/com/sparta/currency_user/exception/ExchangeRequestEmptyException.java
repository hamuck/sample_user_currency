package com.sparta.currency_user.exception;

//환전 요청 찾을 수 없을때 예외처리
public class ExchangeRequestEmptyException extends RuntimeException {
    public ExchangeRequestEmptyException(String message) {
        super(message);
    }
}
