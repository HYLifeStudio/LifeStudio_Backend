package lifestudio.backend.domain.review.exception;

import lifestudio.backend.global.error.exception.EntityNotFoundException;

public class ReservationNotFoundException extends EntityNotFoundException {
    public ReservationNotFoundException(Long target)  {
        super(target + " is not found");
    }
}
