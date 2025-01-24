package com.wwy.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 6,-1};
        int[] sort = selectSort(arr);
        System.out.println(Arrays.toString(sort));


    }
    private static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;//假定最小值为第一个下标为0，后面依次加1
            int min = arr[minIndex];//最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }
    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }
}




