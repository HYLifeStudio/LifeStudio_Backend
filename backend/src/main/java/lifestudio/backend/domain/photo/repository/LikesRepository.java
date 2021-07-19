package lifestudio.backend.domain.photo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lifestudio.backend.domain.photo.domain.Likes;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LikesRepository {

	private final EntityManager em;

	public void save(Likes like) {
		if (like.getId() == null) {
			em.persist(like);
		} else {
			em.merge(like);
		}
	}

	public void delete(Likes like){
		em.remove(like);
	}

	public void deleteById(Long id){
		Likes like = em.find(Likes.class, id);
		em.remove(like);
	}

	public Optional<Likes> findById(Long id) {
		Likes like = em.find(Likes.class, id);
		return Optional.ofNullable(like);
	}

	public List<Likes> findAll() {
		return em.createQuery("select l from Likes l", Likes.class)
			.getResultList();
	}

	public Optional<Likes> findByPhotoIdAndUserId(Long userId, Long photoId) {
		Likes like = em.createQuery("select l from Likes l where l.photo.id = :photoId "
			+ "and l.user.id = :userId", Likes.class)
			.setParameter("userId", userId)
			.setParameter("photoId", photoId)
			.getSingleResult();
		return Optional.ofNullable(like);
	}
}
