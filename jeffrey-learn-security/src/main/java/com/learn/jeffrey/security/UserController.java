package com.learn.jeffrey.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello2")
    public String hello2(){
        return "hello2";
    }

    @RequestMapping("/hello3")
    public String hello3(){
        return "hello3";
    }
}