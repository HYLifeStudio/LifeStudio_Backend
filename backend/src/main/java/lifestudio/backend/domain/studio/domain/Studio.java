package lifestudio.backend.domain.studio.domain;


import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Studio {

    @Id
    @GeneratedValue
    @Column(name = "studio_id")
    private Long id;

    private String studioName;

    @Enumerated(EnumType.STRING)
    private StudioType studioType;

    private String bio;

    @Embedded
    private Address address;

    @Embedded
    private Option option;

    @Embedded
    private Tag tag;

}
