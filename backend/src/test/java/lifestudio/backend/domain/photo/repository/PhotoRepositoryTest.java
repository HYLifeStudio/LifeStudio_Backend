package lifestudio.backend.domain.photo.repository;

import static lifestudio.backend.domain.studio.domain.Background.*;
import static lifestudio.backend.domain.studio.domain.Color.*;
import static lifestudio.backend.domain.studio.domain.StudioType.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.studio.domain.Tag;
import lifestudio.backend.domain.studio.repository.StudioRepository;


@SpringBootTest
@Transactional
class PhotoRepositoryTest {

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	StudioRepository studioRepository;

	@Test
	public void 스튜디오아이디로사진찾기() {

		//given
		Studio studio1 = new Studio();
		Studio studio2 = new Studio();
		studioRepository.save(studio1);
		studioRepository.save(studio2);

		Photo photo1 = new Photo();
		photo1.setStudio(studio1);
		photoRepository.save(photo1);

		Photo photo2 = new Photo();
		photo2.setStudio(studio1);
		photoRepository.save(photo2);

		Photo photo3 = new Photo();
		photo3.setStudio(studio2);
		photoRepository.save(photo3);

		//when
		List<Photo> studio1Photos = photoRepository.findByStudioId(studio1.getId());

		//then
		assertEquals(2,studio1Photos.size());
	}

	@Test
	public void 스튜디오유형과태그로사진들찾기() {
		//given
		Tag tag1 = new Tag();
		tag1.setColor(COLOR);
		tag1.setBackground(PATTERN);
		tag1.setItemExist(true);

		Tag tag2 = new Tag();
		tag2.setColor(BLACKANDWHITE);
		tag2.setBackground(PATTERN);
		tag2.setItemExist(true);

		Studio studio1 = new Studio();
		studio1.setStudioType(SELF);
		studio1.setTag(tag1);
		studioRepository.save(studio1);

		Studio studio2 = new Studio();
		studio2.setStudioType(SELF);
		studio2.setTag(tag2);
		studioRepository.save(studio2);

		Photo photo1 = new Photo();
		photo1.setStudio(studio1);
		photoRepository.save(photo1);

		Photo photo2 = new Photo();
		photo2.setStudio(studio1);
		photoRepository.save(photo2);

		Photo photo3 = new Photo();
		photo3.setStudio(studio2);
		photoRepository.save(photo3);

		//when
		List<Photo> tag1SelfPhotos = photoRepository
			.findByTagsAndStudioType(SELF,COLOR,PATTERN,true );

		List<Photo> tag2SelfPhotos = photoRepository
			.findByTagsAndStudioType(SELF,BLACKANDWHITE,PATTERN,true );
		//then
		assertEquals(2,tag1SelfPhotos.size());
		assertEquals(1,tag2SelfPhotos.size());
	}
}