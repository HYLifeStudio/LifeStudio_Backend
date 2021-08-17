package lifestudio.backend.domain.studio.exception;

import lifestudio.backend.global.error.exception.EntityNotFoundException;

public class StudioNotFoundException extends EntityNotFoundException {

	public StudioNotFoundException(Long target) {
		super(target + " is not found");
	}
}
