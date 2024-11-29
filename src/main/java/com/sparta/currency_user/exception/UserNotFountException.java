package com.sparta.currency_user.exception;

//유저 정보를 찾을 수 없을 때 예외처리
public class UserNotFountException extends RuntimeException {
    public UserNotFountException(String message) {
        super(message);
    }
}
