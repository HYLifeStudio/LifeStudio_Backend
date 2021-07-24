package lifestudio.backend.domain.studio.dto;

import lifestudio.backend.domain.studio.domain.Tag;
import lombok.Data;

public class TagDto {

	@Data
	public static class Res{

		private String color;

		private String background;

		private String itemExist;

		public Res(Tag tag){
			this.color = tag.colorRes();
			this.background = tag.backgroundRes();
			this.itemExist = tag.itemExistRes();
		}
	}


}
