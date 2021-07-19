package lifestudio.backend.domain.review.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.sound.sampled.ReverbType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.repository.StudioRepository;

@SpringBootTest
@Transactional
class ReviewRepositoryTest {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	StudioRepository studioRepository;

	@Test
	public void 스튜디오에속한리뷰들찾기(){

		//given
		Studio studio1 = new Studio();
		studioRepository.save(studio1);

		Studio studio2 = new Studio();
		studioRepository.save(studio2);

		Review studio1Review1 = ReviewWithStudio(studio1);
		reviewRepository.save(studio1Review1);

		Review studio1Review2 = ReviewWithStudio(studio1);
		reviewRepository.save(studio1Review2);

		Review studio2Review1 = ReviewWithStudio(studio2);
		reviewRepository.save(studio2Review1);

		//when
		List<Review> studio1Reviews = reviewRepository.findByStudioId(studio1.getId());
		List<Review> studio2Reviews = reviewRepository.findByStudioId(studio2.getId());

		//then
		assertEquals(2, studio1Reviews.size());
		assertEquals(1, studio2Reviews.size());
	}

	private Review ReviewWithStudio(Studio studio) {
		Review review = new Review();
		review.setStudio(studio);
		return review;
	}

}