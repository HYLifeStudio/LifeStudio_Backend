package lifestudio.backend.domain.studio.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String cityDistrict;
    private String streetAddress;
    private String zipcode;
    private String nearby;

}
