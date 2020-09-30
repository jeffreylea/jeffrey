package com.learn.jeffrey.security.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/29 14:00
 **/
public class Main {
    public static void main(String[] args) {
       String s=new BCryptPasswordEncoder().encode("123");
        System.out.println(s);
    }



}
