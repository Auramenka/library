package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto saveBook(BookDto bookDto);
    List<BookDto> getAllBooks();
    void deleteBook(Long id);
    BookDto updateBook(BookDto bookDto);
    BookDto findById(Long id);

}
