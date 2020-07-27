package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 14:23
 **/
public abstract class ShapeDecorator implements Shape {
    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        shape.draw();
    }
}
