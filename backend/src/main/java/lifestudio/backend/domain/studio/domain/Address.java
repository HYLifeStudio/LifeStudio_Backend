package lifestudio.backend.domain.studio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {

    private String cityDistrict;
    private String streetAddress;
    private String zipCode;
    private String nearBy;

}
