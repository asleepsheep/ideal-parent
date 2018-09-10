package com.ideal.cloud.core.code;

public enum BaseCode implements CodeEnum {
	OK("10000", "成功"), 
	
	REQUEST_METHOD_NOT_SUPPORTED("11111", "请求方式不支持"),

	REQUEST_BODY_IS_MISSING("222222", "请求body不存在"),

	FAIL("99999", "未知错误"),

	;
	private String code;
	private String message;

	BaseCode(String status, String message) {
		this.code = status;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return this.message;
	}
}
