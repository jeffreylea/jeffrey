package com.learn.jeffrey.test;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

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
		Set<String> required = validateParams(array, values);

		System.out.println(required.toString());
	}

	/**
	 * 根据question中属性参数校验输入参数是否正确
	 * 
	 * @param questionRuleArray 问题定义规则的JSON对象数组
	 * @param values            map类型参数
	 * @return 属性集合
	 */
	public static Set<String> validateParams(JSONArray questionRuleArray, Map<String, Object> values) {
		Set<String> requiredProperties = new HashSet<>();
		checkVariablesByQuestionRules(questionRuleArray, values, requiredProperties);
		requiredProperties.remove(null);
		return requiredProperties;
	}

	/**
	 * 根据JSON数组对象中的属性，判断在values是否正确,将不正确的对象属性集合返回
	 * 
	 * @param questionRuleArray  问题定义规则的JSON对象数组
	 * @param values             map类型参数
	 * @param requiredProperties 必要属性集合
	 * @return 属性集合
	 */
	public static Set<String> checkVariablesByQuestionRules(JSONArray questionRuleArray, Map<String, Object> values,
			Set<String> requiredProperties) {
		// 循环问题的JSON数组对象，分别校验必输性，若接收参数不满足必输条件，将该变量保存在set中
		for (Object item : questionRuleArray) {
			JSONObject questionRule = (JSONObject) item;
			String requiredPropertie = checkVariablesByQuestionRule(questionRule, values);
			requiredProperties.add(requiredPropertie);
			if (isVariablesEnabled(questionRule, values) && questionRule.containsKey("subquestions")) {
				// 如果变量启用状态为启用,再判断子问题是否必输
				JSONArray arr = JSON.parseArray(JSONObject.toJSONString(questionRule.get("subquestions")));
				checkVariablesByQuestionRules(arr, values, requiredProperties);
			}
		}
		return requiredProperties;
	}

	/**
	 * 根据object中的属性，判断在values是否正确,如果不正确则返回该对象属性
	 * 
	 * @param questionRule 问题定义规则的JSON对象
	 * @param values       map类型参数
	 * @return 对象中属性名称
	 */
	public static String checkVariablesByQuestionRule(JSONObject questionRule, Map<String, Object> values) {
		boolean isValidate = isValidateByVariableShowIf(questionRule, values);
		boolean required = (Boolean) questionRule.get("required");
		if (isValidate && required) {
			String variable = (String) questionRule.get("variable");
			if (StringUtils.isNotEmpty(variable)) {
				// 获取对象属性
				String[] objPropertyArray = variable
						.split("\\.");
				Map propertyMap = null;
				if (objPropertyArray.length > 0) {
					propertyMap = (Map) values.get(objPropertyArray[0]);
					for (int j = 1; j < objPropertyArray.length - 1; j++) {
						if (CollectionUtils.isEmpty(propertyMap)) {
							if (log.isInfoEnabled()) {
								log.info(variable + "为必输参数，请检查");
							}
							return variable;
						}
						propertyMap = (Map) propertyMap.get(objPropertyArray[j]);
					}
					if (CollectionUtils.isEmpty(propertyMap)) {
						if (log.isInfoEnabled()) {
							log.info(variable + "为必输参数，请检查");
						}
						return variable;
					}
					Object requiredProperty = propertyMap.get(objPropertyArray[objPropertyArray.length - 1]);
					if (requiredProperty == null || "".equals(requiredProperty)) {
						if (log.isInfoEnabled()) {
							log.info(variable + "为必输参数，请检查");
						}
						return variable;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取变量启用状态
	 * 
	 * @param questionRule 问题定义规则的JSON对象
	 * @param values       map类型参数
	 * @return 是否启用
	 */
	public static boolean isVariablesEnabled(JSONObject questionRule, Map<String, Object> values) {
		String variable = (String) questionRule.get("variable");
		if (StringUtils.isNotEmpty(variable)) {
			// 获取对象属性
			String[] objPropertyArray = variable
					.split("\\.");
			Map propertyMap = null;
			if (objPropertyArray.length > 0) {
				propertyMap = (Map) values.get(objPropertyArray[0]);
				for (int j = 1; j < objPropertyArray.length - 1; j++) {
					if (CollectionUtils.isEmpty(propertyMap)) {
						return false;
					}
					propertyMap = (Map) propertyMap.get(objPropertyArray[j]);
				}
				if (CollectionUtils.isEmpty(propertyMap)) {
					return false;
				}
				Object isEnableValue = propertyMap.get("enabled");
				if (isEnableValue instanceof Boolean) {
					return (Boolean) isEnableValue;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否有showIf参数，如果有判断参数值是否等于传进来的值，如果相等再校验
	 * 
	 * @return 是否继续校验
	 */
	public static boolean isValidateByVariableShowIf(JSONObject questionRule, Map<String, Object> values) {
		// 如果不存在showIf参数，此对象需要校验函数返回true;如果存在并且相等，此对象需要校验，函数返回true；
		// 其它情况不需要校验，函数返回false
		String showIfStr = (String) questionRule.get("showIf");
		if (StringUtils.isNotEmpty(showIfStr)) {
			// 获取showIf中变量
			String variableOfShowIf = showIfStr.split("=")[0];
			String valueOfShowIf = showIfStr.split("=")[1];
			// 判断showIf中变量在传进来的values中的值
			if (StringUtils.isNotEmpty(variableOfShowIf)) {
				// 获取对象属性
				String[] objPropertyArray = variableOfShowIf
						.split("\\.");
				Map propertyMap = null;
				if (objPropertyArray.length > 0) {
					propertyMap = (Map) values.get(objPropertyArray[0]);
					for (int j = 1; j < objPropertyArray.length - 1; j++) {
						if (CollectionUtils.isEmpty(propertyMap)) {
							break;
						}
						propertyMap = (Map) propertyMap.get(objPropertyArray[j]);
					}
					if (!CollectionUtils.isEmpty(propertyMap)) {
						Object requiredProperty = propertyMap
								.get(objPropertyArray[objPropertyArray.length - 1]);
						String requiredPropertyStr = String.valueOf(requiredProperty);
						return (requiredProperty != null && StringUtils.equals(valueOfShowIf, requiredPropertyStr));
					}

				}
			}

		}
		return true;
	}
}
