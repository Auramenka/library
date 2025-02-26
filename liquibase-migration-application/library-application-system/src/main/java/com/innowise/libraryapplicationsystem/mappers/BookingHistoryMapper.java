package com.innowise.libraryapplicationsystem.mappers;

import com.innowise.libraryapplicationsystem.dto.BookingHistoryDto;
import com.innowise.libraryapplicationsystem.model.BookingHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingHistoryMapper {

    BookingHistoryDto toDto(BookingHistory bookingHistory);

    BookingHistory toEntity(BookingHistoryDto bookingHistoryDto);

}
