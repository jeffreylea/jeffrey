package com.learn.jeffrey.algorithm;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/28 9:34
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {7, 3, 4, 6, 9, 1, 5};
        quickSort1(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int low, int high) {

        int start = low;
        int end = high;
        int key = arr[low];
        while (start < end) {
            // 从后往前比较
            while (end > start && arr[end] >= key) {
                end--;
            }
            if (arr[end] <= key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }

            // 从前往后比较
            while (end > start && arr[start] <= key) {
                start++;
            }
            if (arr[start] > key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }

            // 第一次循环比较结束，关键值的位置确定，左边的值都比关键值小，右边的值都比关键值大。
            // 递归
            if (start > low)
                quickSort(arr, low, start - 1);
            if (end < high)
                quickSort(arr, end + 1, high);
        }
    }

    public static void quickSort1(int[] arr, int low, int high) {
        int L = low;
        int R = high;
        int key = arr[L];
        while (L < R) {
            while (arr[R] >= key) {
                R--;
            }
            if (arr[R] <= key) {
                int temp = arr[L];
                arr[L] = arr[R];
                arr[R] = temp;
            }
            while (arr[L] <= key) {
                L++;
            }
            if (arr[L] >= key) {
                int temp = arr[R];
                arr[R] = arr[L];
                arr[L] = temp;
            }
            if (L > low) {
                quickSort1(arr, L - 1, R);
            }
            if (R < high) {
                quickSort1(arr, L, R - 1);
            }
        }
    }
}
