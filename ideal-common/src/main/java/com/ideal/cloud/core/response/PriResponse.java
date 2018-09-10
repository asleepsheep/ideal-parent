package com.ideal.cloud.core.response;

import com.ideal.cloud.core.code.BaseCode;
import com.ideal.cloud.core.code.CodeEnum;
import com.ideal.cloud.core.utils.DateHelper;
import com.ideal.cloud.core.utils.IpHelper;
import com.ideal.cloud.core.utils.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**内部请求反馈*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriResponse<T> implements Serializable,Response<T> {
	private static final long serialVersionUID = 1L;

	private String code = BaseCode.OK.getCode();

	private String message = BaseCode.OK.getMessage();

	private String serverIP = IpHelper.getHostIp();
	
	private String serverPort;

	private String serverName;

	private String respTime = DateHelper.getNowString(DateHelper.FormatUnit.yyyyMMddHHmmss, false);

	private T data;

	public PriResponse(T data) {
		super();
		this.data = data;
	}

	public PriResponse(CodeEnum codeEnum) {
		super();
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMessage();
	}

	public PriResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public PriResponse(CodeEnum codeEnum, String message) {
		super();
		this.code = codeEnum.getCode();
		if (!StringHelper.isEmpty(message))
			this.message = message;
		else
			this.message = codeEnum.getMessage();
	}
	
	public static <T> PriResponse<T> ok() {
		return new PriResponse<T>();
	}

	public static <T> PriResponse<T> error(CodeEnum codeEnum) {
		return new PriResponse<T>(codeEnum);
	}

	public static <T> PriResponse<T> error(CodeEnum codeEnum, String message) {
		return new PriResponse<T>(codeEnum, message);
	}

	public static <T> PriResponse<T> error(String message) {
		return new PriResponse<T>(BaseCode.FAIL, message);
	}
	
	public static <T> PriResponse<T> error(String code,String message) {
		return new PriResponse<T>(code, message);
	}

	public static <T> PriResponse<T> success(T t) {
		return new PriResponse<T>(t);
	}
}
