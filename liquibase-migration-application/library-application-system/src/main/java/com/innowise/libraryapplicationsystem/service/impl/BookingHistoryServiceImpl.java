package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.BookingHistoryDto;
import com.innowise.libraryapplicationsystem.exceptions.EntityNotExistsException;
import com.innowise.libraryapplicationsystem.mappers.BookingHistoryMapper;
import com.innowise.libraryapplicationsystem.model.Book;
import com.innowise.libraryapplicationsystem.model.BookingHistory;
import com.innowise.libraryapplicationsystem.model.User;
import com.innowise.libraryapplicationsystem.repository.BookRepository;
import com.innowise.libraryapplicationsystem.repository.BookingHistoryRepository;
import com.innowise.libraryapplicationsystem.repository.UserRepository;
import com.innowise.libraryapplicationsystem.service.BookingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingHistoryServiceImpl implements BookingHistoryService {

    private static final String USER_NOT_EXIST_ERR_MSG = "User doesn't exist";
    private static final String BOOK_NOT_EXIST_ERR_MSG = "Book doesn't exist";
    private static final String BOOK_HISTORY_NOT_EXIST_ERR_MSG = "BookingHistory doesn't exist";

    private final BookingHistoryRepository bookingHistoryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookingHistoryMapper bookingHistoryMapper;

    @Override
    public BookingHistoryDto saveBookingHistory(BookingHistoryDto bookingHistoryDto) {

        User user = userRepository.findById(bookingHistoryDto.getUserDto().getId())
                .orElseThrow(() -> new EntityNotExistsException(USER_NOT_EXIST_ERR_MSG));

        Book book = bookRepository.findById(bookingHistoryDto.getBookDto().getId())
                .orElseThrow(() -> new EntityNotExistsException(BOOK_NOT_EXIST_ERR_MSG));

        BookingHistory bookingHistory = bookingHistoryMapper.toEntity(bookingHistoryDto);

        bookingHistory.setUser(user);
        bookingHistory.setBook(book);

        return bookingHistoryMapper.toDto(bookingHistoryRepository.save(bookingHistory));
    }

    @Override
    public List<BookingHistoryDto> getAllBookingHistory() {
        return bookingHistoryRepository.findAll().stream()
                .map(bookingHistoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBookingHistory(Long id) {
        if (!isBookHistoryExists(id)) {
            throw new EntityNotExistsException(BOOK_HISTORY_NOT_EXIST_ERR_MSG);
        }
        bookingHistoryRepository.deleteById(id);
    }

    @Override
    public BookingHistoryDto updateBookingHistory(BookingHistoryDto bookingHistoryDto) {
        BookingHistory bookingHistoryFromDB = getBookingHistoryFromDB(bookingHistoryDto.getId());

        bookingHistoryFromDB.setDateFrom(bookingHistoryDto.getDateFrom());
        bookingHistoryFromDB.setDateTo(bookingHistoryDto.getDateTo());
        bookingHistoryFromDB.setStatus(bookingHistoryDto.getStatus());

        return bookingHistoryMapper.toDto(bookingHistoryRepository.save(bookingHistoryFromDB));
    }

    @Override
    public BookingHistoryDto findById(Long id) {
        return bookingHistoryMapper.toDto(getBookingHistoryFromDB(id));
    }

    private boolean isBookHistoryExists(Long id) {
        return bookingHistoryRepository.existsById(id);
    }

    private BookingHistory getBookingHistoryFromDB(Long id) {
        return bookingHistoryRepository.findById(id).orElseThrow(() -> new EntityNotExistsException(BOOK_HISTORY_NOT_EXIST_ERR_MSG));
    }
}
