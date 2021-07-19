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

		Review review1 = new Review();
		review1.setStudio(studio1);
		reviewRepository.save(review1);

		Review review2 = new Review();
		review2.setStudio(studio1);
		reviewRepository.save(review2);

		Review review3 = new Review();
		review3.setStudio(studio2);
		reviewRepository.save(review3);

		//when
		List<Review> studio1Reviews = reviewRepository.findByStudioId(studio1.getId());
		List<Review> studio2Reviews = reviewRepository.findByStudioId(studio2.getId());

		//then
		assertEquals(2, studio1Reviews.size());
		assertEquals(1, studio2Reviews.size());
	}


}