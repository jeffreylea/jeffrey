package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 10:22
 **/
public class Bread extends Food {

    private Food basicFood;

    public Bread(Food basicFood) {
        this.basicFood = basicFood;
    }

    public String make() {
        return basicFood.make() + "+面包";
    }
}
