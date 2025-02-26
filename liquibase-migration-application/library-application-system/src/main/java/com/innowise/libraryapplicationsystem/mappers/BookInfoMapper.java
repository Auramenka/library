package com.innowise.libraryapplicationsystem.mappers;

import com.innowise.libraryapplicationsystem.dto.BookInfoDto;
import com.innowise.libraryapplicationsystem.model.BookInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookInfoMapper {

    BookInfoDto toDto(BookInfo bookInfo);
    BookInfo toEntity(BookInfoDto bookInfoDto);

}
