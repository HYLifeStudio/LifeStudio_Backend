package lifestudio.backend.domain.studio.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.studio.dto.StudioDto;

@SpringBootTest
@Transactional
class StudioApiControllerTest {

	@Autowired
	StudioApiController studioApiController;

	@Test
	public void 스튜디오생성(){
		//given
		StudioDto.createReq dto = getSampleDto("SELF","서울시 양천구");

		//when
		StudioDto.Res studioRes = studioApiController.createStudio(dto);

		//then
		assertEquals(studioRes.getStudioName(),dto.getStudioName());
	}

	private StudioDto.createReq getSampleDto(String type, String cityDistrict) {
		return StudioDto.createReq.builder()
			.studioName("인생사진관")
			.studioType(type)
			.bio("그 누구의 방해없이 혼자 스스로 자유롭게\n"
				+ "전문가의 장비와 시설을 사용하여\n"
				+ "고퀄리티의 화보급 사진을 찍으실 수 있습니다.\n"
				+ "사진관을 넘어선 하나의 트랜디한 문화 공간입니다.")
			.cityDistrict(cityDistrict)
			.streetAddress("목동동로 177 2차 306")
			.zipCode("22-342")
			.nearBy("오목교역 3번출구 8분거리")
			.shootingTime(30)
			.retouchingTime(15)
			.originalProvide(true)
			.printPhoto(3)
			.item("각종 머리띠 (20종 이상 구비), 가면, 가발 \n"
				+ "교복 및 드레스, 정장 의상 대여 가능\n"
				+ "4종 나무 의자, 벤치, 나무 박스, ")
			.color("COLOR")
			.background("PATTERN")
			.itemExist(true)
			.build();
	}

	@Test
	public void 조회및삭제(){
		//given
		StudioDto.createReq selfYangchunDto1 = getSampleDto("SELF","서울시 양천구");
		StudioDto.createReq selfYangchunDto2 = getSampleDto("SELF","서울시 양천구");
		StudioDto.createReq bodySungdongDto1 = getSampleDto("BODYPROFILE","서울시 성동구");

		StudioDto.Res selfYangchunRes1 = studioApiController.createStudio(selfYangchunDto1);
		StudioDto.Res slefYangchunRes2 = studioApiController.createStudio(selfYangchunDto2);
		StudioDto.Res bodySungdongRes = studioApiController.createStudio(bodySungdongDto1);

		//when
		List<StudioDto.summaryRes> allStudios = studioApiController.getStudios(null,null);
		List<StudioDto.summaryRes> selfYangchunStudios = studioApiController.getStudios("SELF","서울시 양천구");
		StudioDto.Res fingBodySungdongRes = studioApiController.getStudio(bodySungdongRes.getId());

		//then
		assertEquals(3,allStudios.size());
		assertEquals(2,selfYangchunStudios.size());
		assertEquals(bodySungdongRes.getId(),fingBodySungdongRes.getId());

		//삭제

		//when
		studioApiController.deleteStudio(selfYangchunRes1.getId());
		List<StudioDto.summaryRes> selfYangchunStudiosAfterDelete = studioApiController.getStudios("SELF","서울시 양천구");

		//then
		assertEquals(1,selfYangchunStudiosAfterDelete.size());


	}



}