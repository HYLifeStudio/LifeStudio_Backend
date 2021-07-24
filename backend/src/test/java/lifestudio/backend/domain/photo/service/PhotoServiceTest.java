package lifestudio.backend.domain.photo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;

@SpringBootTest
@Transactional
class PhotoServiceTest {

	@Autowired
	PhotoService photoService;

	@Autowired
	UserService userService;

	@Autowired
	LikesService likesService;

	@Test
	public void 사진에유저가좋아요했는지확인(){

		//given
		User user1 = User.builder().build();
		Long user1Id  = userService.createUser(user1);

		Photo photo1 = Photo.builder().build();
		Long photo1Id = photoService.createPhoto(photo1);

		Photo photo2 = Photo.builder().build();
		Long photo2Id = photoService.createPhoto(photo2);

		likesService.updateLikes(user1Id,photo1Id);

		//when
		Boolean isLikedPhoto1 = photoService.LikeCheck(photo1Id,user1Id);
		Boolean isLikedPhoto2 = photoService.LikeCheck(photo2Id,user1Id);

		//then
		assertEquals(true,isLikedPhoto1);
		assertEquals(false,isLikedPhoto2);

	}
}