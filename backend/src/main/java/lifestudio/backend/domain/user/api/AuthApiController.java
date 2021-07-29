package lifestudio.backend.domain.user.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.user.domain.RoleType;
import lifestudio.backend.domain.user.domain.Sex;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.dto.UserDto;
import lifestudio.backend.domain.user.service.UserService;
import lifestudio.backend.global.common.dto.AuthDto;
import lifestudio.backend.global.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;


	@PostMapping("api/auth/signin")
	public AuthDto.JwtAuthenticationRes signIn(@Valid @RequestBody UserDto.SignInReq Req) {
		User user = userService.findByEmail(Req.getEmail()).orElseThrow(RuntimeException::new);
		if (!passwordEncoder.matches(Req.getPassword(),user.getPassword())){
			throw new RuntimeException();
		}
		String jwt = jwtTokenProvider.createToken(String.valueOf(user.getId()),user.getRoles());
		return new AuthDto.JwtAuthenticationRes(jwt);
	}

	@PostMapping("/api/auth/signup")
	public UserDto.Res signUp(@RequestBody @Valid UserDto.SignUpReq dto) {
		User user = User.builder()
			.name(dto.getName())
			.sex(Sex.valueOf(dto.getSex()))
			.birth(dto.getBirth())
			.email(dto.getEmail())
			.nickName(dto.getNickName())
			.phone(dto.getPhone())
			.password(dto.getPassword())
			.roles(Collections.singletonList("ROLE_USER"))
			.build();
		Long id = userService.createUser(user);
		return new UserDto.Res(userService.findById(id));
	}

}
