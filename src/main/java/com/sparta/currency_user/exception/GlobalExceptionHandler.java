package com.sparta.currency_user.exception;

import jakarta.persistence.EntityNotFoundException;
import com.sparta.currency_user.exception.UserNotFountException;
import com.sparta.currency_user.exception.CurrencyNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> illegalArgumentException(IllegalArgumentException e) {
        Map<String, String> response = new HashMap<>();
        response.put("errorCode", "ERR400");
        response.put("errorMessage", e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFountException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFountException e) {
        Map<String, String> response = new HashMap<>();
        response.put("errorCode", "ERR404");
        response.put("errorMessage", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Map<String, String>> entityNotFoundException(CurrencyNotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("errorCode", "ERR404");
        response.put("errorMessage", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ExchangeRequestEmptyException.class)
    public ResponseEntity<Map<String, String>> exchangeRequestNotFoundException(ExchangeRequestEmptyException e) {
        Map<String, String> response = new HashMap<>();
        response.put("errorCode", "ERR404");
        response.put("errorMessage", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> invalidEmailException(ConstraintViolationException e) {
        Map<String, String> response = new HashMap<>();
        String errorMessage = e.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())  // 위반된 메시지를 추출
                .collect(Collectors.joining(", "));  // 여러 메시지가 있을 경우 콤마로 구분

        response.put("errorCode", "ERR400");
        response.put("errorMessage", "유효하지 않은 입력값입니다: " + errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
