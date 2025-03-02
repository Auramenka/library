package com.innowise.libraryapplicationsystem.builder.impl;

import com.innowise.libraryapplicationsystem.builder.FilterBookPredicateBuilder;
import com.innowise.libraryapplicationsystem.dto.FilterBookDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class BookYearPredicateBuilder implements FilterBookPredicateBuilder {

    private static final String YEAR_FIELD = "year";

    @Override
    public Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, FilterBookDto filterBookDto) {
        if (filterBookDto.getYear() == null) {
            return null;
        }

        return criteriaBuilder.equal(root.get(YEAR_FIELD), filterBookDto.getYear());
    }
}
