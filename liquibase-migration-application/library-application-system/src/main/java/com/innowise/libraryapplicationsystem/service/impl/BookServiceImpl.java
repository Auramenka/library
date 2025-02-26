package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.BookDto;
import com.innowise.libraryapplicationsystem.exceptions.NotExistsException;
import com.innowise.libraryapplicationsystem.mappers.BookMapper;
import com.innowise.libraryapplicationsystem.model.Book;
import com.innowise.libraryapplicationsystem.model.BookInfo;
import com.innowise.libraryapplicationsystem.repository.BookInfoRepository;
import com.innowise.libraryapplicationsystem.repository.BookRepository;
import com.innowise.libraryapplicationsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_EXIST = "Book doesn't exist";
    private static final String BOOK_INFO_NOT_EXIST = "BookInfo doesn't exist";
    private static final String BOOK_IS_EMPTY = "BookDto is empty";

    private final BookRepository bookRepository;
    private final BookInfoRepository bookInfoRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        checkBookDto(bookDto);

        BookInfo bookInfo = bookInfoRepository.findById(bookDto.getBookInfoDto().getId())
                .orElseThrow(() -> new NotExistsException(BOOK_INFO_NOT_EXIST));

        Book book = bookMapper.toEntity(bookDto);

        book.setBookInfo(bookInfo);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {
        Book bookFromDB = getBookFromDB(id);
        bookRepository.deleteById(bookFromDB.getId());
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        checkBookDto(bookDto);

        Book bookFromDB = getBookFromDB(bookDto.getId());

        BookInfo bookInfoFromDB = bookInfoRepository.findById(bookDto.getBookInfoDto().getId())
                .orElseThrow(() -> new NotExistsException(BOOK_INFO_NOT_EXIST));

        bookFromDB.setBookInfo(bookInfoFromDB);

        return bookMapper.toDto(bookRepository.save(bookFromDB));
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(getBookFromDB(id));
    }

    private void checkBookDto(BookDto bookDto) {
        if (bookDto == null) {
            throw new NotExistsException(BOOK_IS_EMPTY);
        }
    }

    private Book getBookFromDB(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotExistsException(BOOK_NOT_EXIST));
    }
}
