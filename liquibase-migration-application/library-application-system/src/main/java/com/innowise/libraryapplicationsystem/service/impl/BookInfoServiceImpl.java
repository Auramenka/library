package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.builder.FilterBookPredicateBuilder;
import com.innowise.libraryapplicationsystem.dto.BookInfoDto;
import com.innowise.libraryapplicationsystem.dto.FilterBookDto;
import com.innowise.libraryapplicationsystem.exceptions.EntityNotExistsException;
import com.innowise.libraryapplicationsystem.mappers.BookInfoMapper;
import com.innowise.libraryapplicationsystem.model.BookInfo;
import com.innowise.libraryapplicationsystem.repository.BookInfoRepository;
import com.innowise.libraryapplicationsystem.service.BookInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookInfoServiceImpl implements BookInfoService {

    private static final String BOOK_INFO_NOT_EXIST_ERR_MSG = "BookInfo doesn't exist";

    private final BookInfoRepository bookInfoRepository;
    private final BookInfoMapper bookInfoMapper;
    private final List<FilterBookPredicateBuilder> filterBookPredicateBuilderList;

    @Override
    public BookInfoDto saveBookInfo(BookInfoDto bookInfoDto) {
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
        if (!isBookInfoExists(id)) {
            throw new EntityNotExistsException(BOOK_INFO_NOT_EXIST_ERR_MSG);
        }
        bookInfoRepository.deleteById(id);
    }

    @Override
    public BookInfoDto updateBookInfo(BookInfoDto bookInfoDto) {
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

    @Override
    public List<BookInfoDto> getFilterBooks(FilterBookDto filterBookDto, Pageable pageable) {
        Specification<BookInfo> specificationByParameter = getSpecificationByParameters(filterBookDto);
        Page<BookInfo> page = bookInfoRepository.findAll(specificationByParameter, pageable);
        return page.getContent().stream().map(bookInfoMapper::toDto).collect(Collectors.toList());
    }

    private Specification<BookInfo> getSpecificationByParameters(FilterBookDto filterBookDto) {
        return (root, query, criteriaBuilder) -> filterBookPredicateBuilderList.stream()
                .map(filterBookPredicateBuilder ->
                        filterBookPredicateBuilder.build(root, criteriaBuilder, filterBookDto))
                .filter(Objects::nonNull)
                .reduce(criteriaBuilder::and).orElse(null);
    }

    private boolean isBookInfoExists(Long id) {
        return bookInfoRepository.existsById(id);
    }

    private BookInfo getBookInfoFromDB(Long id) {
        return bookInfoRepository.findById(id).orElseThrow(() -> new EntityNotExistsException(BOOK_INFO_NOT_EXIST_ERR_MSG));
    }
}