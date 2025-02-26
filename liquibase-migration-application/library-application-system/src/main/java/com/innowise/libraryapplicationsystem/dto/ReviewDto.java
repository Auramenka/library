package com.innowise.libraryapplicationsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDto {

    private Long id;
    private Integer rating;
    private String comment;
    private LocalDate created_at;
    private UserDto userDto;
    private BookInfoDto bookInfoDto;

}
