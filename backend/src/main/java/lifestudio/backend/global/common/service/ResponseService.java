package lifestudio.backend.global.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lifestudio.backend.global.error.ErrorResponse;
import lifestudio.backend.global.common.response.ListResponse;
import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.response.SingleResponse;

@Service
public class ResponseService {

	public enum CommonResponse{
		SUCCESS(0, "성공하였습니다"),
		FAIL(-1, "실패했습니다");

		int code;
		String message;

		CommonResponse(int code, String messageg) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return message;
		}
	}

	public <T> SingleResponse<T> getSingleResponse(T data){
		SingleResponse<T> result = new SingleResponse<>();
		result.setData(data);
		setSuccessResponse(result);
		return result;
	}

	public <T> ListResponse<T> getListResponse(List<T> list){
		ListResponse<T> result = new ListResponse<>();
		result.setList(list);
		setSuccessResponse(result);
		return result;
	}

	public Response getSuccessResponse(){
		Response result = new Response();
		setSuccessResponse(result);
		return result;
	}

	private void setSuccessResponse(Response result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMessage(CommonResponse.SUCCESS.getMsg());
	}
}
