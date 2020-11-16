package com.learn.jeffrey.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/17 11:02
 **/
@RestController
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/hello2")
    public String hello2(HttpServletRequest request){
        String authCode = request.getHeader("authCode");
        System.out.println("设备接口认证参数authCode="+authCode);
        String test = SecurityContextHolder.getContext().getAuthentication().getName();
        return test;
    }

    @RequestMapping("/hello3")
    public String hello3(){
        return "hello3";
    }
}
