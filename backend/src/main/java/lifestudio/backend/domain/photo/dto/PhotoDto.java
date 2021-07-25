package lifestudio.backend.domain.photo.dto;

import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.studio.domain.Studio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class PhotoDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class createReq {

		private Long studioId;

		private String title;

		private String url;

		private String thumbnailUrl;

	}


	@Data
	public static class Res {

		private Long id;

		private Long studioId;

		private String title;

		private String url;

		private String thumbnailUrl;

		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		public Res(Photo photo){
			this.id = photo.getId();
			this.studioId = photo.getStudio().getId();
			this.title = photo.getTitle();
			this.url = photo.getUrl();
			this.thumbnailUrl = photo.getThumbnailUrl();
			this.createdAt = photo.getCreatedAt();
			this.updatedAt = photo.getUpdatedAt();
		}
	}

	@Data
	public static class PhotoWithLikeRes{

		private Long id;

		private Long studioId;

		private String title;

		private String url;

		private String thumbnailUrl;

		private Boolean isLiked;

		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		public PhotoWithLikeRes(Photo photo, Boolean isLiked){
			this.id = photo.getId();
			this.studioId = photo.getStudio().getId();
			this.title = photo.getTitle();
			this.url = photo.getUrl();
			this.thumbnailUrl = photo.getThumbnailUrl();
			this.isLiked = isLiked;
			this.createdAt = photo.getCreatedAt();
			this.updatedAt = photo.getUpdatedAt();
		}

	}
}
