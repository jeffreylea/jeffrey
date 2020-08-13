package com.learn.jeffrey.algorithm;

/**
 * Description <P>贪心算法理解</P>
 *
 * @Author lijianfei
 * @Date 2020/8/13 14:05
 **/
public class GreedyAlgorithm {
    static int N = 5;
    static int[] count = {2, 2, 2, 3, 5};//每一种纸币的数量
    static int[] value = {1, 5, 10, 50, 100};

    public static void main(String[] args) {
        System.out.println(solution(459));
    }

    public static int solution(int money) {
        int num = 0;
        for (int i = N - 1; i >= 0; i--) {
            int c = Math.min(money / value[i], count[i]);//每一个所需要的张数
            money = money - c * value[i];
            num += c;//总张数
        }
        if (money > 0) {
            System.out.println("money=" + money);
            return -1;
        }
        return num;
    }
}
