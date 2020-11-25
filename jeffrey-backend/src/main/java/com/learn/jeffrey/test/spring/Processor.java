package com.learn.jeffrey.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * Description <P>监控Bean的创建，在Bean初始化之前或之后做一些操作</P>
 *
 * @Author lijianfei
 * @Date 2020/11/25 17:23
 **/
public class Processor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("8.bean处理器：bean创建之后也可以称为后预处理");
        if (bean instanceof User){
            System.out.println("监控到Bean,业务处理");
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5.bean处理器：bean创建之前");
        return bean;
    }

}
