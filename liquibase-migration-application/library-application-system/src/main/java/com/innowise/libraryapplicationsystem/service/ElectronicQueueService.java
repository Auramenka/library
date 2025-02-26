package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.ElectronicQueueDto;

import java.util.List;

public interface ElectronicQueueService {

    ElectronicQueueDto createQueue(ElectronicQueueDto electronicQueueDto);
    List<ElectronicQueueDto> getAllQueues();
    void deleteQueue(Long id);
    ElectronicQueueDto updateElectronicQueue(ElectronicQueueDto electronicQueueDto);
    ElectronicQueueDto findById(Long id);

}
