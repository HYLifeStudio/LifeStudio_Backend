package lifestudio.backend.domain.photo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.photo.domain.Likes;
import lifestudio.backend.domain.photo.exception.LikesAlreadyExistException;
import lifestudio.backend.domain.photo.exception.LikesNotFoundException;
import lifestudio.backend.domain.photo.repository.LikesRepository;
import lifestudio.backend.domain.photo.repository.PhotoRepository;
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
		Long userId = like.getUser().getId();
		Long photoId = like.getPhoto().getId();
		List<Likes> findLikes = likeRepository.findByUserIdAndPhotoId(userId, photoId);
		if (findLikes.isEmpty()) {
			likeRepository.save(like);
			return like.getId();
		} else {
			throw new LikesAlreadyExistException("likes");
		}


	}

	@Transactional
	public Long updateLikes(Long id){
		Optional<Likes> like = likeRepository.findById(id);
		if (like.isEmpty()){
			throw new LikesNotFoundException(id);
		} else {
			like.get().changeLike();
			return like.get().getId();
		}
	}

	public Likes findById(Long likeId){
		Optional<Likes> like = likeRepository.findById(likeId);
		if (like.isEmpty()){
			throw new LikesNotFoundException(likeId);
		} else {
			return like.get();
		}
	}

	public List<Likes> findAll(){
		return likeRepository.findAll();
	}

	public List<Likes> findByUserIdAndPhotoId(Long userId, Long photoId){
		List<Likes> likes = likeRepository.findByUserIdAndPhotoId(userId, photoId);
		if (likes.isEmpty()){
			throw new LikesNotFoundException(userId, photoId);
		} else {
			return likes;
		}
	}

	@Transactional
	public Long deleteById(Long likeId){
		likeRepository.deleteById(likeId);
		return likeId;
	}


}
