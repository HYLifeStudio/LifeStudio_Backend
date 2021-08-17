package lifestudio.backend.domain.review.repository;

import lifestudio.backend.domain.review.domain.Reservation;
import lifestudio.backend.domain.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public void save(Reservation reservation) {
        if (reservation.getId() == null) {
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
    }

    public void delete(Reservation reservation){
        em.remove(reservation);
    }

    public void deleteById(Long id){
        Reservation reservation = em.find(Reservation.class, id);
        em.remove(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        Reservation reservation = em.find(Reservation.class, id);
        return Optional.ofNullable(reservation);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findByStudioId(Long studioId) {
        return em.createQuery("select r from Reservation r where r.studio.id = :studioId", Reservation.class)
                .setParameter("studioId", studioId)
                .getResultList();
    }

    public List<Reservation> findByUserId(Long userId) {
        return em.createQuery("select r from Reservation r where r.user.id = :userId", Reservation.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
