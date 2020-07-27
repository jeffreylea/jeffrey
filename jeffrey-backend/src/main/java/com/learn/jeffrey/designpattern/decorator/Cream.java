package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 10:26
 **/
public class Cream extends Food {
    private Food basicFood;

    public Cream(Food basicFood) {
        this.basicFood = basicFood;
    }

    public String make() {
        return basicFood.make() + "奶油";
    }
}
