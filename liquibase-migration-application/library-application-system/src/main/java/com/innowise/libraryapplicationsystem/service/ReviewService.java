package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto saveReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviews();
    void deleteReview(Long id);
    ReviewDto updateReview(ReviewDto reviewDto);
    ReviewDto findById(Long id);

}
