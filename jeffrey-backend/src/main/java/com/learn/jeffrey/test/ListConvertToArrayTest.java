package com.learn.jeffrey.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年3月5日
 */
public class ListConvertToArrayTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		System.out.println((String[])list.toArray());
	}
}
