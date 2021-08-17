package lifestudio.backend.domain.photo.exception;

import lifestudio.backend.global.error.exception.EntityNotFoundException;

public class LikesNotFoundException extends EntityNotFoundException {

	public LikesNotFoundException(Long target) {
		super(target + " is not found");
	}

	public LikesNotFoundException(Long userId, Long photoId) {
		super(userId + "and" + photoId + " are not found");
	}
}
