# 两数相加
给定两个 非空 链表来表示两个非负整数。位数按照 逆序 方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

示例：

输入： (2 -> 4 -> 3) + (5 -> 6 -> 4)
输出： 7 -> 0 -> 8
原因： 342 + 465 = 807


# 两数之和 (/LeetCode_test1.java)
给定一个整数数组和一个目标值，找出数组中和为目标值的 两个 数。

你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
---
```
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
```
```
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
```


