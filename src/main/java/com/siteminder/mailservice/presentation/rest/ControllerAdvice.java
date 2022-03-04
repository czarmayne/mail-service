package com.siteminder.mailservice.presentation.rest;

import com.siteminder.mailservice.core.constant.CustomError;
import com.siteminder.mailservice.core.dto.ErrorField;
import com.siteminder.mailservice.presentation.dto.ResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

//FIXME: ControllerAdvice for better error readability and not exposing too much info
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorField> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
                    log.error("Aggregate Statement: Field Validation Error: {}", fieldError.getField());
                    return new ErrorField(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                new ErrorDetail(ex.getMessage(), errors), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleServiceException(Exception ex) {
        log.error("General Exception: ", ex);
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .response(ex.getMessage())
                        .result(false)
                        .statusCode(CustomError.DEFAULT_SERVER_ERROR.getCode())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ErrorDetail {
        private String debugMessage;
        private List<ErrorField> errorFields;
    }
}
