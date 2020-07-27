/**
 *
 */
package com.learn.jeffrey.designpattern.singleton;

/**
 * @author Administrator
 *
 */
public class Singleton {
    /**
     * new 静态变量
     *
     */
    private static Singleton singleton = new Singleton();

    /**
     * 构造方法私有化
     */
    private Singleton() {

    }

    /**
     * 公共获取对象的方法
     * @return
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public void showMsg() {
        System.out.println("单例测试");
    }

    /**
     * 使用
     * @param args
     */
    public static void main(String[] args) {
        //获取对象
        Singleton singleton = Singleton.getInstance();
        //调用
        singleton.showMsg();
    }

    /**
     * 饿汉模式 全局的单例实例在类加载时创建
     */
    static class HungryWay {
        //静态初始化时创建实例
        private static HungryWay hungryWay = new HungryWay();

        //构造方法私有化，只有一个方法被private修饰的构造方法，外部无法new创建实例
        private HungryWay() {
        }

        // 外部使用时获取实例
        private static HungryWay getInstance() {
            return hungryWay;
        }
    }

    /**
     * 懒汉模式 全局的单例实例
     */
    static class LazyWay {
        private static LazyWay lazyWay;

        private LazyWay() {
        }
        // 线程安全
        private static synchronized LazyWay getInstance() {
            if (lazyWay == null) {
                lazyWay = new LazyWay();
            }
            return lazyWay;
        }
    }

}
