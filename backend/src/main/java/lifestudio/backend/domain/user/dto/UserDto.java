package lifestudio.backend.domain.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lifestudio.backend.domain.user.domain.RoleType;
import lifestudio.backend.domain.user.domain.Sex;
import lifestudio.backend.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class SiginUpReq {

		private String userName;

		private String sex;

		private LocalDate birth;

		private String email;

		private String nickName;

		private String phone;

		private String password;

	}

	@Getter
	public static class Res {

		private Long id;

		private String userName;

		private Sex sex;

		private LocalDate birth;

		private String email;

		private String nickName;

		private String phone;

		private String password;

		public Res(User user) {
			this.id = user.getId();
			this.userName = user.getUserName();
			this.sex = user.getSex();
			this.birth = user.getBirth();
			this.email = user.getEmail();
			this.nickName = user.getNickName();
			this.phone = user.getPhone();
			this.password = user.getPassword();
		}
	}

}
