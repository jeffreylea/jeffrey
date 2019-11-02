package com.learn.jeffrey.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BasisTest {
	public static void main(String[] args) {
		System.out.println("test");
		ArrayList<Integer> arrayList= new ArrayList<>();
		arrayList.add(-1);
		arrayList.add(3);
		arrayList.add(3);
		arrayList.add(-5);
		arrayList.add(7);
		arrayList.add(4);
		arrayList.add(-9);
		arrayList.add(-7);
		System.out.println("原始数组："+arrayList);
		Collections.reverse(arrayList);
		System.out.println("Colletions.reverse(arrayList):"+arrayList);
		Collections.rotate(arrayList,3);
		System.out.println("Colletions.rotate(arrayList,3)"+arrayList);
		Collections.rotate(arrayList,-3);
		System.out.println("Colletions.rotate(arrayList,-3)"+arrayList);
	
		Collections.sort(arrayList);
		System.out.println(arrayList);
		//Collections.shuffle(arrayList);
		System.out.println(arrayList);
		Collections.swap(arrayList, 1, 2);
		System.out.println(arrayList);
		
		//定制排序
		Collections.sort(arrayList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		
		//Collections.sort(arrayList);
		System.out.println(arrayList);
		System.out.println(Collections.binarySearch(arrayList, -5));
		
		System.out.println(Collections.max(arrayList));
		System.out.println(Collections.min(arrayList));
//		Collections.fill(arrayList, 9);
//		System.out.println(arrayList);
		System.out.println(Collections.frequency(arrayList, 3));
		
		int[] ints= {1,2,3,4,5};
		System.out.println(Arrays.toString(ints));
		int[] intb=Arrays.copyOfRange(ints, 1, 3);
		System.out.println("Arrays.copyOf(ints, 2)");
		for (int i : intb) {
			System.out.println(i);
		}
	}
	
	

}
