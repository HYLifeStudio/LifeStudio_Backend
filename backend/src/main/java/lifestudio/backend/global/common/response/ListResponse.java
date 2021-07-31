package lifestudio.backend.global.common.response;

import java.util.List;

import lombok.Data;

@Data
public class ListResponse<T> extends Response {
	private List<T> list;
}
