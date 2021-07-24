package lifestudio.backend.domain.photo.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.LikesDto;
import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;

@SpringBootTest
@Transactional
class LikesApiControllerTest {

	@Autowired
	UserService userService;

	@Autowired
	PhotoService photoService;

	@Autowired
	LikesApiController likesApiController;

	@Test
	public void 좋아요누르기(){

		//given
		User user1 = User.builder().build();
		Long user1Id = userService.createUser(user1);

		Photo photo1 = Photo.builder().build();
		Long photo1Id = photoService.createPhoto(photo1);

		LikesDto.createReq dto = LikesDto.createReq.builder()
			.photoId(photo1Id)
			.userId(user1Id)
			.build();

		//when
		LikesDto.Res likeRes = likesApiController.LikeByPhotoAndUserId(dto);
		LikesDto.Res AfterlikeRes = likesApiController.LikeByPhotoAndUserId(dto);

		//then
		assertEquals(true, likeRes.getIsLiked());
		assertEquals(false, AfterlikeRes.getIsLiked());

	}


}