package lifestudio.backend.domain.studio.dto;

import java.util.ArrayList;
import java.util.List;

import lifestudio.backend.domain.studio.domain.Option;
import lombok.Data;

public class OptionDto {

	@Data
	public static class Res {

		private Integer shootingTime;

		private Integer retouchingTime;

		private Boolean originalProvide;

		private Integer printPhoto;

		private String item;

		public Res(Option option){
			this.shootingTime = option.getShootingTime();
			this.retouchingTime = option.getRetouchingTime();
			this.originalProvide = option.getOriginalProvide();
			this.printPhoto = option.getPrintPhoto();
			this.item = option.getItem();
		}

	}


}
