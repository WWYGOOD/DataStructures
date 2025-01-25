package com.wwy.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        
        int search = search(arr,5678);
        System.out.print("程序执行时间：");
        System.out.println(+System.currentTimeMillis()-start);

        System.out.println(search);
    }
    /**
     * 查找
     *
     * @param arr 要查找的数组
     */
    public static int search(int[] arr, int findVal) {
        int left = 0;
        int right = arr.length - 1;
        int index = insertValueSearch(arr, left, right, findVal);
        return index;
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (findVal > mid) {
            //向右
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < mid) {
            //向左
            return insertValueSearch(arr, left, mid-1, findVal);
        } else {
            return mid;
        }
    }

}
