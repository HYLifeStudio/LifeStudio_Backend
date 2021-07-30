package lifestudio.backend.global.common.result;

import lombok.Data;

@Data
public class SingleResult<T> extends CommonResult {
	private T data;
}
