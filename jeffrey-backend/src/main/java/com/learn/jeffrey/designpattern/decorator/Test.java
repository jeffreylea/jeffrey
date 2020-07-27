package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 10:30
 **/
public class Test {
    public static void main(String[] args) {
        Food food = new Bread(new Cream(new Food("香肠：")));
        System.out.println(food.make());
    }
}
