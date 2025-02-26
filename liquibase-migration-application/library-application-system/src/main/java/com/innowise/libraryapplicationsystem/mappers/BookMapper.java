package com.innowise.libraryapplicationsystem.mappers;

import com.innowise.libraryapplicationsystem.dto.BookDto;
import com.innowise.libraryapplicationsystem.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookDto toDto(Book book);
    Book toEntity(BookDto bookDto);

}
