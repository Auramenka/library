package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.BookDto;
import com.innowise.libraryapplicationsystem.service.BookService;
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
@RequestMapping(ApiConstants.BOOKS_ENDPOINT)
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ApiResponse<BookDto> createBook(@RequestBody BookDto bookDto) {
        return ApiResponse.<BookDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(bookService.saveBook(bookDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookDto>> getAllBooks() {
        return ApiResponse.<List<BookDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookService.getAllBooks())
                .build();
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<BookDto> getBookById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<BookDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteBook(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        bookService.deleteBook(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<BookDto> updateBook(@RequestBody BookDto bookDto) {
        return ApiResponse.<BookDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookService.updateBook(bookDto))
                .build();
    }

}
