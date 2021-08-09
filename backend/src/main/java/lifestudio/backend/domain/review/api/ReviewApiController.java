package lifestudio.backend.domain.review.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lifestudio.backend.domain.review.domain.Reservation;
import lifestudio.backend.domain.review.service.ReservationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.review.dto.ReviewDto;
import lifestudio.backend.domain.review.service.ReviewService;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.service.StudioService;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;
import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {

	private final ReviewService reviewService;

	private final ReservationService reservationService;

	private final StudioService studioService;

	private final UserService userService;

	private final ResponseService responseService;

	@PostMapping("/api/reviews")
	public Response createReview(@RequestBody @Valid ReviewDto.createReq dto){

		Reservation reservation = reservationService.findById(dto.getReservationId());
		Studio studio = studioService.findById(dto.getStudioId());
		User user = userService.findById(dto.getUserId());
		Review review = Review.builder()
				.reservation(reservation)
				.studio(studio)
				.user(user)
				.content(dto.getContent())
				.rating(dto.getRating())
				.createdAt(LocalDateTime.now())
				.build();

		Long id = reviewService.createReview(review);
		return responseService.getSingleResponse(new ReviewDto.Res(reviewService.findById(id)));

	}

	@GetMapping("/api/reviews/{id}")
	public Response getReview(@PathVariable final long id) {
		return responseService.getSingleResponse(new ReviewDto.Res(reviewService.findById(id)));
	}

	@GetMapping("/api/reviews")
	public Response getReviews() {

		List<Review> findReviews = reviewService.findAll();

		List<ReviewDto.Res> collect = findReviews.stream()
			.map(r -> new ReviewDto.Res(r))
			.collect(Collectors.toList());
		return responseService.getListResponse(collect);
	}


	@DeleteMapping("/api/reviews/{id}")
	public Response deleteReview(@PathVariable final long id) {
		Review review = reviewService.findById(id);
		reviewService.deleteById(id);
		return responseService.getSuccessResponse();
	}




}
