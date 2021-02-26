package com.learn.jeffrey.test;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/6/4 14:09
 **/
public class ThreadTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


    }
}
