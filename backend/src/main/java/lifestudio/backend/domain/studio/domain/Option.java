package lifestudio.backend.domain.studio.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Option {

    private Integer shootingTime;

    private Integer retouchingTime;

    private Boolean originalProvide;

    private Integer printPhoto;

    private String item;

}
