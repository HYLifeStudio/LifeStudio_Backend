package lifestudio.backend.domain.studio.dto;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.cglib.core.CollectionUtils;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.PhotoDto;
import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.review.dto.ReviewDto;
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

		private PhotoDto.Res representativePhoto;

		private Integer ratingAverage;

		public summaryRes(Studio studio) {
			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.address = new AddressDto.Res(studio.getAddress());
			this.options = new OptionDto.Res(studio.getOption());
			this.representativePhoto = studio.getPhotos().isEmpty() ? null : new PhotoDto.Res(studio.getPhotos().get(0));
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

		private List<PhotoDto.Res> photos = new ArrayList<PhotoDto.Res>();

		private List<ReviewDto.Res> reviews = new ArrayList<ReviewDto.Res>();


		public Res(Studio studio){
			this.id = studio.getId();
			this.studioName = studio.getStudioName();
			this.studioType = studio.getStudioType();
			this.bio = studio.getBio();
			this.address = new AddressDto.Res(studio.getAddress());
			this.option = new OptionDto.Res(studio.getOption());
			this.tag = new TagDto.Res(studio.getTag());
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


