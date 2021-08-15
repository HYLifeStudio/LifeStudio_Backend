package lifestudio.backend.global.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lifestudio.backend.domain.user.service.UserService;

@SpringBootTest
class WebSecurityConfigTest {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void 패스워드암호화() {
		// given
		String rawPassword = "12345678";

		// when
		String encodedPassword = passwordEncoder.encode(rawPassword);

		// then
		assertNotEquals(rawPassword,encodedPassword);
		assertEquals(true, passwordEncoder.matches(rawPassword,encodedPassword));

	}
}