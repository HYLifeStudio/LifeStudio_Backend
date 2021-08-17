package lifestudio.backend.domain.studio.dto;

import lifestudio.backend.domain.studio.domain.Address;
import lombok.Data;

public class AddressDto {

	@Data
	public static class Res{

		private String detailAddress;

		private String summaryAddress;

		public Res(Address address){
			this.detailAddress = address.detailAddress();
			this.summaryAddress = address.summaryAddress();
		}


	}
}
