package com.learn.jeffrey.test.basic;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.DESede;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.learn.jeffrey.utils.EncryptUtils;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.Md5Crypt;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/6/22 17:55
 **/

public class Test {
    private static int value1 = 0;
    private static int value2 = 0;

    private String value4 = "q";
    private String value5 = "q";
    private String value6 = "q";
    private String value7 = "q";

    private static synchronized void increaceValue2() {
        value2++;
    }

    private static AtomicInteger value3 = new AtomicInteger(0);

    private static final ThreadLocal threadSession = new ThreadLocal();


    /**
     * @param args
     */
    public static void main(String[] args) {

        DES des = new DES("IKWLgczsuSwpDhKjeH8YLa4Bd7EU5s35".getBytes());
        byte[] a = des.decrypt("330ac00d58764bf3a1aac212201bd5f6");
        System.out.println(new String(a));

        Map<String, String> map = System.getenv();
        System.out.println(map);
        Stack<Integer> stack;
        List<A> aList = new ArrayList<A>();

        for (A item : aList) {
            System.out.println(item.getClass() + ":" + item.toString());
            if (item instanceof B) {
                System.out.println(((B) item).name);
            }
            // class atest.A:A [name=a1]
            // class atest.B:B [name=b1, sex=true]
        }
    }

    static class A {
        int age = 12;
    }

    static class B extends A {
        String name = "w";
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        while (l1 != null) {
            while (l2 != null) {
                if (l1.val > l2.val) {

                    l2 = l2.next;
                } else {
                    l1 = l1.next;
                }
            }

        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void testLock() {
        ReadWriteLock readWriteLock;
        Semaphore semaphore = new Semaphore(5);
        try {
            semaphore.acquire();
            try {

            } catch (Exception e) {

            } finally {
                semaphore.release();
            }
        } catch (Exception e) {

        }
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
            System.out.println("开始wait");
            condition.await(20, TimeUnit.SECONDS);
            condition.signal();
            System.out.println("" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    static class MyThread extends Thread implements Runnable {
        public void run() {
            System.out.println("线程类");
        }
    }

    static class MyRunnable extends HashSet implements Runnable {

        @Override
        public void run() {
            System.out.println("runnable线程类");
        }
    }

    public static void test2() throws ClassNotFoundException {
        ClassLoader clazzLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String clazzName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream is = getClass().getResourceAsStream(clazzName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        String currentClass = "com.learn.jeffrey.test.basic.Test";
        Class<?> clazz = clazzLoader.loadClass(currentClass);
        System.out.println(System.getProperty("sun.boot.class.path"));
        ClassLoader loader = Test.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);
    }

    public static void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();
        ExecutorService service = Executors.newCachedThreadPool();

        AtomicInteger atomicInteger = new AtomicInteger();

        atomicInteger.getAndIncrement();
        atomicInteger.incrementAndGet();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    while (true) {
                        sleep(1000);
                        test1();
                        increaceValue2();
                        value3.incrementAndGet();
                        System.out.println("value1=" + value1);
                        System.out.println("value2=" + value2);
                        System.out.println("value3=" + value3);
                    }
                }
            }) {

            }.start();
        }

        sleep(1000);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
    }

    public void reentrantLockTest() {
        Lock lock = new ReentrantLock();
    }

    public static void test1() {
        synchronized ("") {
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

    public int add() {
        int a = 1;
        int b = 2;
        return a + b;
    }
}
