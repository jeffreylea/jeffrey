package com.learn.jeffrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Description <P>网关</P>
 *
 * @Author lijianfei
 * @Date 2020/7/24 18:12
 **/
@EnableZuulProxy
@SpringBootApplication
public class JeffreyZuulService {
    public static void main(String[] args) {
        SpringApplication.run(JeffreyZuulService.class, args);
    }
}
