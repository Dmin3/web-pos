package com.example.webpos.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> commonException(MethodArgumentNotValidException e) {
        ErrorResponse res = ErrorResponse.of(ErrorCode.MEMBER_NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.valueOf(res.getStatus()));
    }
}
