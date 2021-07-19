package lifestudio.backend.domain.studio.repository;

import static lifestudio.backend.domain.studio.domain.StudioType.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.studio.domain.Address;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;

@SpringBootTest
@Transactional
class StudioRepositoryTest {

	@Autowired
	StudioRepository studioRepository;

	@Test
	public void 지역과유형으로조회() {

		//given
		Address address1 = new Address();
		address1.setCityDistrict("서울시 성동구");

		Address address2 = new Address();
		address2.setCityDistrict("서울시 강남구");

		Studio studio1 = new Studio();
		studio1.setStudioType(SELF);
		studio1.setAddress(address1);
		studioRepository.save(studio1);

		Studio studio2 = new Studio();
		studio2.setStudioType(IDPHOTO);
		studio2.setAddress(address2);
		studioRepository.save(studio2);

		Studio studio3 = new Studio();
		studio3.setStudioType(SELF);
		studio3.setAddress(address1);
		studioRepository.save(studio3);

		Studio studio4 = new Studio();
		studio4.setStudioType(IDPHOTO);
		studio4.setAddress(address2);
		studioRepository.save(studio4);

		//when
		List<Studio> SungdongguSelfStudios = studioRepository
			.findByStudioTypeAndCityDistrict(SELF, "서울시 성동구");

		List<Studio> GangnamguIdPhotoStudios = studioRepository
			.findByStudioTypeAndCityDistrict(IDPHOTO, "서울시 강남구");

		//then
		assertEquals(2,SungdongguSelfStudios.size());
		assertEquals(2,GangnamguIdPhotoStudios.size());

	}




}