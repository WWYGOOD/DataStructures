package com.wwy.sort;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr={123,56456,-565465,545115,-44456,45178996,-5896};
        sort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right) {
        if (right <= left) return;
        int pivot = arr[left];
        int begin = left;
        int end = right;
        while (right > left) {
            while (right > left && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (right > left && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left]=pivot;
        sort(arr,begin,left-1);
        sort(arr,left+1,end);

    }
}
