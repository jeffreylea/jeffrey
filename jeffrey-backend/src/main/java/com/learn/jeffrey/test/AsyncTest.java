package com.learn.jeffrey.test;

import java.util.Random;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lijianfei
 *         <p>
 *         异步方法使用
 *         </p>
 *         2020年3月11日
 */
@Slf4j
public class AsyncTest {
	
	public static void doTaskOne() throws InterruptedException {
		Random random = new Random();
		StopWatch stopWatch = new StopWatch("任务耗时");
		stopWatch.start("任务一耗时");
		Thread.sleep(random.nextInt(1000));
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		
	}
	
	public static void main(String[] args) {
		try {
			doTaskOne();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
