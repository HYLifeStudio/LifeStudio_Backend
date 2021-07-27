package lifestudio.backend.global.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lifestudio.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
		return userRepository.findById(Long.valueOf(userPk))
			.orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 업습니다"));
	}
}
