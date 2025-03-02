package com.innowise.libraryapplicationsystem.builder.impl;

import com.innowise.libraryapplicationsystem.builder.FilterBookPredicateBuilder;
import com.innowise.libraryapplicationsystem.dto.FilterBookDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class BookTitlePredicateBuilder implements FilterBookPredicateBuilder {

    private static final String TITLE_FIELD = "title";

    @Override
    public Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, FilterBookDto filterBookDto) {
        if (filterBookDto.getTitle() == null) {
            return null;
        }

        return criteriaBuilder.equal(root.get(TITLE_FIELD), filterBookDto.getTitle());
    }
}
