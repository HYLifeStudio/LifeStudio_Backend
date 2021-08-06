package lifestudio.backend.domain.studio.domain;


import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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

    @Embedded
    private Address address;

    @Embedded
    private Tag tag;

    @Enumerated(EnumType.STRING)
    private StudioType studioType;

    @Embedded
    private OpeningTime openingTime;

    @Embedded
    private Option option;

    private String bio;

    private String shopNumber;

    private String registrationNumber;

    private String managerName;

    @OneToOne(fetch = FetchType.LAZY)
    private Photo BusinessRegistrationPhoto;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Photo RepresentativePhoto;

    @OneToOne(fetch = FetchType.LAZY)
    private User masterUSer ;

    @Builder.Default
    @OneToMany(mappedBy = "studio")
    private List<Photo> photos = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "studio")
    private List<Review> reviews = new ArrayList<>();

    public Integer getReviewRatingAverage(){
        int sum = 0;
        if(reviews.isEmpty()){
            return null;
        } else {
            sum = this.reviews.stream()
                .mapToInt(Review::getRating)
                .sum();
            return sum/reviews.size();
        }
    }


}
