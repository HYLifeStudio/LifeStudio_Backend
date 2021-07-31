package lifestudio.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class AuthDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class signInReq {

		private String email;

		private String password;

	}

	@Data
	public static class JwtAuthenticationRes {

		private String accessToken;

		private String tokenType = "Bearer";

		public JwtAuthenticationRes(String accessToken) {
			this.accessToken = accessToken;
		}

	}
}
