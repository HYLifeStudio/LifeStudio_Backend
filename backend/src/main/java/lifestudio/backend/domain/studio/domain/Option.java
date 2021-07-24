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

    public String timeSummary(){
        return "제한시간 " + (this.shootingTime+this.retouchingTime) + "분";
    }

    public String originalProvideSummary(){
        return this.originalProvide ? "원본파일 무료제공" : "원본파일 제공X";
    }

    public String printPhotoSummary(){
        return "인화사진 " + this.printPhoto + "장제공";
    }

    public String  itemSummary(){
        return this.item == null ? "소품 X" : "소품 무료제공";
    }

    public String timeDetail(){
        return "기본 촬영 " + this.shootingTime + "분, " + "사진 보정 " + this.retouchingTime + "분 "
            + "총 " + ( this.shootingTime + this.retouchingTime ) + "분";
    }

    public String provideDetail(){
        String orignalProvideDetail = this.originalProvide ? "원본파일 무료제공, " : "원본파일 제공X, ";
        String printPhotoDetail = "인화사진 " + this.printPhoto + "장 기본 제공";
        return orignalProvideDetail + printPhotoDetail;
    }


    public String  itemDetail(){
        if (this.item.isEmpty()){
            return "소품을 제공하지 않습니다.";
        } else {
            return this.item;
        }
    }
}
