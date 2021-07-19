package lifestudio.backend.domain.user.domain;


import lifestudio.backend.domain.photo.domain.Likes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String username;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDateTime birth;

    private String email;

    private String nickname;

    private String phone;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

}
