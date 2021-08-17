package lifestudio.backend.domain.user.exception;

import lifestudio.backend.global.error.exception.ErrorCode;
import lifestudio.backend.global.error.exception.InvalidValueException;

public class LoginInputInvalidException extends InvalidValueException {

	public LoginInputInvalidException(final String email) {
		super(email, ErrorCode.LOGIN_INPUT_INVALID);
	}
}
