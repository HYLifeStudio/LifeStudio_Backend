package lifestudio.backend.domain.user.api;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.user.domain.RoleType;
import lifestudio.backend.domain.user.domain.Sex;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.dto.UserDto;
import lifestudio.backend.domain.user.repository.UserRepository;

@SpringBootTest
@Transactional
class UserApiControllerTest {

	@Autowired
	UserApiController userApiController;

	@Test
	public void 회원가입(){
		//given

		UserDto.SiginUpReq dto = UserDto.SiginUpReq.builder()
			.userName("윤승권")
			.sex("MALE")
			.birth(LocalDate.of(1996,3,22))
			.email("zxcvb5434@likelion.org")
			.nickName("시험감독")
			.phone("01099265434")
			.password("1q2w3e4r!")
			.build();

		//when
		UserDto.Res userRes = userApiController.signUpUser(dto);

		//then
		assertEquals(dto.getEmail(), userRes.getEmail());
	}

	@Test
	public void 조회및삭제(){

		//given
		UserDto.SiginUpReq dto1 = UserDto.SiginUpReq.builder()
			.userName("윤승권")
			.sex("MALE")
			.birth(LocalDate.of(1996,3,22))
			.email("zxcvb5434@likelion.org")
			.nickName("시험감독")
			.phone("01099265434")
			.password("1q2w3e4r!")
			.build();

		UserDto.SiginUpReq dto2 = UserDto.SiginUpReq.builder()
			.userName("안경록")
			.sex("MALE")
			.birth(LocalDate.of(1996,3,22))
			.email("zxcvb5234@likelion.org")
			.nickName("시함감독")
			.phone("01099365434")
			.password("1q2w3e4r!!")
			.build();

		UserDto.Res user1Res = userApiController.signUpUser(dto1);
		UserDto.Res user2Res = userApiController.signUpUser(dto2);

		//when
		UserDto.Res findUser1Res = userApiController.getUser(user1Res.getId());
		List<UserDto.Res> findUsers = userApiController.getUsers();

		//then
		assertEquals(user1Res.getId(),findUser1Res.getId());
		assertEquals(2,findUsers.size());

		//삭제

		//when
		userApiController.deleteUser(user2Res.getId());
		List<UserDto.Res> AfterDeletefindUsers = userApiController.getUsers();

		//then
		assertEquals(1,AfterDeletefindUsers.size());

	}





}