package com.innowise.libraryapplicationsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectronicQueueDto {

    private Long id;
    private Long bookId;
    private Long userId;
    private Long userIdWaiting;

}
