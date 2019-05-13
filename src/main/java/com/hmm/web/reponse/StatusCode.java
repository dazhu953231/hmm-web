package com.hmm.web.reponse;

/**
 * 异常状态码
 */
public enum StatusCode {
	//待完善，需要和前端协商
	SUCCESS(0, "OK"),
	//系统错误，所有为止错误均返回此状态码
	SYSTEM_ERROR(10500, "System error"),
	//request not found
	NOT_FOUND(10404, "Not found");

	//错误代码
	private int code;
	//简短描述
	private String msg;

	private StatusCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}
	public String getMsg() {
		return this.msg;
	}

}
