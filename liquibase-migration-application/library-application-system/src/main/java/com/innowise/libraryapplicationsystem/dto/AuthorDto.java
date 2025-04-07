package com.innowise.libraryapplicationsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {

    private Long id;

    @NotBlank(message = "Author name cannot be blank")
    private String name;

}
