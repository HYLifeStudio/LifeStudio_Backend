package lifestudio.backend.domain.review.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lifestudio.backend.domain.review.domain.Review;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

	private final EntityManager em;

	public void save(Review review) {
		if (review.getId() == null) {
			em.persist(review);
		} else {
			em.merge(review);
		}
	}

	public void delete(Review review){
		em.remove(review);
	}

	public void deleteById(Long id){
		Review review = em.find(Review.class, id);
		em.remove(review);
	}

	public Optional<Review> findById(Long id) {
		Review review = em.find(Review.class, id);
		return Optional.ofNullable(review);
	}

	public List<Review> findAll() {
		return em.createQuery("select r from Review r", Review.class)
			.getResultList();
	}

	public List<Review> findByStudioId(Long studioId) {
		return em.createQuery("select r from Review r where r.studio.id = :studioId", Review.class)
			.setParameter("studioId", studioId)
			.getResultList();
	}
}
