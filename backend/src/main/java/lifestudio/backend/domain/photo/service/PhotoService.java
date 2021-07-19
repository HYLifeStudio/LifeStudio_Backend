package lifestudio.backend.domain.photo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Photo;
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

	public List<Photo> findByTagsAndStudioType(StudioType studioType, Color color, Background background, Boolean itemExist){
		return photoRepository.findByTagsAndStudioType(studioType,color,background,itemExist);
	}

	@Transactional
	public Long deleteById(Long photoId){
		photoRepository.deleteById(photoId);
		return photoId;
	}
}
