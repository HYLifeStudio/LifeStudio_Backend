package lifestudio.backend.domain.studio.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Option {

    private Integer shootingTime;

    private Integer retouchingTime;

    private Boolean originalProvide;

    private Integer printPhoto;

    private String item;

}
