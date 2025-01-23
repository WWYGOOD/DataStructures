package com.wwy.recursion;

import java.util.List;

public class Queue8 {
    int maxSize = 8;
    int[] arr = new int[maxSize];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }

    /**
     * 放置第n个皇后
     *
     * @param n 表示当前放置第n行第n个皇后
     */
    public void check(int n) {
        if (n == maxSize) {
            printQueue();
            return;
        } else {
            //依次放入皇后，判断是否冲突
            for (int i = 0; i < maxSize; i++) {
                //先把这个皇后放置到第n个皇后到i列
                arr[n] = i;
                //判断是否冲突，如果不冲突，继续放下一个
                if (flag(n)) {
                    check(n + 1);
                }
            }
        }

    }

    /**
     * 判断要放置的棋子的前一个棋子是否冲突
     * 说明：
     * 1.arr[i] == arr[n]判断该第n个皇后和前一个n-1个皇后是否在同一列
     * 2.Math.abs(n - i) == Math.abs(arr[i] - arr[n])斜率相等，说明前后两个皇后在同一条直线上
     * 3.没必要判断是否在同一行
     *
     * @param n 表示第n行第n个皇后
     */
    public boolean flag(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    public void printQueue() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("第" +Integer.parseInt(String.valueOf(i+ 1)) + "行" + "第" + Integer.parseInt(String.valueOf(arr[i] + 1))+ "列");
            System.out.print(",");
        }
        System.out.println(" ");
    }
}
