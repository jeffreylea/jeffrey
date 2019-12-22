package com.learn.jeffrey.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Administrator
 * @Description 
 * <p> 练习配置文件 </p>
 * @Date 2019-12-22 02:57:32
 */
@RestController
public class ProfileActiveTest {
	
	@Value("${test.config.name}")
	private String name;
	
	/**
	 * 接收参数时可以加注解@RequestParam("age")，默认true是必传的，也可设置false
	 * @Requestparam(name = "age",requried = false)
	 * @param age
	 * @return
	 */
	@GetMapping("profiles")
	public String testProfile(@RequestParam("age")String age) {
		return name+":"+age;
	}

}
