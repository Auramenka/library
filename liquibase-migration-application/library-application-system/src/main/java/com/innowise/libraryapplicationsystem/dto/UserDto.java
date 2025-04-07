package com.innowise.libraryapplicationsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must be in the format user@example.com")
    private String email;

    @NotBlank(message = "Phone can't be empty")
    private String phone;

}
