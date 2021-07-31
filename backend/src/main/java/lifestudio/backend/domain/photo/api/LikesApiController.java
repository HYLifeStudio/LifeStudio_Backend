package lifestudio.backend.domain.photo.api;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.dto.LikesDto;
import lifestudio.backend.domain.photo.service.LikesService;
import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.user.service.UserService;
import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikesApiController {

	private final LikesService likesService;

	private final UserService userService;

	private final PhotoService photoService;

	private final ResponseService responseService;

	@PostMapping("/api/likes")
	public Response createLike(@RequestBody @Valid LikesDto.createReq dto){
		Likes like = Likes.builder()
			.user(userService.findById(dto.getUserId()))
			.photo(photoService.findById(dto.getPhotoId()))
			.isLiked(true)
			.build();
		Long likeId = likesService.createLikes(like);
		return responseService.getSingleResponse(new LikesDto.Res(likesService.findById(likeId)));
	}

	@PutMapping("api/likes/{id}")
	public Response updateLike(@PathVariable final long id) {
		Long updateId = likesService.updateLikes(id);
		return responseService.getSingleResponse(new LikesDto.Res(likesService.findById(updateId)));
	}

	@GetMapping("/api/likes/{id}")
	public Response getLike(@PathVariable final long id) {
		return responseService.getSingleResponse(new LikesDto.Res(likesService.findById(id)));
	}

	@GetMapping("/api/likes")
	public Response getLikes(@RequestParam(required = false) Long userId,
		@RequestParam(required = false) Long photoId) {
		List<Likes> findLikes;
		if (userId == null && photoId == null) {
			findLikes = likesService.findAll();
		} else {
			findLikes = likesService.findByUserIdAndPhotoId(userId,photoId);
		}

		List<LikesDto.Res> collect = findLikes.stream()
			.map(l -> new LikesDto.Res(l))
			.collect(Collectors.toList());
		return responseService.getListResponse(collect);
	}

	@DeleteMapping("/api/likes/{id}")
	public Response deleteLike(@PathVariable final long id) {
		Likes like = likesService.findById(id);
		likesService.deleteById(id);
		return responseService.getSuccessResponse();
	}
}
