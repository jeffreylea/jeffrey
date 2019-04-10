package com.learn.jeffrey.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jeffrey.utils.DataFormatUtil;

/**
 * @author Jeffrey
 *
 * 2019年4月3日
 */
@Controller
@RestController
public class TestController{
    
    @PostMapping("/h")
    public String hello(HttpServletRequest request) throws IOException, DocumentException{
    	Map<String, String> map=DataFormatUtil.xmlToMap(request);
    	@SuppressWarnings("unchecked")
		Map<String, Object> map2=DataFormatUtil.jsonStringToObject(DataFormatUtil.mapToJson(map).toString(), Map.class);
         return DataFormatUtil.mapToXml("xml",map2);
    
    }

   public static void main(String[] args) {
	Map<Object, Object> map=new HashMap<>();
	map.put("key1", "value1");
	map.put("key2", 2);
	map.put("id", 3);
	map.put("name", 3);
	
	System.out.println(DataFormatUtil.mapToJson(map));;
	User user=DataFormatUtil.jsonStringToObject(DataFormatUtil.mapToJson(map).toString(), User.class);
}
}
