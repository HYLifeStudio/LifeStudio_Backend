package lifestudio.backend.domain.photo.api;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.dto.LikesDto;
import lifestudio.backend.domain.photo.service.LikesService;
import lifestudio.backend.domain.photo.service.PhotoService;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikesApiController {

	private final LikesService likesService;

	private final UserService userService;

	private final PhotoService photoService;

	@PostMapping("/api/likes")
	public LikesDto.Res createLike(@RequestBody @Valid LikesDto.createReq dto){

		Photo photo = photoService.findById(dto.getPhotoId());
		User user = userService.findById(dto.getPhotoId());

		Likes like = new Likes();
		like.setUser(user);
		like.setPhoto(photo);
		like.setIsLiked(dto.getIsLiked());

		Long id = likesService.createLikes(like);
		return new LikesDto.Res(likesService.findById(id));

	}

	@GetMapping("/api/likes/{id}")
	public LikesDto.Res getLike(@PathVariable final long id) {
		return new LikesDto.Res(likesService.findById(id));
	}

	@GetMapping("/api/likes")
	public List<LikesDto.Res> getLikes() {

		List<Likes> findLikes = likesService.findAll();

		List<LikesDto.Res> collect = findLikes.stream()
			.map(l -> new LikesDto.Res(l))
			.collect(Collectors.toList());
		return collect;
	}

	@DeleteMapping("/api/likes/{id}")
	public LikesDto.Res deleteLike(@PathVariable final long id) {
		Likes like = likesService.findById(id);
		likesService.deleteById(id);
		return new LikesDto.Res(like);
	}
}
