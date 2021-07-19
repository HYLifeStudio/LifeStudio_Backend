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
		Address sungdongAddress = AddressWithCityDistrict("서울시 성동구");
		Address gangnamAddress = AddressWithCityDistrict("서울시 강남구");

		Studio selfStudioInSungdong1 = StudioWithAddressAndStudioType(sungdongAddress, SELF);
		studioRepository.save(selfStudioInSungdong1);

		Studio selfStudioInSungdong2 = StudioWithAddressAndStudioType(sungdongAddress, SELF);
		studioRepository.save(selfStudioInSungdong2);

		Studio idPhotoStudioInGangnam1 = StudioWithAddressAndStudioType(gangnamAddress, IDPHOTO);
		studioRepository.save(idPhotoStudioInGangnam1);

		Studio idPhotoStudioInGangnam2 = StudioWithAddressAndStudioType(gangnamAddress, IDPHOTO);
		studioRepository.save(idPhotoStudioInGangnam2);

		//when
		List<Studio> selfStudiosInSungdong = studioRepository
			.findByStudioTypeAndCityDistrict(SELF, "서울시 성동구");

		List<Studio> idPhotoStudiosInGangnam = studioRepository
			.findByStudioTypeAndCityDistrict(IDPHOTO, "서울시 강남구");

		//then
		assertEquals(2,selfStudiosInSungdong.size());
		assertEquals(2,idPhotoStudiosInGangnam.size());

	}

	private Address AddressWithCityDistrict(String cityDistrict) {
		Address address = new Address();
		address.setCityDistrict(cityDistrict);
		return address;
	}

	private Studio StudioWithAddressAndStudioType(Address address, StudioType studioType) {
		Studio studio = new Studio();
		studio.setStudioType(studioType);
		studio.setAddress(address);
		return studio;
	}

}