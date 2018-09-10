package com.ideal.cloud.core.code;

public enum TimerCode implements CodeEnum {
	TIMER_ERROR("T10000", "定时器执行异常"),

	;
	private String code;
	private String message;

	TimerCode(String status, String message) {
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
