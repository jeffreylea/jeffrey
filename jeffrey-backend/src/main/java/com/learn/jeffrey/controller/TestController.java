package com.learn.jeffrey.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	/*Map<Object, Object> map=new HashMap<>();
	map.put("key1", "value1");
	map.put("key2", 2);
	map.put("id", 3);
	map.put("name", 3);
	
	System.out.println(DataFormatUtil.mapToJson(map));;
	User user=DataFormatUtil.jsonStringToObject(DataFormatUtil.mapToJson(map).toString(), User.class);
	TestController controller=new  TestController();
	System.out.println(controller.name());*/
	   myTask();
	
}
   public int name() {
	   
	   int i=0;
	   try {
		return i=10;
	} finally {
		i=20;
		System.out.println(i);
	}
	
}
    static int i=0;
   public static void myTask() {
		ScheduledExecutorService executorService=Executors.newScheduledThreadPool(2);
		i=i+1;
		executorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
				
			}
		}, 0, 2, TimeUnit.SECONDS);
	
}
}
