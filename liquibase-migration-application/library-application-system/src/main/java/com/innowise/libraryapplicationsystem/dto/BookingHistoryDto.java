package com.innowise.libraryapplicationsystem.dto;

import com.innowise.libraryapplicationsystem.model.BookingStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingHistoryDto {

    private Long id;

    @NotNull(message = "Start date cannot be null")
    private LocalDate dateFrom;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be today or in the future")
    private LocalDate dateTo;

    @NotNull(message = "Status cannot be null")
    private BookingStatus status;

    @NotNull(message = "Book cannot be null")
    private BookDto bookDto;

    @NotNull(message = "User cannot be null")
    private UserDto userDto;

}
