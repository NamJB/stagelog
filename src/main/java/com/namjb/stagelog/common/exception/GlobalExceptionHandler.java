package com.namjb.stagelog.common.exception;


import com.namjb.stagelog.common.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            errors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse response = new ErrorResponse(
                "Invalid_input",
                "입력값을 확인해주세요",
                errors);

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidInputException ex) {

        Map<String,String> errors =  new HashMap<>();
        errors.put(ex.getFieldName(),ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                "Invalid_input",
                "입력값을 확인해주세요",
                errors

        );

        return ResponseEntity
                .badRequest()
                .body(response);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {

        log.error("요청 처리중 예상하지 못한 오류 발생",ex);

        ErrorResponse response = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "서버 오류 발생",
                Map.of());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
