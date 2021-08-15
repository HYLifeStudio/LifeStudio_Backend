package lifestudio.backend.global.common.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListResponse<T> extends Response {
	private List<T> list;
}
