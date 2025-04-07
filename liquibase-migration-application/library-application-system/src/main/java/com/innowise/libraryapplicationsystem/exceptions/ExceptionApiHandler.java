package com.innowise.libraryapplicationsystem.exceptions;

import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(EntityNotExistsException.class)
    public ApiResponse<String> handleException(EntityNotExistsException e) {
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .exception(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleViolationException(MethodArgumentNotValidException exception){
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .exception(exception.getMessage())
                .build();
    }

}
