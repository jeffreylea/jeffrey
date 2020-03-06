package com.learn.jeffrey.test;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.loadtime.definition.LightXMLParser;

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
		/**
		 * (String[])list.toArray()
		 * 这种强制转化会报ClassCastException
		 * Exception in thread "main" java.lang.ClassCastException
		 */
		/**
		 * list.toArray(new String[0])
		 * 使用这种方式或者
		 * new String[] {}
		 * 这种方式可以正常运行。
		 */
		Object[] a=list.toArray(new String[5]);
		for(Object string:a) {
			System.out.println(string);
		}
	}
}
