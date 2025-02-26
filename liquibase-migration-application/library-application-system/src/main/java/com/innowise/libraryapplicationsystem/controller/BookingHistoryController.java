package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.dto.BookingHistoryDto;
import com.innowise.libraryapplicationsystem.service.BookingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/bookingHistory")
public class BookingHistoryController {

    private final BookingHistoryService bookingHistoryService;

    @PostMapping
    public ResponseEntity<BookingHistoryDto> createBookingHistory(@RequestBody BookingHistoryDto bookingHistoryDto) {
        return new ResponseEntity<>(bookingHistoryService.saveBookingHistory(bookingHistoryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookingHistoryDto>> getAllBookingHistory() {
        return new ResponseEntity<>(bookingHistoryService.getAllBookingHistory(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingHistoryDto> getBookingHistoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookingHistoryService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookingHistory(@PathVariable("id") Long id) {
        bookingHistoryService.deleteBookingHistory(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BookingHistoryDto> updateBookingHistory(@RequestBody BookingHistoryDto bookingHistoryDto) {
        return new ResponseEntity<>(bookingHistoryService.updateBookingHistory(bookingHistoryDto), HttpStatus.OK);
    }

}
