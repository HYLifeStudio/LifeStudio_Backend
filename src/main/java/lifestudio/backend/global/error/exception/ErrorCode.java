package lifestudio.backend.global.error.exception;

public enum ErrorCode {

	// Common
	INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
	ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
	INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
	INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
	HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

	// User
	EMAIL_DUPLICATION(400, "U001", "Email is Duplication"),
	LOGIN_INPUT_INVALID(400, "U002", "Login input is invalid"),

	//Likes
	LIKES_ALREADY_EXIST(400,"L001","Likes is alredy exist between user and photo")


	;


	private final String code;
	private final String message;
	private int status;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public String getCode() {
		return code;
	}

	public int getStatus() {
		return status;
	}

}
