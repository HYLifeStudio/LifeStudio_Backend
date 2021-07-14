package lifestudio.backend.domain.user.domain;

import lifestudio.backend.domain.photo.domain.Photo;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

}
