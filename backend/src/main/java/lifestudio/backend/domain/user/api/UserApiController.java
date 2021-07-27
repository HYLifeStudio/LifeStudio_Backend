package lifestudio.backend.domain.user.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.user.domain.RoleType;
import lifestudio.backend.domain.user.domain.Sex;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.dto.UserDto;
import lifestudio.backend.domain.user.dto.UserDto.Res;
import lifestudio.backend.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;

	@PostMapping("/api/users")
	public UserDto.Res signUpUser(@RequestBody @Valid UserDto.SiginUpReq dto) {
		User user = User.builder()
			.name(dto.getName())
			.sex(Sex.valueOf(dto.getSex()))
			.birth(dto.getBirth())
			.email(dto.getEmail())
			.nickName(dto.getNickName())
			.phone(dto.getPhone())
			.password(dto.getPassword())
			.roles(Collections.singletonList("USER"))
			.build();
		Long id = userService.createUser(user);
		return new UserDto.Res(userService.findById(id));
	}

	@GetMapping("/api/users/{id}")
	public UserDto.Res getUser(@PathVariable final long id) {
		return new UserDto.Res(userService.findById(id));
	}

	@GetMapping("/api/users")
	public List<UserDto.Res> getUsers() {
		List<User> findUsers = userService.findAll();
		List<UserDto.Res> collect = findUsers.stream()
			.map(u -> new UserDto.Res(u))
			.collect(Collectors.toList());
		return collect;
	}

	@DeleteMapping("/api/users/{id}")
	public UserDto.Res deleteUser(@PathVariable final long id) {
		User user = userService.findById(id);
		userService.deleteById(id);
		return new Res(user);
	}


}
