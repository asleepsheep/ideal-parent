package com.ideal.cloud.core.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseReqData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sign;

}
