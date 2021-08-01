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
	public void 스튜디오에속한사진들찾기() {

		//given
		Studio studio1 = Studio.builder()
			.build();
		studioRepository.save(studio1);

		Studio studio2 = Studio.builder()
			.build();
		studioRepository.save(studio2);

		Photo studio1Photo1 = PhotoWithStudio(studio1);
		photoRepository.save(studio1Photo1);

		Photo studio1Photo2 = PhotoWithStudio(studio1);
		photoRepository.save(studio1Photo2);

		Photo studio2Photo1 = PhotoWithStudio(studio2);
		photoRepository.save(studio2Photo1);

		//when
		List<Photo> studio1Photos = photoRepository.findByStudioId(studio1.getId());

		//then
		assertEquals(2,studio1Photos.size());
	}

	private Photo PhotoWithStudio(Studio studio) {
		Photo photo = Photo.builder()
			.studio(studio)
			.build();
		return photo;
	}

	@Test
	public void 스튜디오유형과태그로사진들찾기() {
		//given
		Tag tag1 = new Tag(COLOR,PATTERN,true);
		Tag tag2 = new Tag(BLACKANDWHITE,PATTERN,true);

		Studio selfStudioWithTag1 = StudiowithTagAndStudioType(tag1, SELF);
		studioRepository.save(selfStudioWithTag1);

		Studio selfStudioWithTag2 = StudiowithTagAndStudioType(tag2, SELF);
		studioRepository.save(selfStudioWithTag2);

		Photo photo1InSelfStudioWithTag1 = PhotoWithStudio(selfStudioWithTag1);
		photoRepository.save(photo1InSelfStudioWithTag1);

		Photo photo2InSelfStudioWithTag1 = PhotoWithStudio(selfStudioWithTag1);
		photoRepository.save(photo2InSelfStudioWithTag1);

		Photo photoInSelfStudioWithTag2 = PhotoWithStudio(selfStudioWithTag2);
		photoRepository.save(photoInSelfStudioWithTag2);

		//when
		List<Photo> tag1SelfPhotos = photoRepository
			.findByStudioType(SELF);

		List<Photo> tag2SelfPhotos = photoRepository
			.findByStudioType(SELF);
		//then
		assertEquals(3,tag1SelfPhotos.size());
		assertEquals(3,tag2SelfPhotos.size());
	}

	private Studio StudiowithTagAndStudioType(Tag tag, StudioType studioType) {
		Studio studio = Studio.builder()
			.studioType(studioType)
			.tag(tag)
			.build();
		return studio;
	}
}