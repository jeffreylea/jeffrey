package com.learn.jeffrey.test.basic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2021/2/19 18:48
 **/
public class TestA implements Cloneable, Serializable {
    public void hello(){
        System.out.println("hello");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestA a = new TestA();
        a.clone();
    }
    public Object clone() throws CloneNotSupportedException {
       TestA testA = (TestA) super.clone();
       return testA;
    }
}
