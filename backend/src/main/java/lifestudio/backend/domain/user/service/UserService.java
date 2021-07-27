package lifestudio.backend.domain.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Long createUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return user.getId();
	}


	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}

	public List<User> findAll(){
		return userRepository.findAll();
	}

	public Long deleteById(Long userId) {
		userRepository.deleteById(userId);
		return userId;
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
