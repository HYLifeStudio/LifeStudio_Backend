package lifestudio.backend.domain.review.domain;

import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.user.domain.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private int rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
