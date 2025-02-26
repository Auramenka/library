package com.innowise.libraryapplicationsystem.repository;

import com.innowise.libraryapplicationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
