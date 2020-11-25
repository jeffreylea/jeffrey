package com.learn.jeffrey.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/25 17:19
 **/
public class SpringBoy implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String name;

    public void setName(String name) {
        System.out.println("2.设置相关的属性。。。");
        this.name = name;
    }

    public SpringBoy(){
        System.out.println("1、构造方法");
    }
    private void init(){
        System.out.println("7、初始化");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("创建bean的factory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("3、设置Bean的名字："+name+"...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9、销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6.预处理，他是processor的before后执行，和processor拥有相同的功能");
    }
    public void selfDestroy(){
        System.out.println("10.自定义销毁");
    }
}
