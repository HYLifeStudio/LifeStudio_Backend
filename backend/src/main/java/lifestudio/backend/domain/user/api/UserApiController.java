package lifestudio.backend.domain.user.api;

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
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setSex(dto.getSex());
		user.setBirth(dto.getBirth());
		user.setEmail(dto.getEmail());
		user.setNickName(dto.getNickName());
		user.setPhone(dto.getPhone());
		user.setPassword(dto.getPassword());
		user.setRoleType(RoleType.USER);
		Long id = userService.createUser(user);
		return new UserDto.Res(userService.findById(id));
	}

	@GetMapping("/api/users/{id")
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

	@DeleteMapping("/api/users/{id")
	public UserDto.Res deleteUser(@PathVariable final long id) {
		User user = userService.findById(id);
		userService.deleteById(id);
		return new Res(user);
	}


}
