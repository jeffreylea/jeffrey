package com.learn.jeffrey.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年2月10日
 */
@Slf4j
public class JsonUtil {

	/**
	 * 读取json文件
	 * 
	 * @param fileName json文件名
	 * @return 返回json字符串
	 */
	public static String readJsonFile(File jsonFile) {
		String jsonStr = "";
		log.info("————开始读取{}文件————", jsonFile.getPath());
		try (FileReader fileReader = new FileReader(jsonFile);
				Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");) {
			int ch = 0;
			StringBuilder sb = new StringBuilder();
			while ((ch = reader.read()) != -1) {
				sb.append((char) ch);
			}
			jsonStr = sb.toString();
			log.info("————读取{}文件结束!————", jsonFile.getPath());
			return jsonStr;
		} catch (Exception e) {
			log.info("————读取{}文件出现异常，读取失败!————", jsonFile.getPath());
			log.error(null, e);
			return null;
		}
	}
	
	
	private static Validator validator = Validation.buildDefaultValidatorFactory()
			.getValidator();

	public static <T> Map<String,String> validate(T obj){
		Map<String, String> validateResult = new HashMap<>();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if(set != null && !set.isEmpty() ){
			String property = null;
			String errorMsg = null;
			for(ConstraintViolation<T> cv : set){
				property = cv.getPropertyPath().toString();
				errorMsg = cv.getMessage();
				validateResult.put(property, errorMsg);
			}
		}
		return validateResult;
	}
}
