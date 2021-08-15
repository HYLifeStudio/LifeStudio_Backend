package lifestudio.backend.domain.user.api;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lifestudio.backend.domain.user.service.EmailSenderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lifestudio.backend.domain.user.domain.Sex;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.dto.UserDto;
import lifestudio.backend.domain.user.exception.LoginInputInvalidException;
import lifestudio.backend.domain.user.exception.UserNotFoundException;
import lifestudio.backend.domain.user.service.UserService;
import lifestudio.backend.domain.user.dto.AuthDto;
import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lifestudio.backend.global.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final ResponseService responseService;
	private final EmailSenderService emailSenderService;


	@PostMapping("api/auth/signin")
	public Response signIn(@Valid @RequestBody UserDto.SignInReq Req) {
		try {
			User user = userService.findByEmail(Req.getEmail());
			if (!passwordEncoder.matches(Req.getPassword(),user.getPassword())){
				throw new LoginInputInvalidException(Req.getEmail());
			}
			String jwt = jwtTokenProvider.createToken(String.valueOf(user.getId()),user.getRoles());
			return responseService.getSingleResponse(new AuthDto.JwtAuthenticationRes(jwt));
		} catch (UserNotFoundException e){
			throw new LoginInputInvalidException(Req.getEmail());
		}
	}

	@PostMapping("/api/auth/signup")
	public Response signUp(@RequestBody @Valid UserDto.SignUpReq dto) {
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
		return responseService.getSingleResponse(new UserDto.Res(userService.findById(id)));
	}

	@PostMapping("api/auth/emailsend")
	public Response emailSend(@RequestParam(name="email") String email, HttpSession session) {
		emailSenderService.sendEmail(session, email);
		return responseService.getSuccessResponse();
	}

	@PostMapping("api/auth/emailverification")
	public Response emailVerification(HttpSession session,
			@RequestParam(name="email") String email,
			@RequestParam(name = "code") int code) {
		if (emailSenderService.emailCertification(session, email, code)){
			return responseService.getSuccessResponse();
		} else
			return responseService.getFailResponse();
	}

}
