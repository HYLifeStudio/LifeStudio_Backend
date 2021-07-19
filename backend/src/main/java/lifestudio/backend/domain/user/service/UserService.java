package lifestudio.backend.domain.user.service;

import java.util.List;

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

	@Transactional
	public Long createUser(User user) {
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

}
