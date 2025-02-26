package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.BookInfoDto;
import com.innowise.libraryapplicationsystem.exceptions.NotExistsException;
import com.innowise.libraryapplicationsystem.mappers.BookInfoMapper;
import com.innowise.libraryapplicationsystem.model.BookInfo;
import com.innowise.libraryapplicationsystem.repository.BookInfoRepository;
import com.innowise.libraryapplicationsystem.service.BookInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookInfoServiceImpl implements BookInfoService {

    private static final String BOOK_INFO_NOT_EXIST = "BookInfo doesn't exist";
    private static final String BOOK_INFO_IS_EMPTY = "BookInfoDto is empty";

    private final BookInfoRepository bookInfoRepository;
    private final BookInfoMapper bookInfoMapper;

    @Override
    public BookInfoDto saveBookInfo(BookInfoDto bookInfoDto) {
        checkBookInfoDto(bookInfoDto);
        return bookInfoMapper.toDto(bookInfoRepository.save(bookInfoMapper.toEntity(bookInfoDto)));
    }

    @Override
    public List<BookInfoDto> getAllBookInfo() {
        return bookInfoRepository.findAll().stream()
                .map(bookInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBookInfo(Long id) {
        BookInfo bookInfoFromDB = getBookInfoFromDB(id);
        bookInfoRepository.deleteById(bookInfoFromDB.getId());
    }

    @Override
    public BookInfoDto updateBookInfo(BookInfoDto bookInfoDto) {
        checkBookInfoDto(bookInfoDto);
        BookInfo bookInfoFromDB = getBookInfoFromDB(bookInfoDto.getId());
        bookInfoFromDB.setTitle(bookInfoDto.getTitle());
        bookInfoFromDB.setDescription(bookInfoDto.getDescription());
        bookInfoFromDB.setYear(bookInfoDto.getYear());
        return bookInfoMapper.toDto(bookInfoRepository.save(bookInfoFromDB));
    }

    @Override
    public BookInfoDto findById(Long id) {
        return bookInfoMapper.toDto(getBookInfoFromDB(id));
    }

    private void checkBookInfoDto(BookInfoDto bookInfoDto) {
        if (bookInfoDto == null) {
            throw new NotExistsException(BOOK_INFO_IS_EMPTY);
        }
    }

    private BookInfo getBookInfoFromDB(Long id) {
        return bookInfoRepository.findById(id).orElseThrow(() -> new NotExistsException(BOOK_INFO_NOT_EXIST));
    }
}
