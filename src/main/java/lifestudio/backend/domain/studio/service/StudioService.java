package lifestudio.backend.domain.studio.service;

import java.util.List;
import java.util.Optional;

import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.studio.domain.*;
import lifestudio.backend.domain.studio.dto.StudioDto;
import lifestudio.backend.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.studio.exception.StudioNotFoundException;
import lifestudio.backend.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudioService {

	private final StudioRepository studioRepository;

	@Transactional
	public Long createStudio(Studio studio){
		studioRepository.save(studio);
		return studio.getId();
	}

	@Transactional
	public Long updateStudio(Long studioId, StudioDto.updateReq dto){
		Studio updateStudio = findById(studioId);

		Address address = Address.builder()
				.cityDistrict(dto.getCityDistrict())
				.streetAddress(dto.getStreetAddress())
				.nearBy(dto.getNearBy())
				.build();

		Option option = Option.builder()
				.shootingTime(dto.getShootingTime())
				.retouchingTime(dto.getRetouchingTime())
				.originalProvide(dto.getOriginalProvide())
				.printPhoto(dto.getPrintPhoto())
				.item(dto.getItem())
				.build();

		Tag tag = Tag.builder()
				.color(Color.valueOf(dto.getColor()))
				.background(dto.getBackground())
				.itemExist(dto.getItemExist())
				.build();

		OpeningTime openingTime = OpeningTime.builder()
				.openTime(dto.getOpenTime())
				.closeTime(dto.getCloseTime())
				.monday(dto.getMonday())
				.tuesday(dto.getTuesday())
				.wednesday(dto.getWednesday())
				.thursday(dto.getThursday())
				.friday(dto.getFriday())
				.saturday(dto.getSaturday())
				.sunday(dto.getSunday())
				.build();

		updateStudio.setStudioName(dto.getStudioName());
		updateStudio.setAddress(address);
		updateStudio.setStudioType(StudioType.valueOf(dto.getStudioType()));
		updateStudio.setTag(tag);
		updateStudio.setOpeningTime(openingTime);
		updateStudio.setOption(option);
		updateStudio.setBio(dto.getBio());
		updateStudio.setOption(option);

		return studioId;
	}

	public Studio findById(Long studioId){
		Optional<Studio> findStudio = studioRepository.findById(studioId);
		if (findStudio.isEmpty()){
			throw new StudioNotFoundException(studioId);
		} else {
			return findStudio.get();
		}
	}

	public List<Studio> findAll(){
		return studioRepository.findAll();
	}

	public List<Studio> findByStudioTypeAndCityDistrict(StudioType studioType, String cityDistrict){
		return studioRepository.findByStudioTypeAndCityDistrict(studioType,cityDistrict);
	}

	@Transactional
	public Long deleteById(Long studioId){
		studioRepository.deleteById(studioId);
		return studioId;
	}

}
