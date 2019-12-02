package com.learn.jeffrey.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 定时任务练习
 * </p>
 * 
 * @author lijianfei
 * @date 2019-05-07
 */
public class TimedTaskTest {
	
	static AtomicInteger atomicInteger=new  AtomicInteger();
	static int i=0;
	public static void main(String[] args) {

		newScheduledThreadPoolTest();
	}

	// 使用定长线程池newScheduledThreadPool定时及周期性任务练习
	public static void newScheduledThreadPoolTest() {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

		Runnable task = new Runnable() {

			@Override
			public void run() {
				System.out.println("已启动"+atomicInteger.getAndIncrement()+"秒");
			}
		};

		executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
	}
}
