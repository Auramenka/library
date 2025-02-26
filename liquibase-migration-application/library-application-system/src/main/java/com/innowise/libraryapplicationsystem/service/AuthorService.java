package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto saveAuthor(AuthorDto authorDto);
    List<AuthorDto> getAllAuthors();
    void deleteAuthor(Long id);
    AuthorDto updateAuthor(AuthorDto authorDto);
    AuthorDto findById(Long id);

}
