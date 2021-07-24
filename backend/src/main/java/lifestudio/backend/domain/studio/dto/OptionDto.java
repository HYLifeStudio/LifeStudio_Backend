package lifestudio.backend.domain.studio.dto;

import java.util.ArrayList;
import java.util.List;

import lifestudio.backend.domain.studio.domain.Option;
import lombok.Data;

public class OptionDto {

	@Data
	public static class Res {

		private List<String> summaryOptions = new ArrayList<String>();

		private List<String> detailOptions = new ArrayList<String >();

		public Res(Option option){
			this.summaryOptions.add(option.itemSummary());
			this.summaryOptions.add(option.timeSummary());
			this.summaryOptions.add(option.originalProvideSummary());
			this.summaryOptions.add(option.printPhotoSummary());
			this.detailOptions.add(option.timeDetail());
			this.detailOptions.add(option.provideDetail());
			this.detailOptions.add(option.itemDetail());
		}

	}


}
