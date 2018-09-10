package com.ideal.cloud.core.exception;

import com.ideal.cloud.core.code.BaseCode;
import com.ideal.cloud.core.code.CodeEnum;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String code;

    private String message;

    public BaseException(String message) {
		super(message);
		this.code = BaseCode.FAIL.getCode();
		this.message = message;
	}
	
	public BaseException(String message, Throwable e) {
		super(message, e);
		this.code = BaseCode.FAIL.getCode();
		this.message = message;
	}
	
	public BaseException(String message, String code) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public BaseException(String message, String code, Throwable e) {
		super(message, e);
		this.message = message;
		this.code = code;
	}
	
	public BaseException(CodeEnum codeEnum, Throwable e) {
		super(codeEnum.getMessage(), e);
		this.message = codeEnum.getMessage();
		this.code = codeEnum.getCode();
	}
	
	public BaseException(CodeEnum codeEnum, String error) {
		super(error);
		this.message = codeEnum.getMessage();
		this.code = codeEnum.getCode();
	}
	
	public BaseException(CodeEnum codeEnum) {
		super(codeEnum.getMessage());
		this.message = codeEnum.getMessage();
		this.code = codeEnum.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
