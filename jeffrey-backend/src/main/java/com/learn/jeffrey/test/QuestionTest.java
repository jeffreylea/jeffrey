package com.learn.jeffrey.test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年2月11日
 */
@Slf4j
public class QuestionTest {

	public static void main(String[] args) {
		// 读取Question.json params
		String jsonStrParam = JsonUtil.readJsonFile(new File("F:\\template" + "\\params.json"));
		Map<String, Object> values = JSON.parseObject(jsonStrParam, Map.class);
		String jsonStr = JsonUtil.readJsonFile(new File("F:\\template" + "\\question.json"));
		jsonStr = jsonStr.replaceAll("\r|\n|\t", "");
		System.out.println(jsonStr);

		// Map<String, String> validateResult = new HashMap<>();
		JSONArray array = JSONArray.parseArray(jsonStr);

		Set<String> requiredProperties = new HashSet<>();
		Set<String> required = validateJSONArrayFromMapValues(array, values, requiredProperties);
		required.remove(null);
		System.out.println(required);
	}

	public static Set<String> validateJSONArrayFromMapValues(JSONArray array, Map<String, Object> values,
			Set<String> requiredProperties) {
		for (Object item : array) {
			JSONObject i = (JSONObject) item;
			String requiredPropertie = validateJSONObjectFromMapValues(i, values);
			requiredProperties.add(requiredPropertie);
			if (i.containsKey("subquestions")) {
				JSONArray arr = JSON.parseArray(JSONObject.toJSONString(i.get("subquestions")));
				validateJSONArrayFromMapValues(arr, values, requiredProperties);
			}
		}
		return requiredProperties;
	}

	public static String validateJSONObjectFromMapValues(JSONObject object, Map<String, Object> values) {

		Set<String> validateResult = new HashSet<>();

		boolean required = (Boolean) object.get("required");
		if (required) {
			String variable = (String) object.get("variable");
			if (StringUtils.isNotEmpty(variable)) {
				// 获取对象属性
				String[] objPropertyArray = variable.split("\\.");
				Map q = null;
				if (objPropertyArray.length > 0) {
					q = (Map) values.get(objPropertyArray[0]);
					for (int j = 1; j < objPropertyArray.length - 1; j++) {
						q = (Map) q.get(objPropertyArray[j]);
					}
					Object requiredProperty = q.get(objPropertyArray[objPropertyArray.length - 1]);
					System.out.println(requiredProperty);
					if (requiredProperty == null || "".equals(requiredProperty)) {
						validateResult.add(variable);
						System.out.println(variable + "为必输参数，请检查");
						return variable;
					}
				}
			}
		}
		return null;
	}
}
