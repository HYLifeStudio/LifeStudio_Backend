package lifestudio.backend.domain.studio.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Option {

    private int shootingTime;

    private int retouchingtime;

    private boolean originalProvide;

    private int printPhoto;

    private String item;

}
