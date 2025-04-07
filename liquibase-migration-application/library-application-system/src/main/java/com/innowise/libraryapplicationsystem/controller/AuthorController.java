package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.AuthorDto;
import com.innowise.libraryapplicationsystem.service.AuthorService;
import jakarta.validation.Valid;
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
@RequestMapping(ApiConstants.AUTHORS_ENDPOINT)
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ApiResponse<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return ApiResponse.<AuthorDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(authorService.saveAuthor(authorDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<AuthorDto>> getAllAuthors() {
        return ApiResponse.<List<AuthorDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(authorService.getAllAuthors())
                .build();
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<AuthorDto> getAuthorById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<AuthorDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(authorService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteAuthor(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        authorService.deleteAuthor(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<AuthorDto> updateAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return ApiResponse.<AuthorDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(authorService.updateAuthor(authorDto))
                .build();
    }

}
