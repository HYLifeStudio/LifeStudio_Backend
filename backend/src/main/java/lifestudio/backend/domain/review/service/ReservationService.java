package lifestudio.backend.domain.review.service;

import lifestudio.backend.domain.review.domain.Reservation;
import lifestudio.backend.domain.review.exception.ReservationNotFoundException;
import lifestudio.backend.domain.review.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Long createReservation(Reservation reservation){
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public Reservation findById(Long reservationId){
        Optional<Reservation> findReservation = reservationRepository.findById(reservationId);
        if (findReservation.isEmpty()){
            throw new ReservationNotFoundException(reservationId);
        } else {
            return findReservation.get();
        }
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public List<Reservation> findByStudioId(Long studioId){
        return reservationRepository.findByStudioId(studioId);
    }

    public List<Reservation> findByUserId(Long userId){
        return reservationRepository.findByUserId(userId);
    }

    @Transactional
    public Long deleteById(Long reservationId){
        reservationRepository.deleteById(reservationId);
        return reservationId;
    }
}
