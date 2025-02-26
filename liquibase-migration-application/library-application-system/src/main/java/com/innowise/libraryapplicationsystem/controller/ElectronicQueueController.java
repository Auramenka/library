package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.dto.ElectronicQueueDto;
import com.innowise.libraryapplicationsystem.service.ElectronicQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/queues")
public class ElectronicQueueController {

    private final ElectronicQueueService electronicQueueService;

    @PostMapping
    public ResponseEntity<ElectronicQueueDto> createQueue(@RequestBody ElectronicQueueDto electronicQueueDto) {
        return new ResponseEntity<>(electronicQueueService.createQueue(electronicQueueDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ElectronicQueueDto>> getAllQueues() {
        return new ResponseEntity<>(electronicQueueService.getAllQueues(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectronicQueueDto> getQueueById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(electronicQueueService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQueue(@PathVariable("id") Long id) {
        electronicQueueService.deleteQueue(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ElectronicQueueDto> updateQueue(@RequestBody ElectronicQueueDto electronicQueueDto) {
        return new ResponseEntity<>(electronicQueueService.updateElectronicQueue(electronicQueueDto), HttpStatus.OK);
    }

}
