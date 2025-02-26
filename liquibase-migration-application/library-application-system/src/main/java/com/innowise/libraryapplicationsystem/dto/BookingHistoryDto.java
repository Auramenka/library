package com.innowise.libraryapplicationsystem.dto;

import com.innowise.libraryapplicationsystem.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingHistoryDto {

    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Status status;
    private BookDto bookDto;
    private UserDto userDto;

}
