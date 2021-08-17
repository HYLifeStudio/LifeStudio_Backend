package lifestudio.backend.domain.user.exception;

import lifestudio.backend.global.error.exception.ErrorCode;
import lifestudio.backend.global.error.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

	public EmailDuplicateException(final String email) {
		super(email, ErrorCode.EMAIL_DUPLICATION);
	}
}
