package com.innowise.libraryapplicationsystem.mappers;

import com.innowise.libraryapplicationsystem.dto.ReviewDto;
import com.innowise.libraryapplicationsystem.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);

}
