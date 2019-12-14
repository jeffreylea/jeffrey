package com.learn.jeffrey.utils;

import java.util.regex.Pattern;

/**
 * <p>IP操作相关方法</p>
 * @author lijianfei
 * @date 2019-12-14
 */
public class IpUtils {
	
	/**
	 * 根据IP生成最后六位
	 * @param ip
	 * @return
	 */
	public static String getIpFlag(String ip) {
		if (Pattern.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)", ip)) {
			String[] ips = ip.substring(ip.indexOf('.', ip.indexOf('.') + 1) + 1, ip.length()).split("\\.");
			return String.format("%03d", Integer.parseInt(ips[0])) + String.format("%03d", Integer.parseInt(ips[1]));
		} else {
			return "000001";
		}
	}
	
	/**
	 * 将long型转为IP
	 * @param longIp
	 * @return
	 */
	public static String longToIpV4(long longIp) {
		int octet3 = (int) ((longIp >> 24) % 256);
		int octet2 = (int) ((longIp >> 16) % 256);
		int octet1 = (int) ((longIp >> 8) % 256);
		int octet0 = (int) ((longIp) % 256);
		return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
	}

	/**
	 * 将IP地址转化为Long型
	 * @param ip
	 * @return
	 */
	public static long ipV4ToLong(String ip) {
		String[] octets = ip.split("\\.");
		return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16)
				+ (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
	}
}
