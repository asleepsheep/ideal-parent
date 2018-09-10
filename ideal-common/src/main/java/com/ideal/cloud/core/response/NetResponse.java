package com.ideal.cloud.core.response;

import com.ideal.cloud.core.code.BaseCode;
import com.ideal.cloud.core.code.CodeEnum;
import com.ideal.cloud.core.utils.DateHelper;
import com.ideal.cloud.core.utils.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 外部请求反馈 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetResponse<T> implements Serializable, Response<T> {
	private static final long serialVersionUID = 1L;

	private String code = BaseCode.OK.getCode();

	private String message = BaseCode.OK.getMessage();

	private String respTime = DateHelper.getNowString(DateHelper.FormatUnit.yyyyMMddHHmmss, false);

	private T data;

	public NetResponse(T data) {
		super();
		this.data = data;
	}

	public NetResponse(CodeEnum codeEnum) {
		super();
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMessage();
	}

	public NetResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public NetResponse(CodeEnum codeEnum, String message) {
		super();
		this.code = codeEnum.getCode();
		if (!StringHelper.isEmpty(message))
			this.message = message;
		else
			this.message = codeEnum.getMessage();
	}

	public static <T> NetResponse<T> ok() {
		return new NetResponse<T>();
	}

	public static <T> NetResponse<T> error(CodeEnum codeEnum) {
		return new NetResponse<T>(codeEnum);
	}

	public static <T> NetResponse<T> error(CodeEnum codeEnum, String message) {
		return new NetResponse<T>(codeEnum, message);
	}

	public static <T> NetResponse<T> error(String message) {
		return new NetResponse<T>(BaseCode.FAIL, message);
	}
	
	public static <T> NetResponse<T> error(String code,String message) {
		return new NetResponse<T>(code, message);
	}

	public static <T> NetResponse<T> success(T t) {
		return new NetResponse<T>(t);
	}
}
