package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.dto.BookInfoDto;
import com.innowise.libraryapplicationsystem.service.BookInfoService;
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
@RequestMapping("/api/bookInfos")
public class BookInfoController {

    private final BookInfoService bookInfoService;

    @PostMapping
    public ResponseEntity<BookInfoDto> createBookInfo(@RequestBody BookInfoDto bookInfoDto) {
        return new ResponseEntity<>(bookInfoService.saveBookInfo(bookInfoDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookInfoDto>> getAllBookInfo() {
        return new ResponseEntity<>(bookInfoService.getAllBookInfo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookInfoDto> getBookInfoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookInfoService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookInfo(@PathVariable("id") Long id) {
        bookInfoService.deleteBookInfo(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BookInfoDto> updateBookInfo(@RequestBody BookInfoDto bookInfoDto) {
        return new ResponseEntity<>(bookInfoService.updateBookInfo(bookInfoDto), HttpStatus.OK);
    }

}
