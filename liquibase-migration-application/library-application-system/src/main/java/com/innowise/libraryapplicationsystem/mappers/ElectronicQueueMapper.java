package com.innowise.libraryapplicationsystem.mappers;

import com.innowise.libraryapplicationsystem.dto.ElectronicQueueDto;
import com.innowise.libraryapplicationsystem.model.ElectronicQueue;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ElectronicQueueMapper {

    ElectronicQueueDto toDto(ElectronicQueue electronicQueue);
    ElectronicQueue toEntity(ElectronicQueueDto electronicQueueDto);

}
