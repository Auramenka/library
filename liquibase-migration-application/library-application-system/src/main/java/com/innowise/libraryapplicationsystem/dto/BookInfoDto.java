package com.innowise.libraryapplicationsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInfoDto {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 40, message = "Title must be between 2 and 40 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Year cannot be null")
    @Min(value = 1000, message = "Year must be greater than or equal to 1000")
    private Integer year;

}
