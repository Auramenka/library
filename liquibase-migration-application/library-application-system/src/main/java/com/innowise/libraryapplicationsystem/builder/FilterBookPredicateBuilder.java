package com.innowise.libraryapplicationsystem.builder;

import com.innowise.libraryapplicationsystem.dto.FilterBookDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface FilterBookPredicateBuilder {

    Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, FilterBookDto filterBookDto);

}
