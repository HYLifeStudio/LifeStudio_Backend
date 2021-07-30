package lifestudio.backend.global.common.result;

import java.util.List;

import lombok.Data;

@Data
public class ListResult<T> extends CommonResult {
	private List<T> list;
}
