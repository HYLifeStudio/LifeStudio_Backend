package lifestudio.backend.domain.review.dto;

import lifestudio.backend.domain.review.domain.Reservation;
import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.studio.domain.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ReservationDto {

    @Data
    @AllArgsConstructor
    @Builder
    public static class createReq{

        private Long studioId;

        private Long userId;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime reservationTime;

        private Color color;

        private Boolean background;

        private Integer people;

        private String requirement;

        private Boolean approve;

    }

    @Data
    public static class Res {

        private Long id;

        private Long studioId;

        private String studioName;

        private String studioType;

        private Long userId;

        private LocalDateTime resrvationTime;

        private Color color;

        private Boolean background;

        private Integer people;

        private String requirement;

        private boolean approve;

        public Res(Reservation reservation){
            this.id = reservation.getId();
            this.studioId = reservation.getStudio().getId();
            this.studioName = reservation.getStudio().getStudioName();
            this.studioType = reservation.getStudio().getStudioType().toString();
            this.userId = reservation.getUser().getId();
            this.resrvationTime = reservation.getReservationTime();
            this.color = reservation.getColor();
            this.background = reservation.getBackground();
            this.people = reservation.getPeople();
            this.requirement = reservation.getRequirement();
            this.approve = reservation.getApprove();
        }

    }
}
