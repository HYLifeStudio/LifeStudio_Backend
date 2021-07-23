package lifestudio.backend.domain.studio.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.studio.domain.Address;
import lifestudio.backend.domain.studio.domain.Background;
import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.Option;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.studio.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class StudioDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class createReq{

		private String studioName;

		private String studioType;

		private String bio;

		private String cityDistrict;

		private String streetAddress;

		private String zipCode;

		private String nearBy;

		private Integer shootingTime;

		private Integer retouchingTime;

		private Boolean originalProvide;

		private Integer printPhoto;

		private String item;

		private String color;

		private String background;

		private Boolean itemExist;

	}

	@Data
	public static class Res{

		private Long id;

		private String studioName;

		private StudioType studioType;

		private String bio;

		private Address address;

		private Option option;

		private Tag tag;

		public Res(Studio studio){
			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.studioType = studio.getStudioType();
			this.address = studio.getAddress();
			this.option = studio.getOption();
			this.tag = studio.getTag();
		}
	}
}


