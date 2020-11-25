package com.learn.jeffrey.test.basic;

import com.learn.jeffrey.Main;
import org.springframework.beans.factory.Aware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/16 16:07
 **/
public class DateTest {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("");
    Instant instant = Instant.now();
    /**
     * simpleDateFormat 不是线程安全的，
     */
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws ParseException {
        MyThread01 thread01 = new MyThread01();
        thread01.setName("MyThread01");
        thread01.start();
        Thread thread02 = new Thread(new MyThread02());
        thread02.setName("thread02");
        thread02.start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 给对象加锁，解决线程不安全
                    synchronized (df){
                        String d1 = df.format(new Date(1));
                        try {
                            Date parseDate = df.parse(d1);
                            String d2 = df.format(parseDate);
                            // 结果中有false，说明线程不安全
                            System.out.println(d1);
                        } catch (Exception e) {
                        }
                    }

                }
            }).start();
        }
    }

    /**
     * 实现线程方式1：继承Thread类，重写run方法，
     */
    static class MyThread01 extends Thread implements Aware {
        public void run() {
            //编写自己的线程代码
            System.out.println(df.format(new Date()) + "-" + Thread.currentThread().getName());
        }
    }

    /**
     * 实现线程方式2：实现runnable接口；将其实例作为Thread构造方法的参数传入
     */
    static class MyThread02 implements Runnable {

        @Override
        public void run() {
            System.out.println(df.format(new Date()) + "-" + Thread.currentThread().getName());
        }
    }
}
