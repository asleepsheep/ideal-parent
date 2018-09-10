package com.ideal.cloud.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**外部访问请求*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetRequest<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 请求token */
	private String token;
	
	//设备类型 web/mobile
	private String diviceType;

	//请求Ip
	private String requestIp;
	
	private String reqTime;

	private T data;
}
