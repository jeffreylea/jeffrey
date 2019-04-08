/**
 * 
 */
package com.learn.jeffrey.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * description
 * </p>
 * 
 * @author lijianfei
 * @date 2019-04-08
 */
public class DataFormatUtil {

	/**
	 * dependency: dom4j 
	 * 将接收到的XML转为map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream is = request.getInputStream();
		Document doc = reader.read(is);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		is.close();
		return map;
	}
	
	/**
	 * 将map转化为json
	 * dependency: fastjson 
	 * @param map
	 * @return
	 */
	public static Object mapToJson(Object javaObject) {
		return JSONObject.toJSON(javaObject);
	}

}
