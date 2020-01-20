/**
 * 
 */
package com.learn.jeffrey.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Administrator
 *
 */
public class HashMapTest {
	public static void main(String[] args) {
		String a="jeffrey";
		
		System.out.println(a.hashCode());;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 9);
		System.out.print(sf.format(c.getTime()));
	}
	
	

}
