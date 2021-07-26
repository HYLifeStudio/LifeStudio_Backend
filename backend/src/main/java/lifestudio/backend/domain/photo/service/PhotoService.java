package lifestudio.backend.domain.photo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.domain.Photo;
import lifestudio.backend.domain.photo.repository.LikesRepository;
import lifestudio.backend.domain.photo.repository.PhotoRepository;
import lifestudio.backend.domain.studio.domain.Background;
import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.StudioType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

	private final PhotoRepository photoRepository;

	private  final LikesRepository likesRepository;

	@Transactional
	public Long createPhoto(Photo photo){
		photoRepository.save(photo);
		return photo.getId();
	}

	public Photo findById(Long photoId){
		return photoRepository.findById(photoId).get();
	}

	public List<Photo> findAll(){
		return photoRepository.findAll();
	}

	public List<Photo> findByStudioId(Long studioId){
		return photoRepository.findByStudioId(studioId);
	}

	public List<Photo> findByTagsAndStudioType(String studioType, String color, String background, Boolean itemExist){
		return photoRepository
			.findByTagsAndStudioType(StudioType.valueOf(studioType),Color.valueOf(color),Background.valueOf(background),itemExist);
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
