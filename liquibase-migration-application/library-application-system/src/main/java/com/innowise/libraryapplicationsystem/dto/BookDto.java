package com.innowise.libraryapplicationsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private Long id;

    @NotNull(message = "BookInfoDto cannot be null")
    private BookInfoDto bookInfoDto;

}
