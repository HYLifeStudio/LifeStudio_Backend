package lifestudio.backend.domain.photo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.studio.domain.Background;
import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.StudioType;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PhotoRepository {

	private final EntityManager em;

	public void save(Photo photo) {
		if (photo.getId() == null) {
			em.persist(photo);
		} else {
			em.merge(photo);
		}
	}

	public void delete(Photo photo){
		em.remove(photo);
	}

	public void deleteById(Long id){
		Photo photo = em.find(Photo.class, id);
		em.remove(photo);
	}

	public Optional<Photo> findById(Long id) {
		Photo photo = em.find(Photo.class, id);
		return Optional.ofNullable(photo);
	}

	public List<Photo> findAll() {
		return em.createQuery("select p from Photo p", Photo.class)
			.getResultList();
	}

	public List<Photo> findByStudioId(Long studioId) {
		return em.createQuery("select p from Photo p where p.studio.id = :studioId", Photo.class)
			.setParameter("studioId", studioId)
			.getResultList();
	}

	public List<Photo> findByStudioType(StudioType studioType) {
		return em.createQuery("select p from Photo p where p.studio.studioType = :studioType ", Photo.class)
			.setParameter("studioType", studioType)
			.getResultList();
	}
}
