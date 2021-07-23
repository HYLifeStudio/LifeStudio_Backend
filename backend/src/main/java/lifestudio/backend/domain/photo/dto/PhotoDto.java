package lifestudio.backend.domain.photo.dto;

import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.studio.domain.Studio;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PhotoDto {

	@Data
	@AllArgsConstructor
	public static class createReq {

		private Long studioId;

		private String title;

		private String url;

		private String thumbnailUrl;

	}


	public static class Res {

		private Long id;

		private Studio studio;

		private String title;

		private String url;

		private String thumbnailUrl;

		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		public Res(Photo photo){
			this.id = photo.getId();
			this.studio = photo.getStudio();
			this.title = photo.getTitle();
			this.url = photo.getUrl();
			this.thumbnailUrl = photo.getThumbnailUrl();
			this.createdAt = photo.getCreatedAt();
			this.updatedAt = photo.getUpdatedAt();
		}
	}
}