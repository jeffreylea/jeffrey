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
	private static Singleton singleton= new Singleton();
	
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
		if(singleton==null) {
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
		Singleton singleton=Singleton.getInstance();
		//调用
		singleton.showMsg();
	}

}
