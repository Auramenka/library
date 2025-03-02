package com.innowise.libraryapplicationsystem.exceptions;

import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NotExistsException.class)
    public ApiResponse<String> handleException(NotExistsException e) {
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .exception(e.getMessage())
                .build();
    }

}
