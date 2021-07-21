package lifestudio.backend.domain.review.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.review.dto.ReviewDto;
import lifestudio.backend.domain.review.service.ReviewService;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.studio.dto.StudioDto;
import lifestudio.backend.domain.studio.service.StudioService;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {

	private final ReviewService reviewService;

	private final StudioService studioService;

	private final UserService userService;

	@PostMapping("/api/reviews")
	public ReviewDto.Res createReview(@RequestBody @Valid ReviewDto.createReq dto){

		Studio studio = studioService.findById(dto.getStudioId());
		User user = userService.findById(dto.getStudioId());

		Review review = new Review();
		review.setStudio(studio);
		review.setUser(user);
		review.setContent(dto.getContent());
		review.setRating(dto.getRating());
		review.setCreatedAt(LocalDateTime.now());

		Long id = reviewService.createReview(review);
		return new ReviewDto.Res(reviewService.findById(id));

	}

	@GetMapping("/api/reviews/{id}")
	public ReviewDto.Res getReview(@PathVariable final long id) {
		return new ReviewDto.Res(reviewService.findById(id));
	}

	@GetMapping("/api/reviews")
	public List<ReviewDto.Res> getReviews(@RequestParam(required = false) Long studioId) {

		List<Review> findReviews;

		if ( studioId == null ){
			findReviews = reviewService.findAll();
		} else {
			findReviews = reviewService.findByStudioId(studioId);
		}

		List<ReviewDto.Res> collect = findReviews.stream()
			.map(r -> new ReviewDto.Res(r))
			.collect(Collectors.toList());
		return collect;
	}

	@DeleteMapping("/api/reviews/{id}")
	public ReviewDto.Res deleteReview(@PathVariable final long id) {
		Review review = reviewService.findById(id);
		reviewService.deleteById(id);
		return new ReviewDto.Res(review);
	}




}
