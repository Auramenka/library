package com.innowise.libraryapplicationsystem.repository;

import com.innowise.libraryapplicationsystem.model.ElectronicQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicQueueRepository extends JpaRepository<ElectronicQueue, Long> {
}
