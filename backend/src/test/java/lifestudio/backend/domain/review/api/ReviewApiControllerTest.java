package lifestudio.backend.domain.review.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.review.dto.ReviewDto;
import lifestudio.backend.domain.review.service.ReviewService;
import lifestudio.backend.domain.studio.api.StudioApiController;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.service.StudioService;
import lifestudio.backend.domain.user.api.UserApiController;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;

@SpringBootTest
@Transactional
class ReviewApiControllerTest {

	@Autowired
	ReviewApiController reviewApiController;

	@Autowired
	ReviewService reviewService;

	@Autowired
	StudioService studioService;

	@Autowired
	UserService userService;

	@Test
	public void 리뷰생성(){

		//given

		User user = User.builder().build();
		Long creatUserId = userService.createUser(user);

		Studio studio = Studio.builder().build();
		Long creatStudioId = studioService.createStudio(studio);

		ReviewDto.createReq dto = ReviewDto.createReq.builder()
			.userId(creatUserId)
			.studioId(creatStudioId)
			.content("너무 좋아요")
			.rating(2)
			.build();


		//when
		ReviewDto.Res createReview = reviewApiController.createReview(dto);

		//then
		assertEquals(dto.getUserId(), createReview.getUserId());
		assertEquals(dto.getStudioId(), createReview.getStudioId());
	}

	@Test
	public void 조회및삭제(){

		//given

		User user1 = User.builder().build();
		Long creatUser1Id = userService.createUser(user1);

		User user2 = User.builder().build();
		Long creatUser2Id = userService.createUser(user2);

		Studio studio1 = Studio.builder().build();
		Long creatStudio1Id = studioService.createStudio(studio1);

		Studio studio2 = Studio.builder().build();
		Long creatStudio2Id = studioService.createStudio(studio2);

		Review user1Studio1Review = createReviewWithUserAndStudio(creatUser1Id, creatStudio1Id);
		Review user1Studio2Review = createReviewWithUserAndStudio(creatUser1Id, creatStudio2Id);
		Review user2Studio1Review = createReviewWithUserAndStudio(creatUser2Id, creatStudio1Id);

		//when
		List<ReviewDto.Res> allReviews = reviewApiController.getReviews(null);
		List<ReviewDto.Res> studio1Reviews = reviewApiController.getReviews(creatStudio1Id);
		ReviewDto.Res user2Studio1ReviewRes = reviewApiController.getReview(user2Studio1Review.getId());

		//then
		assertEquals(3,allReviews.size());
		assertEquals(2,studio1Reviews.size());
		assertEquals(user2Studio1Review.getId(),user2Studio1ReviewRes.getId());

		//삭제

		//when
		reviewApiController.deleteReview(user2Studio1Review.getId());
		List<ReviewDto.Res> allReviewsAfterDelete = reviewApiController.getReviews(null);

		//then
		assertEquals(2,allReviewsAfterDelete.size());

	}

	private Review createReviewWithUserAndStudio(Long creatUser1Id, Long creatStudio1Id) {
		Review review = Review.builder()
			.user(userService.findById(creatUser1Id))
			.studio(studioService.findById(creatStudio1Id))
			.build();
		Long createdReivewId = reviewService.createReview(review);
		return reviewService.findById(createdReivewId);
	}

}