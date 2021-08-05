package lifestudio.backend.domain.studio.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.PhotoDto;
import lifestudio.backend.domain.review.dto.ReviewDto;
import lifestudio.backend.domain.studio.domain.OpeningTime;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

public class StudioDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class createReq{

		private String studioName;

		private String cityDistrict;

		private String streetAddress;

		private String nearBy;

		private String studioType;

		private String color;

		private Boolean background;

		private Boolean monday;

		private Boolean tuesday;

		private Boolean wednesday;

		private Boolean thursday;

		private Boolean friday;

		private Boolean saturday;

		private Boolean sunday;

		private String openTime;

		private String closeTime;

		private Integer shootingTime;

		private Integer retouchingTime;

		private Boolean originalProvide;

		private Integer printPhoto;

		private Boolean itemExist;

		private String item;

		private String bio;

		private String shopNumber;

		private String registrationNumber;

		private String managerName;

	}

	@Data
	public static class summaryRes {

		private Long id;

		private String studioName;

		private AddressDto.Res address;

		private OptionDto.Res options;

		private PhotoDto.Res representativePhoto;

		private Integer ratingAverage;

		public summaryRes(Studio studio) {
			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.address = new AddressDto.Res(studio.getAddress());
			this.options = new OptionDto.Res(studio.getOption());
			this.representativePhoto = new PhotoDto.Res(studio.getRepresentativePhoto());
			this.ratingAverage = studio.getReviewRatingAverage();
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

		private OpeningTimeDto.Res openingTime;

		private String shopNumber;

		private String registrationNumber;

		private String managerName;

		private PhotoDto.Res businessRegistrationPhoto;

		private PhotoDto.Res representativePhoto;

		private List<PhotoDto.Res> photos = new ArrayList<PhotoDto.Res>();

		private List<ReviewDto.Res> reviews = new ArrayList<ReviewDto.Res>();

		public Res(Studio studio){

			Photo representativePhoto = studio.getRepresentativePhoto();
			Photo businessRegistrationPhoto = studio.getBusinessRegistrationPhoto();

			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.studioType = studio.getStudioType();
			this.bio = studio.getBio();
			this.address = new AddressDto.Res(studio.getAddress());
			this.option = new OptionDto.Res(studio.getOption());
			this.tag = new TagDto.Res(studio.getTag());
			this.openingTime = new OpeningTimeDto.Res(studio.getOpeningTime());
			this.shopNumber = studio.getShopNumber();
			this.registrationNumber = studio.getRegistrationNumber();
			this.managerName = studio.getManagerName();
			this.representativePhoto = representativePhoto == null ? null :
					new PhotoDto.Res(representativePhoto);
			this.businessRegistrationPhoto = businessRegistrationPhoto == null ? null :
					new PhotoDto.Res(businessRegistrationPhoto);
			this.photos = Optional.ofNullable(studio.getPhotos())
				.map(Collection::stream)
				.orElseGet(Stream::empty)
				.map(p->new PhotoDto.Res(p))
				.collect(Collectors.toList());
			this.reviews = Optional.ofNullable(studio.getReviews())
				.map(Collection::stream)
				.orElseGet(Stream::empty)
				.map(r->new ReviewDto.Res(r))
				.collect(Collectors.toList());
		}
	}
}


