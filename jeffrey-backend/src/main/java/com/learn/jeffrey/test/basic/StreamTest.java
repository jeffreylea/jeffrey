package com.learn.jeffrey.test.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <P>Stream API学习</P>
 *
 * @Author lijianfei
 * @Date 2020/5/2 9:42
 **/
public class StreamTest {
    public static void main(String args[]){
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
        list.add("test5");
        list.stream().count();
    }
}
