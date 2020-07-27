package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 10:19
 **/
public class Food {
    private String foodName;

    public Food() {
    }

    public Food(String foodName){
        this.foodName=foodName;
    }

    public String make(){
        return foodName;
    }

}
