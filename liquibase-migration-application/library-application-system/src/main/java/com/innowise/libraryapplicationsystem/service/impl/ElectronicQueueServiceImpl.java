package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.ElectronicQueueDto;
import com.innowise.libraryapplicationsystem.exceptions.NotExistsException;
import com.innowise.libraryapplicationsystem.mappers.ElectronicQueueMapper;
import com.innowise.libraryapplicationsystem.model.ElectronicQueue;
import com.innowise.libraryapplicationsystem.repository.ElectronicQueueRepository;
import com.innowise.libraryapplicationsystem.service.ElectronicQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectronicQueueServiceImpl implements ElectronicQueueService {

    private static final String ELECTRONIC_QUEUE_NOT_EXIST = "ElectronicQueue doesn't exist";
    private static final String ELECTRONIC_QUEUE_IS_EMPTY = "ElectronicQueueDto is empty";

    private final ElectronicQueueRepository electronicQueueRepository;
    private final ElectronicQueueMapper electronicQueueMapper;

    @Override
    public ElectronicQueueDto createQueue(ElectronicQueueDto electronicQueueDto) {
        checkQueueDto(electronicQueueDto);
        return electronicQueueMapper.toDto(
                electronicQueueRepository.save(electronicQueueMapper.toEntity(electronicQueueDto)));
    }

    @Override
    public List<ElectronicQueueDto> getAllQueues() {
        return electronicQueueRepository.findAll().stream()
                .map(electronicQueueMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQueue(Long id) {
        ElectronicQueue queueFromDB = getQueueFromDB(id);
        electronicQueueRepository.deleteById(queueFromDB.getId());
    }

    @Override
    public ElectronicQueueDto updateElectronicQueue(ElectronicQueueDto electronicQueueDto) {
        checkQueueDto(electronicQueueDto);
        ElectronicQueue queueFromDB = getQueueFromDB(electronicQueueDto.getId());
        queueFromDB.setBookId(electronicQueueDto.getBookId());
        queueFromDB.setUserId(electronicQueueDto.getUserId());
        queueFromDB.setUserIdWaiting(electronicQueueDto.getUserIdWaiting());
        return electronicQueueMapper.toDto(electronicQueueRepository.save(queueFromDB));
    }

    @Override
    public ElectronicQueueDto findById(Long id) {
        return electronicQueueMapper.toDto(getQueueFromDB(id));
    }

    private void checkQueueDto(ElectronicQueueDto electronicQueueDto) {
        if (electronicQueueDto == null) {
            throw new NotExistsException(ELECTRONIC_QUEUE_IS_EMPTY);
        }
    }

    private ElectronicQueue getQueueFromDB(Long id) {
        return electronicQueueRepository.findById(id).orElseThrow(() -> new NotExistsException(ELECTRONIC_QUEUE_NOT_EXIST));
    }
}
