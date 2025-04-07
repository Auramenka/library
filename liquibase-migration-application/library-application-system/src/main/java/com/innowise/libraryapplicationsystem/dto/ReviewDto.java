package com.innowise.libraryapplicationsystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDto {

    private Long id;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @Size(min = 5, max = 500, message = "Comment must be between 5 and 500 characters")
    private String comment;

    @NotNull(message = "Creation date cannot be null")
    private LocalDate created_at;

    @NotNull(message = "User cannot be null")
    private UserDto userDto;

    @NotNull(message = "BookInfo cannot be null")
    private BookInfoDto bookInfoDto;

}
