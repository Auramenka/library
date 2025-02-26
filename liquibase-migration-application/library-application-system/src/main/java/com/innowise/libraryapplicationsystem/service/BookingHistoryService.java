package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.BookingHistoryDto;

import java.util.List;

public interface BookingHistoryService {

    BookingHistoryDto saveBookingHistory(BookingHistoryDto bookingHistoryDto);
    List<BookingHistoryDto> getAllBookingHistory();
    void deleteBookingHistory(Long id);
    BookingHistoryDto updateBookingHistory(BookingHistoryDto bookingHistoryDto);
    BookingHistoryDto findById(Long id);

}
