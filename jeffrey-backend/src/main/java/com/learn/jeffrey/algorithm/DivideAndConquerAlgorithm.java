package com.learn.jeffrey.algorithm;

/**
 * Description <P>分支算法理解-归并排序</P>
 *
 * @Author lijianfei
 * @Date 2020/8/13 15:37
 **/
public class DivideAndConquerAlgorithm {
    public static void main(String[] args) {
        int[] a = {6, 3, 2};
        divideAndConquerSort(a, 0, a.length - 1);

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void divideAndConquerSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            System.out.println("low=" + low + "mid=" + mid);
            divideAndConquerSort(a, low, mid);

            System.out.println("mid=" + mid + "high=" + high);
            divideAndConquerSort(a, mid + 1, high);
            // 左右归并
            System.out.println("合并");
            merge(a, low, mid, high);
        }
        System.out.println("----divideAndConquerSort----");
    }


    public static void merge(int[] a, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中，
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }
}
