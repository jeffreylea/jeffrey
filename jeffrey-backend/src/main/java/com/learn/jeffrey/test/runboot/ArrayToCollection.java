package com.learn.jeffrey.test.runboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author lijianfei
 *         <p>
 *         数组转集合
 *         </p>
 *         2020年1月19日
 */
public class ArrayToCollection {

	public static void main(String[] args) {
		//--------------数组转集合-------------
		int n = 10;
		Integer[] arr = new Integer[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		//Arrays.asList(arr)返回的list不能进行增删操作
		List<Integer> list = new ArrayList<>(Arrays.asList(arr));
		list.remove(1);
		for (Integer i : list) {
			System.out.print(i + ",");
		}
		//--------------比较集合-------------
		System.out.println("最小值："+Collections.min(list));
		System.out.println("最大值："+Collections.max(list));
		
		//--------------hashmap遍历-------------
		//values():获取集合中所有的value值
		HashMap<String, String> hMap = new HashMap<>();
		hMap.put("1", "first");
		hMap.put("2", "second");
		hMap.put("3", "three");
		Collection<String> a=hMap.values();
		System.out.println("forEach");
		for(String k:a) {
			
		}
		a.forEach(p->System.out.println(p));
		System.out.println(a);
		Iterator<String> iterator = a.iterator();
		while (iterator.hasNext()) {
			String value = iterator.next();
			System.out.println(value);
		}
		Iterator<String> iterKey= hMap.keySet().iterator();
		while(iterKey.hasNext()) {
			String key = iterKey.next();
			System.out.println("key:"+key+"  value:"+hMap.get(key));
		}
		Set<Entry<String, String>> entry = hMap.entrySet();
		Iterator<Entry<String, String>> iteratorEntry = entry.iterator();
		while(iteratorEntry.hasNext()) {
			Entry<String, String> entrys = iteratorEntry.next();
			System.out.println("key:"+entrys.getKey()+"  value:"+entrys.getValue());
		}
		System.out.println(entry+"::"+entry.size());
		
	}

}
