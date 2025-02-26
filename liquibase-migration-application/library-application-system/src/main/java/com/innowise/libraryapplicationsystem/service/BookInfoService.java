package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.BookInfoDto;

import java.util.List;

public interface BookInfoService {

    BookInfoDto saveBookInfo(BookInfoDto bookInfoDto);
    List<BookInfoDto> getAllBookInfo();
    void deleteBookInfo(Long id);
    BookInfoDto updateBookInfo(BookInfoDto bookInfoDto);
    BookInfoDto findById(Long id);

}
