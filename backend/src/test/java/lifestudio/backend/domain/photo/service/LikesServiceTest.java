package lifestudio.backend.domain.photo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;

@SpringBootTest
@Transactional
class LikesServiceTest {

	@Autowired
	LikesService likesService;

	@Autowired
	PhotoService photoService;

	@Autowired
	UserService userService;

	@Test
	public void 좋아요누르기(){

		//given
		User user1 = User.builder().build();
		Long user1Id  = userService.createUser(user1);

		Photo photo1 = Photo.builder().build();
		Long photo1Id = photoService.createPhoto(photo1);

		Likes user1Photo1Likes = Likes.builder()
			.user(user1)
			.photo(photo1)
			.isLiked(true)
			.build();

		likesService.createLikes(user1Photo1Likes);

		//when
		Long user1Photo1LikesId = likesService.updateLikes(user1Photo1Likes.getId());
		Likes findUser1Photo1Likes = likesService.findById(user1Photo1LikesId);

		//then
		assertEquals(false, findUser1Photo1Likes.getIsLiked());
	}

	@Test
	public void 사진과유저아이디로좋아요찾기(){

		//given
		User user1 = User.builder().build();
		Long user1Id  = userService.createUser(user1);

		Photo photo1 = Photo.builder().build();
		Long photo1Id = photoService.createPhoto(photo1);

		Likes user1Photo1Likes = Likes.builder()
			.user(user1)
			.photo(photo1)
			.isLiked(true)
			.build();

		likesService.createLikes(user1Photo1Likes);

		//when
		List<Likes> findUser1Photo1Likes = likesService.findByUserIdAndPhotoId(user1Id,photo1Id);

		//then
		assertEquals(1, findUser1Photo1Likes.size());

	}




}