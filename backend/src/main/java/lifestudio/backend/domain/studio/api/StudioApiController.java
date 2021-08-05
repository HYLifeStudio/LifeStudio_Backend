package lifestudio.backend.domain.studio.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.studio.domain.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.studio.dto.StudioDto;
import lifestudio.backend.domain.studio.service.StudioService;

import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudioApiController {

	private final StudioService studioService;
	private final ResponseService responseService;
	private final PhotoService photoService;

	@PostMapping("/api/studios")
	public Response createStudio(@RequestBody @Valid StudioDto.createReq dto){
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

		Studio studio = Studio.builder()
				.studioName(dto.getStudioName())
				.address(address)
				.studioType(StudioType.valueOf(dto.getStudioType()))
				.tag(tag)
				.openingTime(openingTime)
				.option(option)
				.bio(dto.getBio())
				.option(option)
				.shopNumber(dto.getShopNumber())
				.managerName(dto.getManagerName())
				.registrationNumber(dto.getRegistrationNumber())
				.build();

		Long id = studioService.createStudio(studio);
		return responseService.getSingleResponse(new StudioDto.Res(studioService.findById(id)));
	}

	@GetMapping("/api/studios/{id}")
	public Response getStudio(@PathVariable final long id) {
		return responseService.getSingleResponse(new StudioDto.Res(studioService.findById(id)));
	}

	@GetMapping("/api/studios")
	public Response getStudios(@RequestParam(required = false) String type,
		@RequestParam(required = false) String cityDistrict) {

		List<Studio> findStudios;

		if (type == null && cityDistrict == null ){
			findStudios = studioService.findAll();
		} else {
			findStudios = studioService.findByStudioTypeAndCityDistrict(StudioType.valueOf(type),cityDistrict );
		}

		List<StudioDto.summaryRes> collect = findStudios.stream()
			.map(s -> new StudioDto.summaryRes(s))
			.collect(Collectors.toList());
		return responseService.getListResponse(collect);
	}

	@DeleteMapping("/api/studios/{id}")
	public Response deleteStudio(@PathVariable final long id) {
		Studio studio = studioService.findById(id);
		studioService.deleteById(id);
		return responseService.getSuccessResponse();
	}

}
