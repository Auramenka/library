package com.innowise.libraryapplicationsystem.repository;

import com.innowise.libraryapplicationsystem.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long>,
        JpaSpecificationExecutor<BookInfo> {
}
