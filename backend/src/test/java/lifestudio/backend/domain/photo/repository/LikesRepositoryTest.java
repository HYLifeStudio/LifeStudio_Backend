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
		Likes like1 = Likes.builder().build();
		likeRepository.save(like1);

		Likes like2 = Likes.builder().build();
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
		User user1 = User.builder().build();
		userRepository.save(user1);

		User user2 = User.builder().build();
		userRepository.save(user2);

		Photo photo1 = Photo.builder().build();
		photoRepository.save(photo1);

		Photo photo2 = Photo.builder().build();
		photoRepository.save(photo2);

		Likes likeWithUser1AndPhoto1 = LikesWithUserAndPhoto(user1, photo1);
		likeRepository.save(likeWithUser1AndPhoto1);

		Likes likeWithUser2AndPhoto1 = LikesWithUserAndPhoto(user2, photo1);
		likeRepository.save(likeWithUser2AndPhoto1);

		Likes likeWithUser2AndPhoto2 = LikesWithUserAndPhoto(user2, photo2);
		likeRepository.save(likeWithUser2AndPhoto2);

		//when
		Likes findLikeWithUser1Photo1 = likeRepository.findByUserIdAndPhotoId(user1.getId(),photo1.getId()).get(0);
		Likes findLikeWithUser2Photo1 = likeRepository.findByUserIdAndPhotoId(user2.getId(),photo1.getId()).get(0);
		Likes findLikeWithUser2Photo2 = likeRepository.findByUserIdAndPhotoId(user2.getId(), photo2.getId()).get(0);

		//then
		assertEquals(likeWithUser1AndPhoto1, findLikeWithUser1Photo1);
		assertEquals(likeWithUser2AndPhoto1, findLikeWithUser2Photo1);
		assertEquals(likeWithUser2AndPhoto2, findLikeWithUser2Photo2);
	}

	private Likes LikesWithUserAndPhoto(User user, Photo photo) {
		Likes like = Likes.builder()
			.user(user)
			.photo(photo)
			.build();
		return like;
	}

}