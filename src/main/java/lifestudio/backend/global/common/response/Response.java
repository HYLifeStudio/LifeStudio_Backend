package lifestudio.backend.global.common.response;

import lombok.Data;

@Data
public class Response {

	private boolean success;

	private int code;

	private String message;

}
