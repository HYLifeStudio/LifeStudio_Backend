package lifestudio.backend.domain.studio.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudioRepository {

	private final EntityManager em;

	public void save(Studio studio) {
		if (studio.getId() == null) {
			em.persist(studio);
		} else {
			em.merge(studio);
		}
	}

	public void delete(Studio studio){
		em.remove(studio);
	}

	public void deleteById(Long id){
		Studio studio = em.find(Studio.class, id);
		em.remove(studio);
	}

	public Optional<Studio> findById(Long id) {
		Studio studio = em.find(Studio.class, id);
		return Optional.ofNullable(studio);
	}

	public List<Studio> findAll() {
		return em.createQuery("select s from Studio s", Studio.class)
			.getResultList();
	}

	public List<Studio> findByStudioTypeAndCityDistrict(StudioType studioType, String cityDistrict) {
		return em.createQuery("select s from Studio s where s.studioType =:studioType "
			+ "and s.address.cityDistrict =:cityDistrict", Studio.class)
			.setParameter("studioType", studioType)
			.setParameter("cityDistrict", cityDistrict)
			.getResultList();
	}

}
