package lifestudio.backend.domain.photo.domain;


import lifestudio.backend.domain.studio.domain.Studio;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    private String title;

    private String url;

    private String thumbnailUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
