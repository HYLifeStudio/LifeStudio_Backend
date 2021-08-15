package lifestudio.backend.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.user.domain.User;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void 비밀번호암호화(){

		//given
		String rawPassword = "12345";

		User user = User.builder()
			.password(rawPassword)
			.build();

		userService.createUser(user);
		//when
		String encodedPassword = user.getPassword();

		//then
		assertNotEquals(rawPassword,encodedPassword);
		assertEquals(true,passwordEncoder.matches(rawPassword, encodedPassword));

	}
}