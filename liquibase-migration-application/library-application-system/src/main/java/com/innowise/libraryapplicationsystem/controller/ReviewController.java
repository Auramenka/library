package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.ReviewDto;
import com.innowise.libraryapplicationsystem.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.REVIEWS_ENDPOINT)
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        return ApiResponse.<ReviewDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(reviewService.saveReview(reviewDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ReviewDto>> getAllReviews() {
        return ApiResponse.<List<ReviewDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(reviewService.getAllReviews())
                .build();
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<ReviewDto> getReviewById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<ReviewDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(reviewService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteReview(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        reviewService.deleteReview(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto) {
        return ApiResponse.<ReviewDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(reviewService.updateReview(reviewDto))
                .build();
    }

}
