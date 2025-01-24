package com.wwy.sort;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, 7, 5, 1,-5494,-5454,222,112,1545,-44,11,1235,88,-48894,-1};
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        // 输出：1 3 5 6 8 9 
    }

    public static void sort(int[] arr, int left, int right) {
        if (right <= left) return;
        //选择最左侧数为基准数
        int pivot = arr[left];

        int begin = left, end = right;
        while (left < right) {
            // 从右向左找第一个小于pivot的元素
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right]; // 将找到的小于pivot的值填到左坑

            // 从左向右找第一个大于pivot的元素
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left]; // 将找到的大于pivot的值填到右坑
        }
        arr[left] = pivot; // 最后将基准值填入中间

        sort(arr, begin, left - 1);
        sort(arr, left + 1, end);
    }

}


