package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.ReviewDto;
import com.innowise.libraryapplicationsystem.exceptions.EntityNotExistsException;
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

    private static final String USER_NOT_EXIST_ERR_MSG = "User doesn't exist";
    private static final String REVIEW_NOT_EXIST_ERR_MSG = "Review doesn't exist";
    private static final String BOOK_INFO_NOT_EXIST_ERR_MSG = "BookInfo doesn't exist";

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookInfoRepository bookInfoRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {

        User user = userRepository.findById(reviewDto.getUserDto().getId())
                .orElseThrow(() -> new EntityNotExistsException(USER_NOT_EXIST_ERR_MSG));

        BookInfo bookInfo = bookInfoRepository.findById(reviewDto.getBookInfoDto().getId())
                .orElseThrow(() -> new EntityNotExistsException(BOOK_INFO_NOT_EXIST_ERR_MSG));

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
        if (!isReviewExists(id)) {
            throw new EntityNotExistsException(REVIEW_NOT_EXIST_ERR_MSG);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
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

    private boolean isReviewExists(Long id) {
        return reviewRepository.existsById(id);
    }

    private Review getReviewFromDB(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new EntityNotExistsException(REVIEW_NOT_EXIST_ERR_MSG));
    }
}
