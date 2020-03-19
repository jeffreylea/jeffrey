package com.jeffrey.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests {

	@Test
	public void contextLoads() {
		int i=6;
		System.out.println(i);
		System.out.println(i++);
		System.out.println(i);
	}

}
