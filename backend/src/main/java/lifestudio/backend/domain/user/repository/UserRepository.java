package lifestudio.backend.domain.user.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lifestudio.backend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final EntityManager em;

	public void save(User user) {
		if (user.getId() == null) {
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

	public void delete(User user){
		em.remove(user);
	}

	public void deleteById(Long id){
		User user = em.find(User.class, id);
		em.remove(user);
	}

	public Optional<User> findById(Long id) {
		User user = em.find(User.class, id);
		return Optional.ofNullable(user);
	}

	public List<User> findAll() {
		return em.createQuery("select u from User u", User.class)
			.getResultList();
	}

	public List<User> findByEmail(String email) {
		return em.createQuery("select u from User u where u.email = :email", User.class)
			.setParameter("email", email)
			.getResultList();
	}


}
