package lifestudio.backend.global.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleResponse<T> extends Response {
	private T data;
}
