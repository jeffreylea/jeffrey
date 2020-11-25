package com.learn.jeffrey.test.basic;

import com.learn.jeffrey.test.spring.SpringBoy;
import com.learn.jeffrey.test.spring.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/25 18:12
 **/
public class SpringBoyLifeTest {
    @Autowired
    private ApplicationContext conn;
    public static void main(String[] args) {
        ClassPathXmlApplicationContext conn = new ClassPathXmlApplicationContext("SpringBoyLifeTest-context.xml");
        SpringBoy boy = (SpringBoy)conn.getBean("SpringBoy");
        conn.registerShutdownHook();
    }
}
