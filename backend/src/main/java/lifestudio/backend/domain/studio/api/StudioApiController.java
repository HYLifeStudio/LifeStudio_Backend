package lifestudio.backend.domain.studio.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.studio.domain.Address;
import lifestudio.backend.domain.studio.domain.Background;
import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.Option;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.studio.domain.Tag;
import lifestudio.backend.domain.studio.dto.StudioDto;
import lifestudio.backend.domain.studio.service.StudioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudioApiController {

	private final StudioService studioService;

	@PostMapping("/api/studios")
	public StudioDto.Res createStudio(@RequestBody @Valid StudioDto.createReq dto){

		Address address = Address.builder()
			.cityDistrict(dto.getCityDistrict())
			.streetAddress(dto.getStreetAddress())
			.zipCode(dto.getZipCode())
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
			.background(Background.valueOf(dto.getBackground()))
			.itemExist(dto.getItemExist())
			.build();

		Studio studio = Studio.builder()
			.studioName(dto.getStudioName())
			.studioType(StudioType.valueOf(dto.getStudioType()))
			.bio(dto.getBio())
			.address(address)
			.option(option)
			.tag(tag)
			.build();

		Long id = studioService.createStudio(studio);
		return new StudioDto.Res(studioService.findById(id));
	}

	@GetMapping("/api/studios/{id}")
	public StudioDto.Res getStudio(@PathVariable final long id) {
		return new StudioDto.Res(studioService.findById(id));
	}

	@GetMapping("/api/studios")
	public List<StudioDto.Res> getStudios(@RequestParam(required = false) String type,
		@RequestParam(required = false) String cityDistrict) {

		List<Studio> findStudios;

		if (type == null && cityDistrict == null ){
			findStudios = studioService.findAll();
		} else {
			findStudios = studioService.findByStudioTypeAndCityDistrict(StudioType.valueOf(type),cityDistrict );
		}

		List<StudioDto.Res> collect = findStudios.stream()
			.map(s -> new StudioDto.Res(s))
			.collect(Collectors.toList());
		return collect;
	}

	@DeleteMapping("/api/studios/{id}")
	public StudioDto.Res deleteStudio(@PathVariable final long id) {
		Studio studio = studioService.findById(id);
		studioService.deleteById(id);
		return new StudioDto.Res(studio);
	}

}