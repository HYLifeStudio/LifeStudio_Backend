package lifestudio.backend.domain.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.review.exception.ReviewNotFoundException;
import lifestudio.backend.domain.review.repository.ReviewRepository;
import lifestudio.backend.domain.studio.domain.Studio;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;

	@Transactional
	public Long createReview(Review review){
		reviewRepository.save(review);
		return review.getId();
	}

	public Review findById(Long reviewId){
		Optional<Review> findReview = reviewRepository.findById(reviewId);
		if (findReview.isEmpty()){
			throw new ReviewNotFoundException(reviewId);
		} else {
			return findReview.get();
		}
	}

	public List<Review> findAll(){
		return reviewRepository.findAll();
	}

	public List<Review> findByStudioId(Long studioId){
		return reviewRepository.findByStudioId(studioId);
	}

	@Transactional
	public Long deleteById(Long reviewId){
		reviewRepository.deleteById(reviewId);
		return reviewId;
	}
}
