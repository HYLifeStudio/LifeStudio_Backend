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
public class Address {

    private String cityDistrict;
    private String streetAddress;
    private String nearBy;

    public String summaryAddress(){
        return this.cityDistrict + " " + this.streetAddress;
    }

    public String detailAddress() {
        return this.nearBy + " " + this.cityDistrict + " " + this.streetAddress ;
    }
}
