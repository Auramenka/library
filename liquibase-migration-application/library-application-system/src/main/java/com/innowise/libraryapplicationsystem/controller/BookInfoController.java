package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.BookInfoDto;
import com.innowise.libraryapplicationsystem.dto.FilterBookDto;
import com.innowise.libraryapplicationsystem.service.BookInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(ApiConstants.BOOK_INFOS_ENDPOINT)
public class BookInfoController {

    private final BookInfoService bookInfoService;

    @PostMapping
    public ApiResponse<BookInfoDto> createBookInfo(@Valid @RequestBody BookInfoDto bookInfoDto) {
        return ApiResponse.<BookInfoDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(bookInfoService.saveBookInfo(bookInfoDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookInfoDto>> getAllBookInfo() {
        return ApiResponse.<List<BookInfoDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookInfoService.getAllBookInfo())
                .build();
    }

    @GetMapping(ApiConstants.SEARCH_ENDPOINT)
    public List<BookInfoDto> getAllFilterBooks(@RequestBody FilterBookDto filterBookDto, Pageable pageable) {
        return bookInfoService.getFilterBooks(filterBookDto, pageable);
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<BookInfoDto> getBookInfoById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<BookInfoDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookInfoService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteBookInfo(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        bookInfoService.deleteBookInfo(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<BookInfoDto> updateBookInfo(@Valid @RequestBody BookInfoDto bookInfoDto) {
        return ApiResponse.<BookInfoDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(bookInfoService.updateBookInfo(bookInfoDto))
                .build();
    }

}
