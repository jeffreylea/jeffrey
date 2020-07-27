package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 14:19
 **/
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
