package lifestudio.backend.global.common.response;

import lombok.Data;

@Data
public class SingleResponse<T> extends Response {
	private T data;
}
