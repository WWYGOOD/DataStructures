package com.wwy.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        /**
         * 冒泡排序
         */
//        int[] arr = {3, -1, 5, -2, 8, 0};
        long start = System.currentTimeMillis();
        int[] arr = randomNum(80000);
        int[] sort = bubbleSort(arr);

        System.out.println(Arrays.toString(sort));
        System.out.println(System.currentTimeMillis()-start);
    }

    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }

    private static int[] bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }
}
