package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.ElectronicQueueDto;
import com.innowise.libraryapplicationsystem.service.ElectronicQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.QUEUES_ENDPOINT)
public class ElectronicQueueController {

    private final ElectronicQueueService electronicQueueService;

    @PostMapping
    public ApiResponse<ElectronicQueueDto> createQueue(@RequestBody ElectronicQueueDto electronicQueueDto) {
        return ApiResponse.<ElectronicQueueDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(electronicQueueService.createQueue(electronicQueueDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ElectronicQueueDto>> getAllQueues() {
        return ApiResponse.<List<ElectronicQueueDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(electronicQueueService.getAllQueues())
                .build();
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<ElectronicQueueDto> getQueueById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<ElectronicQueueDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(electronicQueueService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteQueue(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        electronicQueueService.deleteQueue(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<ElectronicQueueDto> updateQueue(@RequestBody ElectronicQueueDto electronicQueueDto) {
        return ApiResponse.<ElectronicQueueDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(electronicQueueService.updateElectronicQueue(electronicQueueDto))
                .build();
    }

}
