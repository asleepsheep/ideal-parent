package com.ideal.cloud.core.response;

public interface Response<T> {
	String getCode();
	
	String getMessage();

	T getData();
}
