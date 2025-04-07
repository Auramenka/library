package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.BookDto;
import com.innowise.libraryapplicationsystem.exceptions.EntityNotExistsException;
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

    private static final String BOOK_NOT_EXIST_ERR_MSG = "Book doesn't exist";
    private static final String BOOK_INFO_NOT_EXIST_ERR_MSG = "BookInfo doesn't exist";

    private final BookRepository bookRepository;
    private final BookInfoRepository bookInfoRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto saveBook(BookDto bookDto) {

        BookInfo bookInfo = bookInfoRepository.findById(bookDto.getBookInfoDto().getId())
                .orElseThrow(() -> new EntityNotExistsException(BOOK_INFO_NOT_EXIST_ERR_MSG));

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
        if (!isBookExists(id)) {
            throw new EntityNotExistsException(BOOK_NOT_EXIST_ERR_MSG);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {

        Book bookFromDB = getBookFromDB(bookDto.getId());

        BookInfo bookInfoFromDB = bookInfoRepository.findById(bookDto.getBookInfoDto().getId())
                .orElseThrow(() -> new EntityNotExistsException(BOOK_INFO_NOT_EXIST_ERR_MSG));

        bookFromDB.setBookInfo(bookInfoFromDB);

        return bookMapper.toDto(bookRepository.save(bookFromDB));
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(getBookFromDB(id));
    }

    private boolean isBookExists(Long id) {
        return bookRepository.existsById(id);
    }

    private Book getBookFromDB(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotExistsException(BOOK_NOT_EXIST_ERR_MSG));
    }
}
