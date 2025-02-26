package com.innowise.libraryapplicationsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInfoDto {

    private Long id;
    private String title;
    private String description;
    private Integer year;

}
