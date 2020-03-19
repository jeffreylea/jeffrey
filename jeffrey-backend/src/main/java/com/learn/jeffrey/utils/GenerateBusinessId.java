package com.learn.jeffrey.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lijianfei
 *         <p>
 *         需要完善
 *         </p>
 *         2020年3月19日
 */
@Slf4j
public class GenerateBusinessId {

	@SuppressWarnings("rawtypes")
	@Autowired
	private static RedisTemplate redisTemplate;

	/**
	 * 根据业务代码生成批次号
	 *
	 * @param businessCode 业务代码
	 * @param args         可变参数，其规则： 第1个参数是随机数长度（int），第2个参数是日期格式（DateFormatEnum），
	 *                     第3个参数是hashKey（String，用作业务数据隔离）
	 * @return 批次号
	 */
	@SuppressWarnings("unchecked")
	public static String getBatchNo(BusinessCodeEnum businessCode, Object... args) {
		int randomLength = 8; // 随机数长度
		DateFormatEnum dateFormatEnum = DateFormatEnum.YMDHMS_NO_SEPERATED; // 日期格式
		String hashKey = businessCode.getDesc(); // hashKey，业务数据隔离
		log.info("开始生成{}", hashKey);

		if (args != null && args.length > 0) {
			if (args[0] instanceof Integer) {
				randomLength = (int) args[0];
				log.info("- 随机数长度: {}", randomLength);
			}
			if (args.length > 1 && args[1] instanceof DateFormatEnum) {
				dateFormatEnum = (DateFormatEnum) args[1];
				log.info("- 日期格式: {}", dateFormatEnum.getFormat());
			}
			if (args.length > 2 && args[2] instanceof String) {
				hashKey = (String) args[2];
				log.info("- hashKey: {}", hashKey);
			}
		}
		String prefix = businessCode.getCode() + DateFormatUtils.formatDBYmdHms(dateFormatEnum) ;
		String format = "%0" + randomLength + "d";
		String batchNo = prefix
				+ String.format(format, 2);//redisTemplate.opsForHash().increment(businessCode.getCode(), hashKey, 1L));
		log.info("生成的{}: {}", businessCode.getDesc(), batchNo);
		return batchNo;
	}
	
	public static void main(String[] args) {
		getBatchNo(BusinessCodeEnum.BUSINESSCODE);
	}
}
