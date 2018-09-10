package com.ideal.cloud.core.code;

public enum KlockCode implements CodeEnum {
	TIME_OUT_ERROR("K10000", "超时"),

	;
	private String code;
	private String message;

	KlockCode(String status, String message) {
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
