package com.learn.jeffrey.test.spring;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;

import javax.swing.*;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/19 17:48
 **/
@Data
public class User implements BeanNameAware {
    public String name;

    String beanname;

    @Override
    public void setBeanName(String name) {
        this.beanname = name;
    }
}
