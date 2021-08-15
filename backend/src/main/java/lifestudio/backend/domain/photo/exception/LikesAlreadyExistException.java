package lifestudio.backend.domain.photo.exception;

import lifestudio.backend.global.error.exception.ErrorCode;
import lifestudio.backend.global.error.exception.InvalidValueException;

public class LikesAlreadyExistException extends InvalidValueException {

		public LikesAlreadyExistException(final String likes) {
			super(likes, ErrorCode.LIKES_ALREADY_EXIST);
		}
}
