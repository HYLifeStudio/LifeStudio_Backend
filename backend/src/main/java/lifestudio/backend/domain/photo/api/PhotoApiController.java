package lifestudio.backend.domain.photo.api;

import java.time.LocalDateTime;
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

import lifestudio.backend.domain.photo.service.LikesService;
import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.PhotoDto;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.service.StudioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PhotoApiController {

	private final PhotoService photoService;

	private final StudioService studioService;

	private final LikesService likesService;

	@PostMapping("/api/photos")
	public PhotoDto.Res createPhoto(@RequestBody @Valid PhotoDto.createReq dto){

		Studio studio = studioService.findById(dto.getStudioId());

		Photo photo = Photo.builder()
			.studio(studio)
			.url(dto.getUrl())
			.title(dto.getTitle())
			.thumbnailUrl(dto.getThumbnailUrl())
			.createdAt(LocalDateTime.now())
			.build();

		Long id = photoService.createPhoto(photo);
		return new PhotoDto.Res(photoService.findById(id));

	}

	@GetMapping("/api/photos/{id}")
	public PhotoDto.Res getPhoto(@PathVariable final long id) {
		return new PhotoDto.Res(photoService.findById(id));
	}

	@GetMapping("api/photos")
	public List<PhotoDto.Res> getPhotos(@RequestParam(required = false) String studioType){

		List<Photo> collect;

		if (studioType == null){
			 collect = photoService.findAll();
		}else {
			 collect = photoService.findByStudioType(studioType);
		}

		List<PhotoDto.Res> ResCollect = collect.stream()
			.map(p -> new PhotoDto.Res(p))
			.collect(Collectors.toList());

		return ResCollect;
	}

	@DeleteMapping("/api/photos/{id}")
	public PhotoDto.Res deletePhoto(@PathVariable final long id) {
		Photo photo = photoService.findById(id);
		photoService.deleteById(id);
		return new PhotoDto.Res(photo);
	}
}
