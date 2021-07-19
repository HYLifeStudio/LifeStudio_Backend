package lifestudio.backend.domain.photo.domain;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Likes {

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

    private Boolean isLiked;

}
