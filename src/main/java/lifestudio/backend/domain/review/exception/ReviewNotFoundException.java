package lifestudio.backend.domain.review.exception;

import lifestudio.backend.global.error.exception.EntityNotFoundException;

public class ReviewNotFoundException extends EntityNotFoundException {

	public ReviewNotFoundException(Long target) {
		super(target + " is not found");
	}

}
