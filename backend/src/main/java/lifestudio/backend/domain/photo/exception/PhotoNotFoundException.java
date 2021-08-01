package lifestudio.backend.domain.photo.exception;

import lifestudio.backend.global.error.exception.EntityNotFoundException;

public class PhotoNotFoundException extends EntityNotFoundException {

	public PhotoNotFoundException(Long target) {
		super(target + " is not found");
	}

}
