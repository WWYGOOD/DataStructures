package com.wwy.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int[] arr = randomNum(100000);
//        int[] arr={3,5,1,-1,9,6,4651,99,-5};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(System.currentTimeMillis() - start);

    }

    /**
     * 希尔排序交换法
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int temp = 0;
        //设置gap为步长，将数组分组，每次除以2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //对每一组进行排序
            for (int i = gap; i < arr.length; i++) {
                //比较一组数据的第一个和加上步长的第二个数据，如果大于就交换，小于就比较下一组
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序移位法
     */
    public static void shellSort2(int[] arr) {
        //设置分组以及步长
        for (int gap = arr.length; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j;
                // 对当前元素进行插入排序
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                // 将 temp 插入到正确的位置
                arr[j] = temp;
            }
        }
    }

    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }
}
