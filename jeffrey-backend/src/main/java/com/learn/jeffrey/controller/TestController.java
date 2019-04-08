package com.learn.jeffrey.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
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
    public Map<String , String> hello(HttpServletRequest request) throws IOException, DocumentException{
        return DataFormatUtil.xmlToMap(request);
    }

   public static void main(String[] args) {
	Map<Object, Object> map=new HashMap<>();
	map.put("key1", "value1");
	map.put("key2", 2);
	map.put(3, 3);
	
	Map<Object, Object> map1=new HashMap<>();
	map1.put("map1", map);
	System.out.println(DataFormatUtil.mapToJson(map1));;
	
}
}
