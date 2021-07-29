package lifestudio.backend.domain.photo.api;

import static lifestudio.backend.domain.studio.domain.Background.*;
import static lifestudio.backend.domain.studio.domain.Color.*;
import static lifestudio.backend.domain.studio.domain.StudioType.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.LikesDto;
import lifestudio.backend.domain.photo.dto.PhotoDto;
import lifestudio.backend.domain.photo.service.LikesService;
import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.studio.domain.Tag;
import lifestudio.backend.domain.studio.service.StudioService;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;

@SpringBootTest
@Transactional
class PhotoApiControllerTest {

	@Autowired
	PhotoApiController photoApiController;

	@Autowired
	StudioService studioService;

	@Autowired
	PhotoService photoService;

	@Autowired
	LikesService likesService;

	@Autowired
	UserService userService;

	@BeforeEach
	public void 데이터넣기(){

		Tag tag1 = new Tag(COLOR,PATTERN,true);
		Tag tag2 = new Tag(BLACKANDWHITE,PATTERN,true);

		Studio selfStudioWithTag1 = StudiowithTagAndStudioType(tag1, SELF);
		studioService.createStudio(selfStudioWithTag1);

		Studio selfStudioWithTag2 = StudiowithTagAndStudioType(tag2, SELF);
		studioService.createStudio(selfStudioWithTag2);

		Photo photo1InSelfStudioWithTag1 = PhotoWithStudio(selfStudioWithTag1);
		photoService.createPhoto(photo1InSelfStudioWithTag1);

		Photo photo2InSelfStudioWithTag1 = PhotoWithStudio(selfStudioWithTag1);
		photoService.createPhoto(photo2InSelfStudioWithTag1);

		Photo photoInSelfStudioWithTag2 = PhotoWithStudio(selfStudioWithTag2);
		photoService.createPhoto(photoInSelfStudioWithTag2);

	}

	@Test
	public void 사진만들기(){

		//given
		Studio studio = Studio.builder().build();
		Long creatStudioId = studioService.createStudio(studio);

		PhotoDto.createReq dto = PhotoDto.createReq.builder()
			.studioId(creatStudioId)
			.title("셀프사진")
			.url("www.naver.com")
			.thumbnailUrl("www.gggg.com")
			.build();

		//when
		PhotoDto.Res photoRes = photoApiController.createPhoto(dto);

		//then
		assertEquals(photoRes.getStudioId(), creatStudioId);
	}

	@Test
	public void 스튜디오유형과태그로사진찾기(){

		//when
		List<PhotoDto.Res> selfStudioWithTag1Res =  photoApiController
			.getPhotos("SELF");

		List<PhotoDto.Res> selfStudioWithTag2Res = photoApiController
			.getPhotos("SELF");
		//then
		assertEquals(2,selfStudioWithTag1Res.size());
		assertEquals(1,selfStudioWithTag2Res.size());
	}



	private Studio StudiowithTagAndStudioType(Tag tag, StudioType studioType) {
		Studio studio = Studio.builder()
			.studioType(studioType)
			.tag(tag)
			.build();
		return studio;
	}

	private Photo PhotoWithStudio(Studio studio) {
		Photo photo = Photo.builder()
			.studio(studio)
			.build();
		return photo;
	}

}