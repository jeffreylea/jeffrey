package com.learn.jeffrey.test.basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/6/22 17:55
 **/
public class Test {
    private static int value1 = 0;
    private static int value2 = 0;

    private static synchronized void increaceValue2() {
        value2++;
    }

    private static AtomicInteger value3 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();
        ExecutorService  service = Executors.newCachedThreadPool();

        AtomicInteger atomicInteger = new AtomicInteger();

        atomicInteger.getAndIncrement();
        atomicInteger.incrementAndGet();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test1();
                    increaceValue2();
                    value3.incrementAndGet();
                }
            }){

            }.start();
        }

        Thread.sleep(1000);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
    }

    public void reentrantLockTest(){
        Lock lock = new ReentrantLock();
    }
    public static void test1(){
        synchronized (""){
            value1++;
        }
    }
    public static int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
