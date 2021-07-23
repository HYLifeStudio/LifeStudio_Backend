package lifestudio.backend.domain.review.dto;

import java.time.LocalDateTime;

import lifestudio.backend.domain.review.domain.Review;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class ReviewDto {

	@Data
	@AllArgsConstructor
	@Builder
	public static class createReq{

		private Long studioId;

		private Long userId;

		private String content;

		private Integer rating;

	}

	@Data
	public static class Res {

		private Long id;

		private Studio studio;

		private User user;

		private String content;

		private Integer rating;

		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		public Res(Review review){
			this.id = review.getId();
			this.studio = review.getStudio();
			this.user = review.getUser();
			this.content = review.getContent();
			this.rating = review.getRating();
			this.createdAt = review.getCreatedAt();
			this.updatedAt = review.getUpdatedAt();
		}

	}
}
