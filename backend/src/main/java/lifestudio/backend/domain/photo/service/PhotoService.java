package lifestudio.backend.domain.photo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lifestudio.backend.domain.photo.dto.PhotoDto;
import lifestudio.backend.domain.studio.domain.Option;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.exception.StudioNotFoundException;
import lifestudio.backend.domain.studio.repository.StudioRepository;
import lifestudio.backend.domain.studio.service.StudioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.exception.PhotoNotFoundException;
import lifestudio.backend.domain.photo.repository.LikesRepository;
import lifestudio.backend.domain.photo.repository.PhotoRepository;
import lifestudio.backend.domain.studio.domain.StudioType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

	private final PhotoRepository photoRepository;

	private  final LikesRepository likesRepository;

	private final StudioService studioService;

	@Transactional
	public Long createPhoto(PhotoDto.createReq dto){
		Studio studio = studioService.findById(dto.getStudioId());
		Photo photo = Photo.builder()
				.url(dto.getUrl())
				.title(dto.getTitle())
				.createdAt(LocalDateTime.now())
				.build();
		if (dto.getType().equals("advertisement")){
			photo.setStudio(studio);
			photoRepository.save(photo);
		} else if (dto.getType().equals("businessRegistration")){
			photoRepository.save(photo);
			studio.setBusinessRegistrationPhoto(photo);
		} else if (dto.getType().equals("representative")){
			photo.setStudio(studio);
			photoRepository.save(photo);
			studio.setRepresentativePhoto(photo);
		}
		return photo.getId();
	}

	public Photo findById(Long photoId){
		Optional<Photo> findPhoto = photoRepository.findById(photoId);
		if (findPhoto.isEmpty()){
			throw new PhotoNotFoundException(photoId);
		} else {
			return findPhoto.get();
		}
	}

	public List<Photo> findAll(){
		return photoRepository.findAll();
	}

	public List<Photo> findByStudioId(Long studioId){
		return photoRepository.findByStudioId(studioId);
	}

	public List<Photo> findByStudioType(String studioType){
		return photoRepository
			.findByStudioType(StudioType.valueOf(studioType));
	}

	@Transactional
	public Long deleteById(Long photoId){
		photoRepository.deleteById(photoId);
		return photoId;
	}

	public Boolean LikeCheck(Long photoId, Long userId){
		List<Likes> like = likesRepository.findByUserIdAndPhotoId(userId,photoId);
		if (like.isEmpty()){
			return false;
		} else {
			return like.get(0).getIsLiked();
		}
	}
}
