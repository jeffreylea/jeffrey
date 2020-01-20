package com.learn.jeffrey.test.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年1月20日
 */
public class LeetCode_test1 {
	public static void main(String[] args) {
		/*
		 * Scanner c = new Scanner(System.in); System.out.println(); String[] b=
		 * c.nextLine().split(","); c.close();
		 */
		int[] arr = { 1, 2, 3, 5, 4, 6 };
		int target = 10;
		int[] a = twoSum1(arr, target);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}

	}

	class ListNode{
		public int val;
		public ListNode next;
		
		
	}
	public static int[] twoSum(int[] arr, int target) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (target == (arr[i] + arr[j])) {
					return new int[] { arr[i], arr[j] };
				}
			}
		}
		throw new IllegalArgumentException("no solution");
	}

	/**
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int[] twoSum1(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(target - arr[i])) {
				return new int[] { target - arr[i], arr[i] };
			}
			map.put(arr[i], i);
		}
		return new int[] { 0, 0 };
	}
}
