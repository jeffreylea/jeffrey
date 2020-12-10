package com.learn.jeffrey.test;

import cn.hutool.extra.spring.SpringUtil;
import com.learn.jeffrey.test.spring.SpringBoy;
import com.learn.jeffrey.test.spring.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/25 18:12
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringBoyLifeTest {
    @Autowired
    private ApplicationContext conn;
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext conn = new ClassPathXmlApplicationContext("SpringBoyLifeTest-context.xml");
//        SpringBoy boy1 = SpringUtil.getBean("SpringBoy");
//        SpringBoy boy = (SpringBoy)conn.getBean("SpringBoy");
//        conn.registerShutdownHook();

        AbstractApplicationContext ac = new ClassPathXmlApplicationContext("SpringBoyLifeTest-context.xml");
        SpringBoy springBoy = ac.getBean(SpringBoy.class);
        Class class1 = springBoy.getClass();
        class1.getConstructors();

    }
}
