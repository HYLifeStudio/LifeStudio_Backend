package lifestudio.backend.domain.photo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

	public List<Likes> findByUserIdAndPhotoId(Long userId, Long photoId) {
		return em.createQuery("select l from Likes l where l.photo.id = :photoId "
				+ "and l.user.id = :userId", Likes.class)
				.setParameter("userId", userId)
				.setParameter("photoId", photoId)
				.getResultList();
	}

	public List<Likes> findByUserId(Long userId) {
		return em.createQuery("select l from Likes l where l.user.id = :userId ", Likes.class)
				.setParameter("userId", userId)
				.getResultList();
	}


}
