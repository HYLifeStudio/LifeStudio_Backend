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

    @OneToMany(mappedBy = "studio")
    private List<Photo> photos = new ArrayList<>();

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
