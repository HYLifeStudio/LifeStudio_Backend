package lifestudio.backend.global.common.result;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

	private boolean success;

	private int code;

	private String msg;

}
