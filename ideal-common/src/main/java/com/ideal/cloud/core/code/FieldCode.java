package com.ideal.cloud.core.code;

public enum FieldCode implements CodeEnum {
	FIELD_VALID_ERROR("F10000", "字段验证失败"), 
	
	FIELD_VALID_MESSAGE("F20000", "字段验证失败"),
	;
	private String code;
	private String message;

	FieldCode(String status, String message) {
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
