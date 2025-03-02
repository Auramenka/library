package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.BookingHistoryDto;
import com.innowise.libraryapplicationsystem.service.BookingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.BOOKING_HISTORY_ENDPOINT)
public class BookingHistoryController {

    private final BookingHistoryService bookingHistoryService;

    @PostMapping
    public ApiResponse<BookingHistoryDto> createBookingHistory(@RequestBody BookingHistoryDto bookingHistoryDto) {
        return ApiResponse.<BookingHistoryDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(bookingHistoryService.saveBookingHistory(bookingHistoryDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookingHistoryDto>> getAllBookingHistory() {
        return ApiResponse.<List<BookingHistoryDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookingHistoryService.getAllBookingHistory())
                .build();
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<BookingHistoryDto> getBookingHistoryById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<BookingHistoryDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookingHistoryService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteBookingHistory(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        bookingHistoryService.deleteBookingHistory(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<BookingHistoryDto> updateBookingHistory(@RequestBody BookingHistoryDto bookingHistoryDto) {
        return ApiResponse.<BookingHistoryDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookingHistoryService.updateBookingHistory(bookingHistoryDto))
                .build();
    }

}
