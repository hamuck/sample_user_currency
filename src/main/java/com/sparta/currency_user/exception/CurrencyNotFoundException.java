package com.sparta.currency_user.exception;

//통화 정보를 찾을 수 없을 때 예외처리
public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
