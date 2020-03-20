package com.learn.jeffrey.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.learn.jeffrey.config.Log;
import com.learn.jeffrey.snowflake.SnowFlakeIdGenerator;
import com.learn.jeffrey.utils.DataFormatUtils;

/**
 * @author Jeffrey
 *
 * 2019年4月3日
 */
@Controller
@RestController
public class TestController{
    
    @PostMapping("/h")
    public String hello(@RequestParam("test")String test,HttpServletRequest request) throws IOException, DocumentException{
    	Map<String, String> map=DataFormatUtils.xmlToMap(request);
    	@SuppressWarnings("unchecked")
		Map<String, Object> map2=DataFormatUtils.jsonStringToObject(DataFormatUtils.mapToJson(map).toString(), Map.class);
         return DataFormatUtils.mapToXml("xml",map2);
    
    }
    
    @GetMapping("/test")
	public ResponseEntity<User> getUser() {
		User user = new User();
		user.setContent("内容");;
		/*Map<String,String> a = new HashMap<>();
		a.put("name", "jeffrey");*/
		if(user != null) {
			return ResponseEntity.ok(user);//new ResponseEntity<>(user, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

   public static void main(String[] args) throws IOException {
	   File t = new File("D:\\\\2.txt");
	   Writer  out = new FileWriter(t);
	   
	   for(int i=0;i<(1<<10);i++) {
		   out.write((SnowFlakeIdGenerator.generateId())+"\n");;; 
	   
	   }
	   out.close();
	   final Map<Long, Integer> map21 = new ConcurrentHashMap();
	   map21.put(1L, 1);
	   Map<String, String> map1=DataFormatUtils.urlSplit("https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2015101400446982&redirect_uri=http%3A%2F%2Fexample.com");
	   String appId=map1.get("app_id");
	   String redirext=map1.get("redirect_uri");
	   System.out.println(appId);
	   System.out.println(redirext);
	   User user = new User();
	 user.setContent("l");
	 user.setFlag(true);
	 test1 a=new test1();
	 a.setContent("2");
	 user.setTest11(a);
		System.out.println(JSON.toJSONString(user));
		Map<String, Object> w=DataFormatUtils.parseToMap(JSON.toJSONString(user));
		System.out.println((Boolean)w.get("flag"));
	Map<Object, Object> map=new HashMap<>();
	map.put("key1", "value1");
	map.put("key2", 2);
	map.put("id", 3);
	map.put("name", 3);
	System.out.println(map);
	String string=DataFormatUtils.mapToJson(map).toString();
	System.out.println(DataFormatUtils.parseToMap(null));
	/*System.out.println(DataFormatUtil.mapToJson(map));;
	User user=DataFormatUtil.jsonStringToObject(DataFormatUtil.mapToJson(map).toString(), User.class);
	TestController controller=new  TestController();
	System.out.println(controller.name());*/
	   //myTask();
	
}
   @PostMapping("/ht")
   @Log()
   public int name(HttpServletRequest request) {
	   
	   int i=0;
	   try {
		return i=10;
	} finally {
		i=20;
		System.out.println(i);
	}
	
}
   
   public static void myTask1() {
		ScheduledExecutorService executorService=Executors.newScheduledThreadPool(2);
		
		executorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
				//System.out.println(i+1);
			}
		}, 0, 2, TimeUnit.SECONDS);
	
}
}
