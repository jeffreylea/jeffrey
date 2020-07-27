package com.learn.jeffrey.designpattern.decorator;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 14:26
 **/
public class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(Shape shape) {
        super(shape);
    }
    @Override
    public void draw() {
        shape.draw();
        setRedBorder(shape);
    }
    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
