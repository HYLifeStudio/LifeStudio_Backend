package lifestudio.backend.domain.studio.dto;

import lifestudio.backend.domain.studio.domain.Tag;
import lombok.Data;

public class TagDto {

	@Data
	public static class Res{

		private String color;

		private Boolean background;

		private Boolean itemExist;

		public Res(Tag tag){
			this.color = tag.getColor().toString();
			this.background = tag.getBackground();
			this.itemExist = tag.getItemExist();
		}
	}


}
