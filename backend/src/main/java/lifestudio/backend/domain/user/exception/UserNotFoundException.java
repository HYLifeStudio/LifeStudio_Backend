package lifestudio.backend.domain.user.exception;

import lifestudio.backend.global.error.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

	public UserNotFoundException(Long target) {
		super(target + " is not found");
	}

	public UserNotFoundException(String target) {
		super(target + " is not found");
	}
}
