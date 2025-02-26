package com.innowise.libraryapplicationsystem.repository;

import com.innowise.libraryapplicationsystem.model.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
}
