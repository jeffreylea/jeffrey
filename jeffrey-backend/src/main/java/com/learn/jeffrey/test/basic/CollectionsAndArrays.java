package com.learn.jeffrey.test.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年3月23日
 */
@Slf4j
public class CollectionsAndArrays {
	public static void main(String[] args) {
		//System.out.print(isNotNull("",""));


//		File file = new File("D:\\logs");
//		System.out.print(file.listFiles(File::isHidden).length);;
//		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-w");
//		System.out.print(sdf.format(new Date()));;
		// Collections工具排序方法练习
//		List<String> list = new ArrayList<>();
//		list.add("test1");
//		list.add("test2");
//		list.add("test3");
//		list.add("test4");
//		list.add("test5");
//		log.info("原始列表:{}", list);
		// void reverse(List<?> list): 反转
//		Collections.reverse(list);
//		log.info("Collections.reverse(list):{}", list);
		// Collections.shuffle(list)：随机排序，shuffle这个单词有洗牌的意思，
		// 就像洗牌一样打乱顺序
//		Collections.shuffle(list);
//		log.info("Collections.shuffle(list):{}", list);
//		// Collections.sort(list):自然排序：正序
//		Collections.sort(list);
//		log.info("Collections.sort(list):{}", list);
		// 定制排序，由比较器comparator控制排序逻辑
//		Collections.sort(list, new Comparator<String>() {
//
//			// 重写比较器，如果是对象，例如根据对象中的姓名排序，可以用o1.getName.compareTo(o2)
//			@Override
//			public int compare(String o1, String o2) {
//				return o2.compareTo(o1);
//			}
//		});
//		log.info("Collections.sort(list, compatator):{}", list);
		// Collections.swap(list, 0, 2)交换两个索引位置的元素 ;swap意为交换，下面即为第一个元素和第三个元素交换
//		Collections.swap(list, 0, 2);
//		log.info("Collections.swap(list):{}", list);
		// 在练习Collections.rotate(List<?> list, int
		// distance)这个方法时，首先学习rotate这个单词，rotate是旋转的意思，该方法就是将集合进行旋转操作。
		// 当 distance>0时，将list后的distance个元素整体挪到前面,例如当distance参数为2时，结果如下
		// [test3, test4, test5, test2, test1]->[test1, test3, test4, test5, test2]
		// 当distance<0 时，将list的distance个元素整体移动到后面，当distance>list的长度时，可以看做distance =
		// distance-list
//		Collections.rotate(list, 6);
//		log.info("Collections.rotate(list):{}", list);

		/**
		 * 总结： log.info("原始列表:{}", list); log.info("Collections.reverse(list):{}",
		 * list); log.info("Collections.shuffle(list):{}", list);
		 * log.info("Collections.sort(list):{}", list);
		 * log.info("Collections.sort(list):{}", list); log.info("Collections.swap(list,
		 * compatator):{}", list); log.info("Collections.rotate(list):{}", list);
		 *
		 * 上面语句打印的结果如下： 原始列表:[test1, test2, test3, test4, test5]
		 * Collections.reverse(list):[test5, test4, test3, test2, test1]
		 * Collections.shuffle(list):[test1, test5, test2, test4, test3]
		 * Collections.sort(list):[test1, test2, test3, test4, test5]
		 * Collections.sort(list, compatator):[test5, test4, test3, test2, test1]
		 * Collections.swap(list):[test3, test4, test5, test2, test1]
		 * Collections.rotate(list):[test1, test3, test4, test5, test2]
		 * 明白单词的意思，很容易记下Collections工具中排序方法的使用。
		 */

		// binarySearch(List<? extends Comparable<? super T>> list, T
		// key)：二分查找列表中的指定元素，返回索引，
		// 查找的列表list必须是有序的,而且必须是正序。
//		Collections.sort(list);
//		int a = Collections.binarySearch(list, "test2");
//		log.info("Collections.binarySearch(list, 'test2'):{}", a);a

		// Collections.max(list):根据元素的自然顺序，返回最大的元素。Collections.max(list)是返回最小元素
//		String max = Collections.max(list);
//		log.info("Collections.max(list):{}", max);
//		String min = Collections.min(list);
//		log.info("Collections.min(list):{}", min);

		// max(Collection<? extends T> coll, Comparator<? super T> comp) 根据定制排序，返回最大元素;
		// 如果倒序排列，那么最大的元素其实就是最小的元素,这个方法和min(Collection<? extends T> coll, Comparator<? super T> comp)使用方法一样。
//		String d = Collections.max(list, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o2.compareTo(o1);
//			}
//		});
//		log.info("Collections.max(list, new Comparator<String>()):{}", d);
		
		// void fill(List list, Object obj)用指定的元素代替指定list中的所有元素
		/*Collections.fill(list, "0");
		log.info("fill(List list, Object obj):{}",list);*/
		
		// Collections.frequency(list, "test1") 统计元素test1出现的次数
//		int e = Collections.frequency(list, "test1");
//		log.info("Collections.frequency(list, \"test1\"):{}", e);
		
		// Collections.indexOfSubList(list, subList) 统计sublist在list中第一次出现的索引，找不到则返回-1
		// int lastIndexOfSubList(List source, list target)是统计 target在source中最后一次出现的索引，找不到则返回-1
//		List<String> subList = list.subList(2, 3);
//		int f = Collections.indexOfSubList(list, subList);
//		log.info("Collections.indexOfSubList(list, subList):{}", f);
		
		// Collections.replaceAll(list, "test2", "Test2")新元素Test2替代旧元素test2,
		// 这里要和Collections.fill((List list, Object obj)区别，是将所有元素替代
//		Collections.replaceAll(list, "test2", "Test2");
//		log.info("Collections.replaceAll(list, \"test2\", \"Test2\"):{}", list);
//
//		boolean b;

	}

	public static Boolean valueOf(boolean b){
		return b?Boolean.TRUE:Boolean.FALSE;
	}

	/**
	 * 多个对象是否为null判断，有一个为null即返回false
	 * @param objects
	 * @return
	 */
	public static boolean isNotNull(Object... objects) {
		if (objects == null) {
			return false;
		}
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null) {
				return false;
			}
		}
		return true;
	}
}
