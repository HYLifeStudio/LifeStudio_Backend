package lifestudio.backend.domain.studio.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Tag {

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Background background;

    private Boolean itemExist;

    public Tag(){}

    public Tag(Color color, Background background, Boolean itemExist) {
        this.color = color;
        this.background = background;
        this.itemExist = itemExist;
    }
}
