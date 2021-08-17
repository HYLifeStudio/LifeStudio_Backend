package lifestudio.backend.domain.review.domain;

import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime reservationTime;

    private Color color;

    private Boolean background;

    private Integer people;

    private String requirement;

    private Boolean approve;

    public void changeApprove(){
       this.approve = !this.approve;
    }

}
