package com.learn.jeffrey.algorithm;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/8/5 17:58
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{19, 11, 16, 13, 4, 18, 17, 5, 2, 14, 9, 3, 13, 9, 20};
        bubbleSort3(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }

    public static void bubbleSort(int[] array) {
        // 共需要比较array.length趟
        for (int i = 0; i < array.length; i++) {
            // 一趟比较
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // 优化1
    public static void bubbleSort1(int[] array) {
        // 共需要比较array.length
        for (int i = 0; i < array.length; i++) {
            // 一趟比较
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // 优化2 如果已经排好序，中间不再比较
    public static void bubbleSort2(int[] array) {
        // 共需要比较array.length
        for (int i = 0; i < array.length; i++) {
            // 一趟比较
            boolean flag = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    // 本趟比较有交换
                    flag = false;
                }
            }
            // 如果flag为true,说明上一趟比较之间没有元素交换，是已经排好序了，直接跳出循环，返回结果
            if (flag) {
                break;
            }
        }
    }

    //  先选出最小的数
    public static void bubbleSort3(int[] array) {
        // 共需要比较array.length
        for (int i = array.length; i > 0; i--) {
            // 一趟比较
            boolean flag = true;
            for (int j = array.length - 1; j > array.length - i; j--) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    // 本趟比较有交换
                    flag = false;
                }
            }
            // 如果flag为true,说明上一趟比较之间没有元素交换，是已经排好序了，直接跳出循环，返回结果
            if (flag) {
                break;
            }
        }
    }
}
