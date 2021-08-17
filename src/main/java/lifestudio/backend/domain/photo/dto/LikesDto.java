package lifestudio.backend.domain.photo.dto;

import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class LikesDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class createReq {

		private Long userId;

		private Long photoId;

	}

	@Data
	public static class Res {

		private Long id;

		private Long userId;

		private PhotoDto.Res photo;

		private Boolean isLiked;

		public Res(Likes likes){
			this.id = likes.getId();
			this.userId = likes.getUser().getId();
			this.photo = new PhotoDto.Res(likes.getPhoto());
			this.isLiked = likes.getIsLiked();
		}
	}
}
