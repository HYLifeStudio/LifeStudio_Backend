package lifestudio.backend.domain.studio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.domain.StudioType;
import lifestudio.backend.domain.studio.exception.StudioNotFoundException;
import lifestudio.backend.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudioService {

	private final StudioRepository studioRepository;

	@Transactional
	public Long createStudio(Studio studio){
		studioRepository.save(studio);
		return studio.getId();
	}

	public Studio findById(Long studioId){
		Optional<Studio> findStudio = studioRepository.findById(studioId);
		if (findStudio.isEmpty()){
			throw new StudioNotFoundException(studioId);
		} else {
			return findStudio.get();
		}
	}

	public List<Studio> findAll(){
		return studioRepository.findAll();
	}

	public List<Studio> findByStudioTypeAndCityDistrict(StudioType studioType, String cityDistrict){
		return studioRepository.findByStudioTypeAndCityDistrict(studioType,cityDistrict);
	}

	@Transactional
	public Long deleteById(Long studioId){
		studioRepository.deleteById(studioId);
		return studioId;
	}

}
