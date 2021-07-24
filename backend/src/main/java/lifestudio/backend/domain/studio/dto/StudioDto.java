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
	public static class summaryRes {

		private Long id;

		private String studioName;

		private AddressDto.Res address;

		private OptionDto.Res options;

		private String thumbnailUrl;

		private Integer rating;

		public summaryRes(Studio studio){
			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.address = new AddressDto.Res(studio.getAddress());
			this.options = new OptionDto.Res(studio.getOption());
		}

	}

	@Data
	public static class Res{

		private Long id;

		private String studioName;

		private StudioType studioType;

		private String bio;

		private AddressDto.Res address;

		private OptionDto.Res option;

		private TagDto.Res tag;

		public Res(Studio studio){
			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.studioType = studio.getStudioType();
			this.bio = studio.getBio();
			this.address = new AddressDto.Res(studio.getAddress());
			this.option = new OptionDto.Res(studio.getOption());
			this.tag = new TagDto.Res(studio.getTag());
		}
	}
}


