package com.innowise.libraryapplicationsystem.repository;

import com.innowise.libraryapplicationsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
