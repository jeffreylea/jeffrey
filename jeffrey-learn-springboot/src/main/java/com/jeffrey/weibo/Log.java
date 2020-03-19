package com.jeffrey.weibo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Log {
	
    public static void logDebug(String message) {
			log.debug(message);
	}

	public static void logInfo(String message) {
			log.info(message);
	}


}
