package com.learn.jeffrey.designpattern.observer;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 8:45
 **/
public class LaoWang implements Person{
    @Override
    public void getMessage(String s) {
        System.out.println("接到了小美打过来的电话，内容是:"+s);
    }
}
