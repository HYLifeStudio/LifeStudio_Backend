package lifestudio.backend.domain.photo.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lifestudio.backend.domain.photo.service.FileService;
import org.springframework.web.bind.annotation.*;

import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.PhotoDto;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.service.StudioService;

import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PhotoApiController {

	private final PhotoService photoService;

	private final StudioService studioService;

	private final FileService fileService;

	private final ResponseService responseService;

	@PostMapping("/api/photos")
	public Response createPhoto(@RequestBody @Valid PhotoDto.createReq dto){
		Long id = photoService.createPhoto(dto);
		return responseService.getSingleResponse(new PhotoDto.Res(photoService.findById(id)));

	}

	@PostMapping("api/upload")
	public Response upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
		return responseService.getSingleResponse(new PhotoDto.uploadRes(fileService.upload(multipartFile, "static")));
	}




	@GetMapping("/api/photos/{id}")
	public Response getPhoto(@PathVariable final long id) {
		return responseService.getSingleResponse(new PhotoDto.Res(photoService.findById(id)));
	}

	@GetMapping("api/photos")
	public Response getPhotos(@RequestParam(required = false) String studioType){

		List<Photo> collect;

		if (studioType == null){
			 collect = photoService.findAll();
		}else {
			 collect = photoService.findByStudioType(studioType);
		}

		List<PhotoDto.Res> ResCollect = collect.stream()
			.map(p -> new PhotoDto.Res(p))
			.collect(Collectors.toList());

		return responseService.getListResponse(ResCollect);
	}

	@DeleteMapping("/api/photos/{id}")
	public Response deletePhoto(@PathVariable final long id) {
		Photo photo = photoService.findById(id);
		photoService.deleteById(id);
		return responseService.getSuccessResponse();
	}
}
