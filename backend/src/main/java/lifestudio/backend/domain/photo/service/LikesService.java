package lifestudio.backend.domain.photo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.repository.LikesRepository;
import lifestudio.backend.domain.photo.repository.PhotoRepository;
import lifestudio.backend.domain.studio.domain.Background;
import lifestudio.backend.domain.studio.domain.Color;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepository likeRepository;

	private final UserRepository userRepository;

	private final PhotoRepository photoRepository;

	@Transactional
	public Long createLikes(Likes like){
		likeRepository.save(like);
		return like.getId();
	}

	@Transactional
	public Long updateLikes(Long userId, Long photoID){
		Optional<Likes> like = likeRepository.findByPhotoIdAndUserId(userId, photoID);
		if (like.isEmpty()){
			Likes createlikes = Likes.builder()
				.user(userRepository.findById(userId).get())
				.photo(photoRepository.findById(photoID).get())
				.isLiked(true)
				.build();
			return createLikes(createlikes);
		} else {
			like.get().changeLike();
			return like.get().getId();
		}
	}

	public Likes findById(Long likeId){
		return likeRepository.findById(likeId).get();
	}

	public List<Likes> findAll(){
		return likeRepository.findAll();
	}

	public Likes findByPhotoIdAndUserId(Long userId, Long photoId){
		Optional<Likes> like = likeRepository.findByPhotoIdAndUserId(userId, photoId);
		if (like.isEmpty()){
			return null;
		} else {
			return like.get();
		}
	}

	@Transactional
	public Long deleteById(Long likeId){
		likeRepository.deleteById(likeId);
		return likeId;
	}


}
