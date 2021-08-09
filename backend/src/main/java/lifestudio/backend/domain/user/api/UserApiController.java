package lifestudio.backend.domain.user.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.dto.UserDto;
import lifestudio.backend.domain.user.service.UserService;
import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserApiController {

	private final UserService userService;
	private final ResponseService responseService;

	@PostMapping("/api/users")
	public Response createUser(@RequestBody @Valid UserDto.CreateUserReq dto) {
		User user = User.builder()
				.email(dto.getEmail())
				.password(dto.getPassword())
				.roles(Collections.singletonList(dto.getRole()))
				.phone(dto.getPhone())
				.build();
		Long id = userService.createUser(user);
		return responseService.getSingleResponse(new UserDto.Res(userService.findById(id)));
	}

	@PutMapping("/api/users/{id}")
	public Response updateUser(@PathVariable final Long id, @RequestBody @Valid UserDto.UpdateUserReq dto){
		Long updateUserId = userService.updateUser(id,dto);
		return responseService.getSingleResponse(new UserDto.Res(userService.findById(updateUserId)));
	}

	@GetMapping("/api/users/{id}")
	public Response getUser(@PathVariable final long id) {
		return responseService.getSingleResponse(new UserDto.Res(userService.findById(id)));
	}

	@GetMapping("/api/users")
	public Response getUsers() {
		List<User> findUsers = userService.findAll();
		List<UserDto.Res> collect = findUsers.stream()
			.map(u -> new UserDto.Res(u))
			.collect(Collectors.toList());
		return responseService.getListResponse(collect);
	}

	@GetMapping("/api/users/me")
	public Response getLoggedInUser(Authentication authentication) {
		if (authentication == null){
			throw new RuntimeException();
		} else if(authentication.isAuthenticated()){
			User loginUser = (User)authentication.getPrincipal();
			return responseService.getSingleResponse(new UserDto.Res(loginUser));
		} else {
			throw new RuntimeException();
		}
	}

	@DeleteMapping("/api/users/{id}")
	public Response deleteUser(@PathVariable final long id) {
		User user = userService.findById(id);
		userService.deleteById(id);
		return responseService.getSuccessResponse();
	}


}
