package com.innowise.libraryapplicationsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private Integer statusCode;

    private T body;

    private String exception;

}
