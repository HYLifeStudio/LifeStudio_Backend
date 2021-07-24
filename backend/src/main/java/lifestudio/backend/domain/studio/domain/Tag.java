package lifestudio.backend.domain.studio.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Tag {

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Background background;

    private Boolean itemExist;

    public String colorRes() {
        if (this.color == Color.COLOR){
            return "컬러사진";
        } else if (this.color == Color.BLACKANDWHITE){
            return "흑백사진";
        } else {
            return "";
        }
    }

    public String backgroundRes() {
        if (this.background == Background.SOLID){
            return "단색배경";
        } else if (this.background == Background.PATTERN){
            return "무늬배경";
        } else if (this.background == Background.GRADATION){
            return "그라데이션배경";
        } else {
            return "";
        }
    }

    public String itemExistRes(){
        if (this.itemExist){
            return "소품제공";
        } else {
            return "소품미제공";
        }
    }



}
