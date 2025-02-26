package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.ReviewDto;
import com.innowise.libraryapplicationsystem.exceptions.NotExistsException;
import com.innowise.libraryapplicationsystem.mappers.ReviewMapper;
import com.innowise.libraryapplicationsystem.model.BookInfo;
import com.innowise.libraryapplicationsystem.model.Review;
import com.innowise.libraryapplicationsystem.model.User;
import com.innowise.libraryapplicationsystem.repository.BookInfoRepository;
import com.innowise.libraryapplicationsystem.repository.ReviewRepository;
import com.innowise.libraryapplicationsystem.repository.UserRepository;
import com.innowise.libraryapplicationsystem.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private static final String USER_NOT_EXIST = "User doesn't exist";
    private static final String REVIEW_NOT_EXIST = "Review doesn't exist";
    private static final String BOOK_INFO_NOT_EXIST = "BookInfo doesn't exist";
    private static final String REVIEW_IS_EMPTY = "ReviewDto is empty";

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookInfoRepository bookInfoRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        checkReviewDto(reviewDto);

        User user = userRepository.findById(reviewDto.getUserDto().getId())
                .orElseThrow(() -> new NotExistsException(USER_NOT_EXIST));

        BookInfo bookInfo = bookInfoRepository.findById(reviewDto.getBookInfoDto().getId())
                .orElseThrow(() -> new NotExistsException(BOOK_INFO_NOT_EXIST));

        Review review = reviewMapper.toEntity(reviewDto);

        review.setUser(user);
        review.setBookInfo(bookInfo);

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        Review reviewFromDB = getReviewFromDB(id);
        reviewRepository.deleteById(reviewFromDB.getId());
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        checkReviewDto(reviewDto);
        Review reviewFromDB = getReviewFromDB(reviewDto.getId());

        reviewFromDB.setRating(reviewDto.getRating());
        reviewFromDB.setComment(reviewDto.getComment());
        reviewFromDB.setCreated_at(reviewDto.getCreated_at());

        return reviewMapper.toDto(reviewRepository.save(reviewFromDB));
    }

    @Override
    public ReviewDto findById(Long id) {
        return reviewMapper.toDto(getReviewFromDB(id));
    }

    private void checkReviewDto(ReviewDto reviewDto) {
        if (reviewDto == null) {
            throw new NotExistsException(REVIEW_IS_EMPTY);
        }
    }

    private Review getReviewFromDB(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new NotExistsException(REVIEW_NOT_EXIST));
    }
}
