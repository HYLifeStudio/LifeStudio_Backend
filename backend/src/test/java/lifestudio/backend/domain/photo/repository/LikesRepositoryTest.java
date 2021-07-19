package lifestudio.backend.domain.photo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@Transactional
class LikesRepositoryTest {

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LikesRepository likeRepository;

	@Test
	public void 기본CRUD() {

		//given
		Likes like1 = new Likes();
		Likes like2 = new Likes();
		likeRepository.save(like1);
		likeRepository.save(like2);

		//when
		Likes findLike1 = likeRepository.findById(like1.getId()).get();
		Likes findLike2 = likeRepository.findById(like2.getId()).get();
		List<Likes> likes = likeRepository.findAll();

		//then
		assertEquals(like1, findLike1);
		assertEquals(like2, findLike2);
		assertEquals(likes.size(),2);

	}

	@Test
	public void 로그인한유저가누른사진좋아요찾기() {
		//given
		User user1 = new User();
		User user2 = new User();
		userRepository.save(user1);
		userRepository.save(user2);

		Photo photo1 = new Photo();
		Photo photo2 = new Photo();
		photoRepository.save(photo1);
		photoRepository.save(photo2);

		Likes like1 = new Likes();
		Likes like2 = new Likes();
		Likes like3 = new Likes();
		likeRepository.save(like1);
		likeRepository.save(like2);
		likeRepository.save(like3);

		like1.setUser(user1);
		like1.setPhoto(photo1);

		like2.setUser(user2);
		like2.setPhoto(photo1);

		like3.setUser(user2);
		like3.setPhoto(photo2);

		//when
		Likes User1Photo1 = likeRepository.findByPhotoIdAndUserId(user1.getId(),photo1.getId()).get();
		Likes User2Photo1 = likeRepository.findByPhotoIdAndUserId(user2.getId(),photo1.getId()).get();
		Likes User2Photo2 = likeRepository.findByPhotoIdAndUserId(user2.getId(), photo2.getId()).get();

		//then
		assertEquals(like1,User1Photo1);
		assertEquals(like2, User2Photo1);
		assertEquals(like3, User2Photo2);
	}

}