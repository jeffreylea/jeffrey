package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 10:28
 **/
public class Vegetable extends Food {
    private Food basicFood;

    public Vegetable(Food basicFood) {
        this.basicFood = basicFood;
    }

    public String make() {
        return basicFood.make() + "蔬菜";
    }
}
