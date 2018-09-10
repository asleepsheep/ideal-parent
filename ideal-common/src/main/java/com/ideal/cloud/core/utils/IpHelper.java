package com.ideal.cloud.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpHelper {
	public static String getHostIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
