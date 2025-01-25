package com.wwy.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {3,5,1,4,8};
        System.out.println("原始数组: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    // 归并排序入口方法
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // 计算中间位置
            int mid = left + (right - left) / 2;

            // 递归排序左半部分
            mergeSort(arr, left, mid);
            // 递归排序右半部分
            mergeSort(arr, mid + 1, right);

            // 合并已排序的两部分
            merge(arr, left, mid, right);
        }
    }

    // 合并两个子数组
    private static void merge(int[] arr, int left, int mid, int right) {
        // 计算左右子数组的长度
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 创建临时数组
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // 拷贝数据到临时数组
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        // 合并临时数组的指针
        int i = 0, j = 0, k = left;

        // 比较左右子数组元素，合并到原数组
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // 拷贝左数组剩余元素
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        // 拷贝右数组剩余元素
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }
}