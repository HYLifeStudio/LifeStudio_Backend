package lifestudio.backend.domain.user.service;

import java.util.List;
import java.util.Optional;

import lifestudio.backend.domain.user.domain.Sex;
import lifestudio.backend.domain.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.exception.EmailDuplicateException;
import lifestudio.backend.domain.user.exception.UserNotFoundException;
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
		Boolean existUser = userRepository.findByEmail(user.getEmail()).isPresent();
		if (existUser){
			throw new EmailDuplicateException(user.getEmail());
		} else {
			userRepository.save(user);
		}

		return user.getId();
	}

	@Transactional
	public Long updateUser(Long userId, UserDto.UpdateUserReq dto){
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		User updateUser = findById(userId);
		updateUser.setName(dto.getName());
		updateUser.setSex(Sex.valueOf(dto.getSex()));
		updateUser.setBirth(dto.getBirth());
		updateUser.setNickName(dto.getNickName());
		updateUser.setPhone(dto.getPhone());
		updateUser.setPassword(encodedPassword);
		return updateUser.getId();
	}


	public User findById(Long userId) {

		Optional<User> findUser = userRepository.findById(userId);
		if (findUser.isPresent()){
			return findUser.get();
		} else {
			throw new UserNotFoundException(userId);
		}
	}

	public List<User> findAll(){
		return userRepository.findAll();
	}

	public Long deleteById(Long userId) {
		Optional<User> findUser = userRepository.findById(userId);
		if (findUser.isPresent()){
			userRepository.deleteById(userId);
			return userId;
		} else {
			throw new UserNotFoundException(userId);
		}
	}

	public User findByEmail(String email) {
		Optional<User> findUser = userRepository.findByEmail(email);
		if (findUser.isPresent()){
			return findUser.get();
		} else {
			throw new UserNotFoundException(email);
		}
	}

}
