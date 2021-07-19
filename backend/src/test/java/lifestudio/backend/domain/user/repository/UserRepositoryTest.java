package lifestudio.backend.domain.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.user.domain.User;

@SpringBootTest
@Transactional
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void 기본CRUD() {

		// 생성 및 조회 검증

		//given
		User user1 = new User();
		User user2 = new User();
		userRepository.save(user1);
		userRepository.save(user2);

		//when
		User findUser1 = userRepository.findById(user1.getId()).get();
		User findUser2 = userRepository.findById(user2.getId()).get();
		List<User> all = userRepository.findAll();

		//then
		assertEquals(user1, findUser1);
		assertEquals(user2, findUser2);
		assertEquals(2, all.size());

		// 삭제 검증

		// when
		userRepository.delete(user1);
		userRepository.deleteById(user2.getId());

		// then
		assertThrows(NoSuchElementException.class, () -> {
			userRepository.findById(user1.getId()).get();
		});

		assertThrows(NoSuchElementException.class, () -> {
			userRepository.findById(user2.getId()).get();
		});

	}

	@Test
	public void 이메일로조회하기() {

		//given
		User user1 = new User();
		user1.setEmail("zxcvb5434@likelion.org");
		userRepository.save(user1);

		User user2 = new User();
		user2.setEmail("zxcvb5434@likelion.org");
		userRepository.save(user2);

		User user3 = new User();
		user3.setEmail("zxcvb5435@likelion.org");
		userRepository.save(user3);

		//when
		List<User> findEmailUsers = userRepository.findByEmail(user1.getEmail());

		//then
		assertEquals(2, findEmailUsers.size());
	}

}