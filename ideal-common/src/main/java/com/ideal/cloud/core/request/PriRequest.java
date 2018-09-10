package com.ideal.cloud.core.request;

import com.ideal.cloud.core.utils.DateHelper;
import com.ideal.cloud.core.utils.IpHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**内部系统请求*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriRequest<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String serverName;

	private String serverIP;
	
	private String serverPort;

	private String reqTime;

	private T data;

	public PriRequest(String serverName, T data) {
		super();
		this.serverName = serverName;
		this.serverIP = IpHelper.getHostIp();
		this.reqTime = DateHelper.getNowString(DateHelper.FormatUnit.yyyyMMddHHmmss, false);
		this.data = data;
	}

}
