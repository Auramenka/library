package com.innowise.libraryapplicationsystem.mappers;

import com.innowise.libraryapplicationsystem.dto.AuthorDto;
import com.innowise.libraryapplicationsystem.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {

    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto authorDto);

}
