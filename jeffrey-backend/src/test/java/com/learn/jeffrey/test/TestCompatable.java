package com.learn.jeffrey.test;

import com.learn.jeffrey.test.basic.Test;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/12/21 15:43
 **/
public class TestCompatable {
    public static void main(String[] args) {
        Test.Student s1 = new Test.Student(11,"");
        s1.setAge(18);
        s1.setUserName("张三");
        Test.Student  s2 = new Test.Student(12,"");
        s2.setAge(20);
        s2.setUserName("李四");
        Comparable max = getMax(s1,s2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable c1,Comparable c2){
        int result =c1.compareTo(c2);
        if (result >=0){
            return c1;
        }else {
            return c2;
        }
    }
}
